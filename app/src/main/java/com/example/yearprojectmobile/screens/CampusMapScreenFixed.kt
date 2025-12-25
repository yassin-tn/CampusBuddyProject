package com.example.yearprojectmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusMapScreenFixed(onBack: () -> Unit) {
    var selectedBuilding by remember { mutableStateOf<Building?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text("Campus Map") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Search, "Search")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(16.dp)
                    .background(
                        color = Color(0xFFE3E9F7),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🗺️",
                        fontSize = 80.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Campus Map View",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Interactive map will be displayed here",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BuildingMarker(
                    icon = Icons.Default.Computer,
                    color = Color(0xFF3557D5),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 40.dp, y = 60.dp),
                    onClick = {
                        selectedBuilding = Building(
                            name = "Computer Science Building",
                            code = "CS",
                            description = "Home to Computer Science and Software Engineering departments",
                            floors = 5,
                            facilities = listOf("Classrooms", "Computer Labs", "Research Labs", "Faculty Offices")
                        )
                    }
                )

                BuildingMarker(
                    icon = Icons.Default.Science,
                    color = Color(0xFF5065A8),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-40).dp, y = 80.dp),
                    onClick = {
                        selectedBuilding = Building(
                            name = "Mathematics Building",
                            code = "MATH",
                            description = "Mathematics and Statistics departments",
                            floors = 3,
                            facilities = listOf("Classrooms", "Seminar Rooms", "Research Labs")
                        )
                    }
                )

                BuildingMarker(
                    icon = Icons.Default.LocalLibrary,
                    color = Color(0xFF3C7C57),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = 20.dp),
                    onClick = {
                        selectedBuilding = Building(
                            name = "Central Library",
                            code = "LIB",
                            description = "Main university library with extensive collection",
                            floors = 4,
                            facilities = listOf("Study Rooms", "Computer Lab", "Archives", "Cafe")
                        )
                    }
                )

                BuildingMarker(
                    icon = Icons.Default.Restaurant,
                    color = Color(0xFFE67E22),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 60.dp, y = (-40).dp),
                    onClick = {
                        selectedBuilding = Building(
                            name = "Student Center",
                            code = "SC",
                            description = "Main dining hall and student activities",
                            floors = 2,
                            facilities = listOf("Dining Hall", "Lounge", "Recreation", "Wi-Fi")
                        )
                    }
                )

                BuildingMarker(
                    icon = Icons.Default.Engineering,
                    color = Color(0xFF9B59B6),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-50).dp, y = (-60).dp),
                    onClick = {
                        selectedBuilding = Building(
                            name = "Engineering Building",
                            code = "ENG",
                            description = "Engineering departments and workshops",
                            floors = 4,
                            facilities = listOf("Classrooms", "Workshops", "Labs")
                        )
                    }
                )

                BuildingMarker(
                    icon = Icons.Default.Restaurant,
                    color = Color(0xFF3498DB),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 40.dp),
                    onClick = {
                        selectedBuilding = Building(
                            name = "Campus Cafe",
                            code = "CAFE",
                            description = "Coffee shop and light snacks",
                            floors = 1,
                            facilities = listOf("Seating Area", "Wi-Fi")
                        )
                    }
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Legend",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LegendItem(Icons.Default.Computer, "Academic Buildings", Color(0xFF3557D5))
                    LegendItem(Icons.Default.LocalLibrary, "Library", Color(0xFF3C7C57))
                    LegendItem(Icons.Default.Restaurant, "Dining & Recreation", Color(0xFFE67E22))
                    LegendItem(Icons.Default.Engineering, "Engineering", Color(0xFF9B59B6))
                }
            }

            Text(
                text = "Buildings",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            BuildingCard(
                name = "Computer Science Building",
                code = "CS",
                description = "Computer Science and Engineering departments",
                icon = Icons.Default.Computer,
                color = Color(0xFF3557D5),
                onClick = {
                    selectedBuilding = Building(
                        name = "Computer Science Building",
                        code = "CS",
                        description = "Home to Computer Science and Software Engineering departments",
                        floors = 5,
                        facilities = listOf("Classrooms", "Computer Labs", "Research Labs", "Faculty Offices")
                    )
                }
            )

            BuildingCard(
                name = "Mathematics Building",
                code = "MATH",
                description = "Mathematics and Statistics departments",
                icon = Icons.Default.Science,
                color = Color(0xFF5065A8),
                onClick = {
                    selectedBuilding = Building(
                        name = "Mathematics Building",
                        code = "MATH",
                        description = "Mathematics and Statistics departments",
                        floors = 3,
                        facilities = listOf("Classrooms", "Seminar Rooms", "Research Labs")
                    )
                }
            )

            BuildingCard(
                name = "Central Library",
                code = "LIB",
                description = "Main university library with extensive collection",
                icon = Icons.Default.LocalLibrary,
                color = Color(0xFF3C7C57),
                onClick = {
                    selectedBuilding = Building(
                        name = "Central Library",
                        code = "LIB",
                        description = "Main university library with extensive collection",
                        floors = 4,
                        facilities = listOf("Study Rooms", "Computer Lab", "Archives", "Cafe")
                    )
                }
            )

            BuildingCard(
                name = "Student Center",
                code = "SC",
                description = "Main dining hall and student activities",
                icon = Icons.Default.Restaurant,
                color = Color(0xFFE67E22),
                onClick = {
                    selectedBuilding = Building(
                        name = "Student Center",
                        code = "SC",
                        description = "Main dining hall and student activities",
                        floors = 2,
                        facilities = listOf("Dining Hall", "Lounge", "Recreation", "Wi-Fi")
                    )
                }
            )

            BuildingCard(
                name = "Engineering Building",
                code = "ENG",
                description = "Engineering departments and workshops",
                icon = Icons.Default.Engineering,
                color = Color(0xFF9B59B6),
                onClick = {
                    selectedBuilding = Building(
                        name = "Engineering Building",
                        code = "ENG",
                        description = "Engineering departments and workshops",
                        floors = 4,
                        facilities = listOf("Classrooms", "Workshops", "Labs")
                    )
                }
            )

            BuildingCard(
                name = "Campus Cafe",
                code = "CAFE",
                description = "Coffee shop and light snacks",
                icon = Icons.Default.Restaurant,
                color = Color(0xFF3498DB),
                onClick = {
                    selectedBuilding = Building(
                        name = "Campus Cafe",
                        code = "CAFE",
                        description = "Coffee shop and light snacks",
                        floors = 1,
                        facilities = listOf("Seating Area", "Wi-Fi")
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    selectedBuilding?.let { building ->
        AlertDialog(
            onDismissRequest = { selectedBuilding = null },
            title = {
                Column {
                    Text(
                        text = building.name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = building.code,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            text = {
                Column {
                    Text(
                        text = building.description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Layers,
                            contentDescription = null,
                            tint = Color(0xFF3557D5),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${building.floors} Floors",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Facilities",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    building.facilities.forEach { facility ->
                        Row(
                            modifier = Modifier.padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF3557D5))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = facility,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedBuilding = null },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3557D5)
                    )
                ) {
                    Text("Close")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun BuildingMarker(
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun LegendItem(icon: ImageVector, label: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun BuildingCard(
    name: String,
    code: String,
    description: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = code,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(color, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class Building(
    val name: String,
    val code: String,
    val description: String,
    val floors: Int,
    val facilities: List<String>
)

