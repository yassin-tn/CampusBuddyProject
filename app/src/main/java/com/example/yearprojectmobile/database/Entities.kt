package com.example.yearprojectmobile.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val password: String,
    val name: String,
    val email: String,
    val role: String, // "STUDENT" or "TEACHER"
    val department: String? = null // For teachers
)

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val courseCode: String,
    val courseName: String,
    val credits: Int,
    val instructor: String,
    val instructorId: String,
    val schedule: String,
    val description: String,
    val capacity: Int,
    val enrolledStudents: Int = 0,
    val color: String = "#3557D5",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "attendance",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("studentId"), Index("courseId")]
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val studentId: String,
    val studentName: String,
    val courseId: Long,
    val courseCode: String,
    val date: String,
    val status: String, // "PRESENT", "ABSENT", "LATE", "EXCUSED"
    val scannedAt: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId"), Index("timestamp")]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val userName: String,
    val userRole: String, // "STUDENT" or "TEACHER"
    val message: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isAnnouncement: Boolean = false // True for teacher announcements
)

