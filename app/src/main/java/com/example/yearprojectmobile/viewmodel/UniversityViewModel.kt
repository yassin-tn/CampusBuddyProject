package com.example.yearprojectmobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yearprojectmobile.data.*
import com.example.yearprojectmobile.database.AppDatabase
import com.example.yearprojectmobile.repository.Message
import com.example.yearprojectmobile.repository.UniversityRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UniversityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UniversityRepository

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    private val _teacherCourses = MutableStateFlow<List<Course>>(emptyList())
    val teacherCourses: StateFlow<List<Course>> = _teacherCourses.asStateFlow()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _attendanceRecords = MutableStateFlow<List<AttendanceRecord>>(emptyList())
    val attendanceRecords: StateFlow<List<AttendanceRecord>> = _attendanceRecords.asStateFlow()

    // Rate limiting for student messages (10 minutes)
    private var lastMessageTime: Long = 0
    private val MESSAGE_COOLDOWN_MS = 10 * 60 * 1000L // 10 minutes in milliseconds

    private val _messageError = MutableStateFlow<String?>(null)
    val messageError: StateFlow<String?> = _messageError.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = UniversityRepository(database)

        // Ensure default users exist (in case database callback didn't run)
        viewModelScope.launch {
            repository.ensureDefaultUsersExist()
        }

        // Observe all courses
        viewModelScope.launch {
            repository.getAllCourses().collect { coursesList ->
                _courses.value = coursesList
            }
        }

        // Observe all messages
        viewModelScope.launch {
            repository.getAllMessages().collect { messagesList ->
                _messages.value = messagesList
            }
        }
    }

    // Authentication
    fun login(userId: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            // Ensure default users exist before attempting login
            repository.ensureDefaultUsersExist()

            val user = repository.login(userId, password)
            _currentUser.value = user

            // Return result IMMEDIATELY - don't wait for teacher courses
            onResult(user)

            // Load teacher-specific courses AFTER returning result (non-blocking)
            if (user?.role == UserRole.TEACHER) {
                viewModelScope.launch {
                    repository.getCoursesByTeacher(userId).collect { teacherCoursesList ->
                        _teacherCourses.value = teacherCoursesList
                    }
                }
            }
        }
    }

    fun logout() {
        _currentUser.value = null
        _teacherCourses.value = emptyList()
    }

    // Course operations
    fun addCourse(course: Course, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.insertCourse(course)
            onSuccess()
        }
    }

    fun updateCourse(course: Course, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.updateCourse(course)
            onSuccess()
        }
    }

    fun deleteCourse(course: Course, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.deleteCourse(course)
            onSuccess()
        }
    }

    // Attendance operations
    fun loadAttendanceForCourse(courseId: Long) {
        viewModelScope.launch {
            repository.getAttendanceByCourse(courseId).collect { records ->
                _attendanceRecords.value = records
            }
        }
    }

    fun recordAttendance(record: AttendanceRecord) {
        viewModelScope.launch {
            repository.insertAttendance(record)
        }
    }

    // Message operations
    fun sendMessage(messageText: String, isAnnouncement: Boolean, onError: ((String) -> Unit)? = null) {
        val user = _currentUser.value ?: return

        // Check rate limiting for students only
        if (user.role == UserRole.STUDENT) {
            val currentTime = System.currentTimeMillis()
            val timeSinceLastMessage = currentTime - lastMessageTime

            if (lastMessageTime > 0 && timeSinceLastMessage < MESSAGE_COOLDOWN_MS) {
                val remainingMinutes = ((MESSAGE_COOLDOWN_MS - timeSinceLastMessage) / 60000).toInt() + 1
                val errorMsg = "Please wait $remainingMinutes more minute(s) before sending another message"
                _messageError.value = errorMsg
                onError?.invoke(errorMsg)
                return
            }
        }

        viewModelScope.launch {
            val message = Message(
                userId = user.id,
                userName = user.name,
                userRole = user.role,
                message = messageText,
                isAnnouncement = isAnnouncement && user.role == UserRole.TEACHER
            )
            repository.sendMessage(message)

            // Update last message time for students
            if (user.role == UserRole.STUDENT) {
                lastMessageTime = System.currentTimeMillis()
            }

            // Clear any previous error
            _messageError.value = null
        }
    }

    fun clearMessageError() {
        _messageError.value = null
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            repository.deleteMessage(message)
        }
    }
}

