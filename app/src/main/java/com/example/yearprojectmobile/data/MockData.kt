package com.example.yearprojectmobile.data

import androidx.compose.runtime.mutableStateListOf

object MockData {

    // Login credentials
    const val DEFAULT_STUDENT_ID = "2021CS042"
    const val DEFAULT_PASSWORD = "student123"
    const val DEFAULT_TEACHER_ID = "PROF001"
    const val DEFAULT_TEACHER_PASSWORD = "teacher123"

    // Current logged in user
    var currentUser: User? = null

    // Users (Students and Teachers)
    val users = listOf(
        User(
            id = "2021CS042",
            password = "student123",
            name = "Sarah Johnson",
            email = "sarah.johnson@university.edu",
            role = UserRole.STUDENT
        ),
        User(
            id = "PROF001",
            password = "teacher123",
            name = "Dr. Robert Smith",
            email = "robert.smith@university.edu",
            role = UserRole.TEACHER
        ),
        User(
            id = "PROF002",
            password = "teacher123",
            name = "Dr. Sarah Chen",
            email = "sarah.chen@university.edu",
            role = UserRole.TEACHER
        ),
        User(
            id = "PROF003",
            password = "teacher123",
            name = "Prof. Michael Brown",
            email = "michael.brown@university.edu",
            role = UserRole.TEACHER
        )
    )

    // Teachers
    val teachers = mutableStateListOf(
        Teacher(
            id = "PROF001",
            name = "Dr. Robert Smith",
            email = "robert.smith@university.edu",
            department = "Computer Science",
            coursesTaught = listOf("CS301")
        ),
        Teacher(
            id = "PROF002",
            name = "Dr. Sarah Chen",
            email = "sarah.chen@university.edu",
            department = "Computer Science",
            coursesTaught = listOf("CS302")
        ),
        Teacher(
            id = "PROF003",
            name = "Prof. Michael Brown",
            email = "michael.brown@university.edu",
            department = "Computer Science",
            coursesTaught = listOf("CS303")
        )
    )

    // Full Course List (with management capabilities)
    val fullCourses = mutableStateListOf(
        Course(
            id = "1",
            courseCode = "CS301",
            courseName = "Data Structures & Algorithms",
            credits = 4,
            instructor = "Dr. Robert Smith",
            instructorId = "PROF001",
            schedule = "Mon, Wed • 09:00-10:30",
            description = "Advanced data structures and algorithm analysis",
            capacity = 50,
            enrolledStudents = 42,
            color = "#3557D5"
        ),
        Course(
            id = "2",
            courseCode = "CS302",
            courseName = "Operating Systems",
            credits = 4,
            instructor = "Dr. Sarah Chen",
            instructorId = "PROF002",
            schedule = "Tue, Thu • 10:00-11:30",
            description = "Operating system concepts and design",
            capacity = 45,
            enrolledStudents = 38,
            color = "#3557D5"
        ),
        Course(
            id = "3",
            courseCode = "CS303",
            courseName = "Database Management",
            credits = 3,
            instructor = "Prof. Michael Brown",
            instructorId = "PROF003",
            schedule = "Wed, Fri • 13:00-14:30",
            description = "Database design and SQL",
            capacity = 40,
            enrolledStudents = 35,
            color = "#3557D5"
        ),
        Course(
            id = "4",
            courseCode = "MATH202",
            courseName = "Linear Algebra",
            credits = 3,
            instructor = "Prof. Emily Johnson",
            instructorId = "PROF004",
            schedule = "Mon, Wed • 11:00-12:30",
            description = "Vector spaces and linear transformations",
            capacity = 60,
            enrolledStudents = 54,
            color = "#5065A8"
        ),
        Course(
            id = "5",
            courseCode = "ENG101",
            courseName = "Technical Writing",
            credits = 2,
            instructor = "Dr. Williams",
            instructorId = "PROF005",
            schedule = "Tue • 14:00-15:30",
            description = "Professional and technical communication",
            capacity = 30,
            enrolledStudents = 25,
            color = "#3C7C57"
        ),
        Course(
            id = "6",
            courseCode = "CS304",
            courseName = "Web Development",
            credits = 3,
            instructor = "Dr. Lisa Anderson",
            instructorId = "PROF006",
            schedule = "Thu, Fri • 10:00-11:30",
            description = "Modern web technologies and frameworks",
            capacity = 35,
            enrolledStudents = 30,
            color = "#5065A8"
        )
    )

