package com.example.yearprojectmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanQRScreen(onBack: () -> Unit, onScanComplete: (String) -> Unit) {
    var showManualEntry by remember { mutableStateOf(false) }
    var manualCode by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var scannedData by remember { mutableStateOf<ScannedAttendance?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("Scan QR Code") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        if (showManualEntry) {
            // Manual Code Entry Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Pin,
                    contentDescription = "Enter Code",
                    tint = Color(0xFF3557D5),
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Enter Attendance Code",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Enter the 6-digit code provided by your instructor",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = manualCode,
                    onValueChange = {
                        if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                            manualCode = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("6-Digit Code") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF3557D5),
                        focusedLabelColor = Color(0xFF3557D5)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (manualCode.length == 6) {
                            // Simulate attendance recording
                            scannedData = ScannedAttendance(
                                courseCode = "CS301",
                                courseName = "Data Structures",
                                sessionType = "Lecture",
                                room = "Room A301",
                                date = "March 10, 2024",
                                time = "09:00 AM",
                                instructor = "Dr. Robert Smith"
                            )
                            showSuccessDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = manualCode.length == 6,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3557D5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit Code",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { showManualEntry = false }) {
                    Text("Scan QR Code Instead")
                }
            }
        } else {
            // QR Scanner Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // QR Scanner Frame
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .background(
                            color = Color(0xFFE3E9F7),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Corner brackets to simulate scanner frame
                        Box(
                            modifier = Modifier.size(200.dp)
                        ) {
                            // Top-left corner
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 4.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.TopStart)
                            )
                            Box(
                                modifier = Modifier
                                    .size(4.dp, 40.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.TopStart)
                            )

                            // Top-right corner
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 4.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.TopEnd)
                            )
                            Box(
                                modifier = Modifier
                                    .size(4.dp, 40.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.TopEnd)
                            )

                            // Bottom-left corner
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 4.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.BottomStart)
                            )
                            Box(
                                modifier = Modifier
                                    .size(4.dp, 40.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.BottomStart)
                            )

                            // Bottom-right corner
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 4.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.BottomEnd)
                            )
                            Box(
                                modifier = Modifier
                                    .size(4.dp, 40.dp)
                                    .background(Color(0xFF3557D5))
                                    .align(Alignment.BottomEnd)
                            )

                            // Camera icon in center
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Camera",
                                tint = Color(0xFF3557D5).copy(alpha = 0.3f),
                                modifier = Modifier
                                    .size(60.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = "QR Code",
                    tint = Color(0xFF3557D5),
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Position QR Code",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Align the QR code within the frame to scan for attendance",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Simulate scan button (for demo)
                Button(
                    onClick = {
                        scannedData = ScannedAttendance(
                            courseCode = "CS301",
                            courseName = "Data Structures",
                            sessionType = "Lecture",
                            room = "Room A301",
                            date = "March 10, 2024",
                            time = "09:00 AM",
                            instructor = "Dr. Robert Smith"
                        )
                        showSuccessDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3557D5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Scan QR Code",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { showManualEntry = true }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Enter Code Manually")
                    }
                }
            }
        }
    }

    // Success Dialog
    if (showSuccessDialog && scannedData != null) {
        val attendanceData = scannedData!!
        AlertDialog(
            onDismissRequest = { },
            icon = {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF3C7C57).copy(alpha = 0.1f), shape = RoundedCornerShape(40.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success",
                        tint = Color(0xFF3C7C57),
                        modifier = Modifier.size(48.dp)
                    )
                }
            },
            title = {
                Text(
                    text = "Attendance Recorded!",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                Column {
                    Text(
                        text = "Your attendance has been successfully marked",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    AttendanceDetailRow("Course", attendanceData.courseCode)
                    AttendanceDetailRow("Class", attendanceData.courseName)
                    AttendanceDetailRow("Session Type", attendanceData.sessionType)
                    AttendanceDetailRow("Room", attendanceData.room)
                    AttendanceDetailRow("Date", attendanceData.date)
                    AttendanceDetailRow("Time", attendanceData.time)
                    AttendanceDetailRow("Instructor", attendanceData.instructor)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        manualCode = ""
                        onBack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3557D5)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Done")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun AttendanceDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

data class ScannedAttendance(
    val courseCode: String,
    val courseName: String,
    val sessionType: String,
    val room: String,
    val date: String,
    val time: String,
    val instructor: String
)

