package com.example.yearprojectmobile.repository

import com.example.yearprojectmobile.database.*
import com.example.yearprojectmobile.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UniversityRepository(private val database: AppDatabase) {

    private val userDao = database.userDao()
    private val courseDao = database.courseDao()
    private val attendanceDao = database.attendanceDao()
    private val messageDao = database.messageDao()

    // User operations
    suspend fun login(userId: String, password: String): User? {
        return userDao.login(userId, password)?.toUser()
    }

    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)?.toUser()
    }

    suspend fun getAllTeachers(): List<Teacher> {
        return userDao.getAllTeachers().map { it.toTeacher() }
    }

    // Ensure default users exist (call on app start)
    suspend fun ensureDefaultUsersExist() {
        // Check if student exists
        val student = userDao.getUserById("2021CS042")
        if (student == null) {
            userDao.insertUser(
                UserEntity(
                    id = "2021CS042",
                    password = "student123",
                    name = "Sarah Johnson",
                    email = "sarah.johnson@university.edu",
                    role = "STUDENT"
                )
            )
        }

        // Check if teachers exist
        val teacher1 = userDao.getUserById("PROF001")
        if (teacher1 == null) {
            userDao.insertUser(
                UserEntity(
                    id = "PROF001",
                    password = "teacher123",
                    name = "Dr. Robert Smith",
                    email = "robert.smith@university.edu",
                    role = "TEACHER",
                    department = "Computer Science"
                )
            )
        }

        val teacher2 = userDao.getUserById("PROF002")
        if (teacher2 == null) {
            userDao.insertUser(
                UserEntity(
                    id = "PROF002",
                    password = "teacher123",
                    name = "Dr. Sarah Chen",
                    email = "sarah.chen@university.edu",
                    role = "TEACHER",
                    department = "Computer Science"
                )
            )
        }

        val teacher3 = userDao.getUserById("PROF003")
        if (teacher3 == null) {
            userDao.insertUser(
                UserEntity(
                    id = "PROF003",
                    password = "teacher123",
                    name = "Prof. Michael Brown",
                    email = "michael.brown@university.edu",
                    role = "TEACHER",
                    department = "Computer Science"
                )
            )
        }
    }

    // Course operations
    fun getAllCourses(): Flow<List<Course>> {
        return courseDao.getAllCourses().map { courses ->
            courses.map { it.toCourse() }
        }
    }

    fun getCoursesByTeacher(teacherId: String): Flow<List<Course>> {
        return courseDao.getCoursesByTeacher(teacherId).map { courses ->
            courses.map { it.toCourse() }
        }
    }

    suspend fun insertCourse(course: Course): Long {
        return courseDao.insertCourse(course.toEntity())
    }

    suspend fun updateCourse(course: Course) {
        courseDao.updateCourse(course.toEntity())
    }

    suspend fun deleteCourse(course: Course) {
        val entity = courseDao.getCourseById(course.id.toLong())
        entity?.let { courseDao.deleteCourse(it) }
    }

    // Attendance operations
    fun getAttendanceByCourse(courseId: Long): Flow<List<AttendanceRecord>> {
        return attendanceDao.getAttendanceByCourse(courseId).map { records ->
            records.map { it.toAttendanceRecord() }
        }
    }

    fun getAttendanceByStudent(studentId: String): Flow<List<AttendanceRecord>> {
        return attendanceDao.getAttendanceByStudent(studentId).map { records ->
            records.map { it.toAttendanceRecord() }
        }
    }

    suspend fun insertAttendance(record: AttendanceRecord) {
        attendanceDao.insertAttendance(record.toEntity())
    }

    // Message operations
    fun getAllMessages(): Flow<List<Message>> {
        return messageDao.getAllMessages().map { messages ->
            messages.map { it.toMessage() }
        }
    }

    fun getAnnouncements(): Flow<List<Message>> {
        return messageDao.getAnnouncements().map { messages ->
            messages.map { it.toMessage() }
        }
    }

    suspend fun sendMessage(message: Message): Long {
        return messageDao.insertMessage(message.toEntity())
    }

    suspend fun deleteMessage(message: Message) {
        messageDao.deleteMessage(message.toEntity())
    }
}

// Extension functions for conversions
private fun UserEntity.toUser() = User(
    id = id,
    password = password,
    name = name,
    email = email,
    role = if (role == "TEACHER") UserRole.TEACHER else UserRole.STUDENT
)

private fun UserEntity.toTeacher() = Teacher(
    id = id,
    name = name,
    email = email,
    department = department ?: "",
    coursesTaught = emptyList()
)

private fun CourseEntity.toCourse() = Course(
    id = id.toString(),
    courseCode = courseCode,
    courseName = courseName,
    credits = credits,
    instructor = instructor,
    instructorId = instructorId,
    schedule = schedule,
    description = description,
    capacity = capacity,
    enrolledStudents = enrolledStudents,
    color = color
)

private fun Course.toEntity() = CourseEntity(
    id = if (id.isNotEmpty()) id.toLong() else 0,
    courseCode = courseCode,
    courseName = courseName,
    credits = credits,
    instructor = instructor,
    instructorId = instructorId,
    schedule = schedule,
    description = description,
    capacity = capacity,
    enrolledStudents = enrolledStudents,
    color = color
)

private fun AttendanceEntity.toAttendanceRecord() = AttendanceRecord(
    id = id.toString(),
    studentId = studentId,
    studentName = studentName,
    courseCode = courseCode,
    date = date,
    status = AttendanceStatus.valueOf(status),
    scannedAt = scannedAt
)

private fun AttendanceRecord.toEntity() = AttendanceEntity(
    id = if (id.isNotEmpty()) id.toLong() else 0,
    studentId = studentId,
    studentName = studentName,
    courseId = 0, // Will be set by the system
    courseCode = courseCode,
    date = date,
    status = status.name,
    scannedAt = scannedAt
)

private fun MessageEntity.toMessage() = Message(
    id = id.toString(),
    userId = userId,
    userName = userName,
    userRole = if (userRole == "TEACHER") UserRole.TEACHER else UserRole.STUDENT,
    message = message,
    timestamp = timestamp,
    isAnnouncement = isAnnouncement
)

private fun Message.toEntity() = MessageEntity(
    id = if (id.isNotEmpty()) id.toLong() else 0,
    userId = userId,
    userName = userName,
    userRole = userRole.name,
    message = message,
    timestamp = timestamp,
    isAnnouncement = isAnnouncement
)

// New data class for messages
data class Message(
    val id: String = "",
    val userId: String,
    val userName: String,
    val userRole: UserRole,
    val message: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isAnnouncement: Boolean = false
)