    // Attendance Records
    val attendanceRecords = mutableStateListOf(
        AttendanceRecord(
            id = "ATT001",
            studentId = "2021CS042",
            studentName = "Sarah Johnson",
            courseCode = "CS301",
            date = "2024-12-18",
            status = AttendanceStatus.PRESENT,
            scannedAt = "09:05"
        ),
        AttendanceRecord(
            id = "ATT002",
            studentId = "2021CS042",
            studentName = "Sarah Johnson",
            courseCode = "CS302",
            date = "2024-12-18",
            status = AttendanceStatus.LATE,
            scannedAt = "10:15"
        )
    )

    // Student Info
    const val STUDENT_NAME = "Sarah Johnson"
    const val STUDENT_ID = "2021CS042"

    // Today's classes
    val todaysClasses = listOf(
        ClassItem(
            courseCode = "CS301",
            courseName = "Data Structures & Algorithms",
            time = "09:00 - 10:30",
            room = "Room A301",
            instructor = "Dr. Robert Smith",
            color = "#3557D5"
        ),
        ClassItem(
            courseCode = "MATH202",
            courseName = "Linear Algebra",
            time = "11:00 - 12:30",
            room = "Room B205",
            instructor = "Prof. Emily Johnson",
            color = "#5065A8"
        ),
        ClassItem(
            courseCode = "ENG101",
            courseName = "Technical Writing",
            time = "14:00 - 15:30",
            room = "Room C102",
            instructor = "Dr. Williams",
            color = "#3C7C57"
        )
    )

    // Upcoming tasks
    val upcomingTasks = listOf(
        TaskItem(
            title = "Data Structures Assignment 3",
            courseCode = "CS301",
            courseColor = "#3557D5",
            dueDate = "Due Tomorrow",
            dueTime = "at 23:59"
        ),
        TaskItem(
            title = "Linear Algebra Quiz",
            courseCode = "MATH202",
            courseColor = "#5065A8",
            dueDate = "Due Mar 20",
            dueTime = "at 09:00"
        )
    )

    // All tasks
    val allTasks = listOf(
        TaskItem(
            title = "Data Structures Assignment 3",
            courseCode = "CS301",
            courseColor = "#3557D5",
            dueDate = "Due Tomorrow",
            dueTime = "at 23:59"
        ),
        TaskItem(
            title = "Linear Algebra Quiz Preparation",
            courseCode = "MATH202",
            courseColor = "#5065A8",
            dueDate = "Due Mar 20",
            dueTime = "at 09:00"
        ),
        TaskItem(
            title = "Technical Writing Essay Draft",
            courseCode = "ENG101",
            courseColor = "#3C7C57",
            dueDate = "Due Mar 18",
            dueTime = "at 17:00"
        ),
        TaskItem(
            title = "OS Lab Report 2",
            courseCode = "CS302",
            courseColor = "#3557D5",
            dueDate = "Yesterday",
            dueTime = "at 23:59",
            isOverdue = true
        ),
        TaskItem(
            title = "Database Schema Design",
            courseCode = "CS303",
            courseColor = "#3557D5",
            dueDate = "Due Mar 25",
            dueTime = "at 23:59"
        ),
        TaskItem(
            title = "Web Development Project",
            courseCode = "CS304",
            courseColor = "#5065A8",
            dueDate = "Due Apr 1",
            dueTime = "at 23:59"
        )
    )

