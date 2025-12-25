package com.example.yearprojectmobile.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yearprojectmobile.data.Course
import androidx.core.graphics.toColorInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    course: Course,
    onBack: () -> Unit,
    onDelete: (Course) -> Unit,
    onEdit: (Course) -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Course Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showEditDialog = true }) {
                        Icon(Icons.Default.Edit, "Edit Course")
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, "Delete Course")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(course.color.toColorInt()).copy(alpha = 0.1f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = course.courseCode,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(course.color.toColorInt())
                    )
                    Text(
                        text = course.courseName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Course Information", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    HorizontalDivider()
                    DetailRow(label = "Course Code", value = course.courseCode)
                    DetailRow(label = "Course Name", value = course.courseName)
                    DetailRow(label = "Credits", value = "${course.credits}")
                    DetailRow(label = "Instructor", value = course.instructor)
                    DetailRow(label = "Schedule", value = course.schedule)
                    DetailRow(label = "Description", value = course.description)
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Enrollment", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    HorizontalDivider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        EnrollmentStat(label = "Enrolled", value = "${course.enrolledStudents}", icon = Icons.Default.Person)
                        EnrollmentStat(label = "Capacity", value = "${course.capacity}", icon = Icons.Default.People)
                        EnrollmentStat(label = "Available", value = "${course.capacity - course.enrolledStudents}", icon = Icons.Default.EventSeat)
                    }
                    LinearProgressIndicator(
                        progress = { course.enrolledStudents.toFloat() / course.capacity },
                        modifier = Modifier.fillMaxWidth(),
                        color = if (course.enrolledStudents >= course.capacity) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

    if (showEditDialog) {
        EditCourseDialog(course = course, onDismiss = { showEditDialog = false }, onConfirm = { updatedCourse ->
            onEdit(updatedCourse)
            showEditDialog = false
        })
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Course") },
            text = { Text("Are you sure you want to delete ${course.courseName}? This action cannot be undone.") },
            confirmButton = {
                Button(onClick = { onDelete(course); showDeleteDialog = false }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text("Delete")
                }
            },
            dismissButton = { TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") } }
        )
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
    }
}

@Composable
fun EnrollmentStat(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.primary)
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseDialog(onDismiss: () -> Unit, onConfirm: (Course) -> Unit) {
    var courseCode by remember { mutableStateOf("") }
    var courseName by remember { mutableStateOf("") }
    var credits by remember { mutableStateOf("") }
    var schedule by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Course") },
        text = {
            Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = courseCode, onValueChange = { courseCode = it }, label = { Text("Course Code") }, placeholder = { Text("e.g., CS401") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = courseName, onValueChange = { courseName = it }, label = { Text("Course Name") }, placeholder = { Text("e.g., Advanced Programming") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = credits, onValueChange = { credits = it }, label = { Text("Credits") }, placeholder = { Text("e.g., 3") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = schedule, onValueChange = { schedule = it }, label = { Text("Schedule") }, placeholder = { Text("e.g., Mon, Wed • 09:00-10:30") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = capacity, onValueChange = { capacity = it }, label = { Text("Capacity") }, placeholder = { Text("e.g., 50") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, placeholder = { Text("Course description...") }, modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 5)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (courseCode.isNotBlank() && courseName.isNotBlank()) {
                        val newCourse = Course(
                            id = System.currentTimeMillis().toString(),
                            courseCode = courseCode,
                            courseName = courseName,
                            credits = credits.toIntOrNull() ?: 3,
                            instructor = com.example.yearprojectmobile.data.MockData.currentUser?.name ?: "Unknown",
                            instructorId = com.example.yearprojectmobile.data.MockData.currentUser?.id ?: "",
                            schedule = schedule,
                            description = description,
                            capacity = capacity.toIntOrNull() ?: 30,
                            enrolledStudents = 0,
                            color = "#3557D5"
                        )
                        onConfirm(newCourse)
                    }
                },
                enabled = courseCode.isNotBlank() && courseName.isNotBlank()
            ) { Text("Add Course") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCourseDialog(course: Course, onDismiss: () -> Unit, onConfirm: (Course) -> Unit) {
    var courseCode by remember { mutableStateOf(course.courseCode) }
    var courseName by remember { mutableStateOf(course.courseName) }
    var credits by remember { mutableStateOf(course.credits.toString()) }
    var schedule by remember { mutableStateOf(course.schedule) }
    var description by remember { mutableStateOf(course.description) }
    var capacity by remember { mutableStateOf(course.capacity.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Course") },
        text = {
            Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = courseCode, onValueChange = { courseCode = it }, label = { Text("Course Code") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = courseName, onValueChange = { courseName = it }, label = { Text("Course Name") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = credits, onValueChange = { credits = it }, label = { Text("Credits") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = schedule, onValueChange = { schedule = it }, label = { Text("Schedule") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = capacity, onValueChange = { capacity = it }, label = { Text("Capacity") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 5)
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedCourse = course.copy(courseCode = courseCode, courseName = courseName, credits = credits.toIntOrNull() ?: course.credits, schedule = schedule, description = description, capacity = capacity.toIntOrNull() ?: course.capacity)
                onConfirm(updatedCourse)
            }) { Text("Save Changes") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}

