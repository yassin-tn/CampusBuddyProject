package com.example.yearprojectmobile.data

data class ClassItem(
    val courseCode: String,
    val courseName: String,
    val time: String,
    val room: String,
    val instructor: String,
    val color: String = "#3557D5"
)

data class TaskItem(
    val title: String,
    val courseCode: String,
    val courseColor: String,
    val dueDate: String,
    val dueTime: String? = null,
    val isOverdue: Boolean = false,
    val isCompleted: Boolean = false
)

data class CourseItem(
    val courseCode: String,
    val courseName: String,
    val credits: Int,
    val instructor: String,
    val schedule: String,
    val progress: Float,
    val attendance: Float,
    val pendingTasks: Int,
    val currentGrade: String,
    val color: String = "#3557D5"
)

data class BookItem(
    val title: String,
    val author: String,
    val isbn: String,
    val borrowedDate: String,
    val dueDate: String,
    val daysRemaining: Int,
    val isOverdue: Boolean = false
)

data class Announcement(
    val title: String,
    val content: String,
    val time: String
)

// User roles
enum class UserRole {
    STUDENT,
    TEACHER
}

// User data model
data class User(
    val id: String,
    val password: String,
    val name: String,
    val email: String,
    val role: UserRole
)

// Teacher specific data
data class Teacher(
    val id: String,
    val name: String,
    val email: String,
    val department: String,
    val coursesTaught: List<String> = emptyList()
)

// Course with extended info for teachers
data class Course(
    val id: String,
    val courseCode: String,
    val courseName: String,
    val credits: Int,
    val instructor: String,
    val instructorId: String,
    val schedule: String,
    val description: String,
    val capacity: Int,
    val enrolledStudents: Int,
    val color: String = "#3557D5"
)

// Student attendance record
data class AttendanceRecord(
    val id: String,
    val studentId: String,
    val studentName: String,
    val courseCode: String,
    val date: String,
    val status: AttendanceStatus,
    val scannedAt: String? = null
)

enum class AttendanceStatus {
    PRESENT,
    ABSENT,
    LATE,
    EXCUSED
}