    // Courses
    val courses = listOf(
        CourseItem(
            courseCode = "CS301",
            courseName = "Data Structures & Algorithms",
            credits = 4,
            instructor = "Dr. Robert Smith",
            schedule = "Mon, Wed • 09:00-10:30",
            progress = 0.75f,
            attendance = 0.92f,
            pendingTasks = 2,
            currentGrade = "A",
            color = "#3557D5"
        ),
        CourseItem(
            courseCode = "CS302",
            courseName = "Operating Systems",
            credits = 4,
            instructor = "Dr. Sarah Chen",
            schedule = "Tue, Thu • 10:00-11:30",
            progress = 0.68f,
            attendance = 0.88f,
            pendingTasks = 1,
            currentGrade = "A-",
            color = "#3557D5"
        ),
        CourseItem(
            courseCode = "CS303",
            courseName = "Database Management",
            credits = 3,
            instructor = "Prof. Michael Brown",
            schedule = "Wed, Fri • 13:00-14:30",
            progress = 0.82f,
            attendance = 0.95f,
            pendingTasks = 1,
            currentGrade = "A+",
            color = "#3557D5"
        ),
        CourseItem(
            courseCode = "MATH202",
            courseName = "Linear Algebra",
            credits = 3,
            instructor = "Prof. Emily Johnson",
            schedule = "Mon, Wed • 11:00-12:30",
            progress = 0.70f,
            attendance = 0.90f,
            pendingTasks = 1,
            currentGrade = "B+",
            color = "#5065A8"
        ),
        CourseItem(
            courseCode = "ENG101",
            courseName = "Technical Writing",
            credits = 2,
            instructor = "Dr. Williams",
            schedule = "Tue • 14:00-15:30",
            progress = 0.65f,
            attendance = 0.85f,
            pendingTasks = 1,
            currentGrade = "A-",
            color = "#3C7C57"
        ),
        CourseItem(
            courseCode = "CS304",
            courseName = "Web Development",
            credits = 3,
            instructor = "Dr. Lisa Anderson",
            schedule = "Thu, Fri • 10:00-11:30",
            progress = 0.55f,
            attendance = 0.92f,
            pendingTasks = 1,
            currentGrade = "A",
            color = "#5065A8"
        )
    )

    // Borrowed books
    val borrowedBooks = listOf(
        BookItem(
            title = "Introduction to Algorithms",
            author = "Thomas H. Cormen",
            isbn = "978-0262033848",
            borrowedDate = "Feb 15, 2024",
            dueDate = "Mar 15, 2024",
            daysRemaining = 4
        ),
        BookItem(
            title = "Clean Code",
            author = "Robert C. Martin",
            isbn = "978-0132350884",
            borrowedDate = "Feb 20, 2024",
            dueDate = "Mar 20, 2024",
            daysRemaining = 9
        ),
        BookItem(
            title = "Design Patterns",
            author = "Erich Gamma",
            isbn = "978-0201633612",
            borrowedDate = "Jan 25, 2024",
            dueDate = "Mar 8, 2024",
            daysRemaining = -3,
            isOverdue = true
        )
    )

    // Announcements
    val announcements = listOf(
        Announcement(
            title = "Semester Registration Open",
            content = "Fall 2024 registration starts next week. Check your advisor for course recommendations.",
            time = "2 hours ago"
        ),
        Announcement(
            title = "Library Extended Hours",
            content = "The library will be open 24/7 during exam week.",
            time = "1 day ago"
        )
    )

    // Timetable for the week
    val weekSchedule = mapOf(
        "Mon" to listOf(
            ClassItem("CS301", "Data Structures", "09:00 - 10:30", "Room A301", "Dr. Smith", "#3557D5"),
            ClassItem("MATH202", "Linear Algebra", "11:00 - 12:30", "Room B205", "Prof. Johnson", "#5065A8")
        ),
        "Tue" to listOf(
            ClassItem("CS302", "Operating Systems", "10:00 - 11:30", "Room A205", "Dr. Chen", "#3557D5"),
            ClassItem("ENG101", "Technical Writing", "14:00 - 15:30", "Room C102", "Dr. Williams", "#3C7C57")
        ),
        "Wed" to listOf(
            ClassItem("CS301", "Data Structures", "09:00 - 10:30", "Room A301", "Dr. Smith", "#3557D5"),
            ClassItem("MATH202", "Linear Algebra", "11:00 - 12:30", "Room B205", "Prof. Johnson", "#5065A8"),
            ClassItem("CS303", "Database Management", "13:00 - 14:30", "Room A401", "Prof. Brown", "#3557D5")
        ),
        "Thu" to listOf(
            ClassItem("CS302", "Operating Systems", "10:00 - 11:30", "Room A205", "Dr. Chen", "#3557D5"),
            ClassItem("CS304", "Web Development", "10:00 - 11:30", "Room B301", "Dr. Anderson", "#5065A8")
        ),
        "Fri" to listOf(
            ClassItem("CS303", "Database Management", "13:00 - 14:30", "Room A401", "Prof. Brown", "#3557D5"),
            ClassItem("CS304", "Web Development", "10:00 - 11:30", "Room B301", "Dr. Anderson", "#5065A8")
        )
    )
}

