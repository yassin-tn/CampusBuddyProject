package com.example.yearprojectmobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserEntity::class,
        CourseEntity::class,
        AttendanceEntity::class,
        MessageEntity::class
    ],
    version = 4,  // Force fresh database - fixed teacher login blocking issue
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun courseDao(): CourseDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "university_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // Ensure users exist every time database opens
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    ensureUsersExist(database)
                }
            }
        }

        suspend fun ensureUsersExist(database: AppDatabase) {
            val userDao = database.userDao()

            // Check and insert student if missing
            if (userDao.getUserById("2021CS042") == null) {
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

            // Check and insert teachers if missing
            if (userDao.getUserById("PROF001") == null) {
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

            if (userDao.getUserById("PROF002") == null) {
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

            if (userDao.getUserById("PROF003") == null) {
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

        suspend fun populateDatabase(database: AppDatabase) {
            val userDao = database.userDao()
            val courseDao = database.courseDao()
            val messageDao = database.messageDao()

            // Insert default users
            userDao.insertUser(
                UserEntity(
                    id = "2021CS042",
                    password = "student123",
                    name = "Sarah Johnson",
                    email = "sarah.johnson@university.edu",
                    role = "STUDENT"
                )
            )

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

            // Insert default courses
            courseDao.insertCourse(
                CourseEntity(
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
                )
            )

            courseDao.insertCourse(
                CourseEntity(
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
                )
            )

            courseDao.insertCourse(
                CourseEntity(
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
                )
            )

            courseDao.insertCourse(
                CourseEntity(
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
                )
            )

            courseDao.insertCourse(
                CourseEntity(
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
                )
            )

            courseDao.insertCourse(
                CourseEntity(
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

            // Insert welcome message
            messageDao.insertMessage(
                MessageEntity(
                    userId = "PROF001",
                    userName = "Dr. Robert Smith",
                    userRole = "TEACHER",
                    message = "Welcome to the new semester! Please check the course catalog and enroll in your classes.",
                    isAnnouncement = true
                )
            )
        }
    }
}

