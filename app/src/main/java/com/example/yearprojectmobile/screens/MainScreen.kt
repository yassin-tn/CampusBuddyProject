package com.example.yearprojectmobile.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yearprojectmobile.viewmodel.ThemeViewModel
import com.example.yearprojectmobile.viewmodel.UniversityViewModel

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Timetable : BottomNavItem("timetable", Icons.Default.DateRange, "Timetable")
    object Tasks : BottomNavItem("tasks", Icons.Default.CheckCircle, "Tasks")
    object Courses : BottomNavItem("courses", Icons.Default.MenuBook, "Courses")
    object Discussion : BottomNavItem("discussion", Icons.Default.Forum, "Discussion")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: androidx.navigation.NavHostController,
    themeViewModel: ThemeViewModel,
    onSignOut: () -> Unit = {}
) {
    val universityViewModel: UniversityViewModel = viewModel()
    val currentUser by universityViewModel.currentUser.collectAsState()
    val messages by universityViewModel.messages.collectAsState()
    val messageError by universityViewModel.messageError.collectAsState()

    var selectedTab by remember { mutableStateOf(0) }
    var showProfileScreen by remember { mutableStateOf(false) }
    var showScanQRScreen by remember { mutableStateOf(false) }
    var showCampusMapScreen by remember { mutableStateOf(false) }

    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Timetable,
        BottomNavItem.Tasks,
        BottomNavItem.Courses,
        BottomNavItem.Discussion
    )

    if (showProfileScreen) {
        ProfileScreen(
            onBack = { showProfileScreen = false },
            onSignOut = {
                showProfileScreen = false
                universityViewModel.logout()
                onSignOut()
            },
            currentUser = currentUser
        )
    } else if (showScanQRScreen) {
        ScanQRScreen(
            onBack = { showScanQRScreen = false },
            onScanComplete = { code ->
                showScanQRScreen = false
                // Handle scan complete
            }
        )
    } else if (showCampusMapScreen) {
        UniversityCampusMap(
            onBack = { showCampusMapScreen = false }
        )
    } else {
        Scaffold(
            topBar = {
                if (selectedTab == 0) {
                    TopAppBar(
                        title = { Text("Home") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        actions = {
                            IconButton(onClick = { themeViewModel.toggleTheme() }) {
                                Icon(
                                    imageVector = if (themeViewModel.isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                                    contentDescription = "Toggle Theme"
                                )
                            }
                            Badge(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                IconButton(onClick = { /* Handle notifications */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Notifications,
                                        contentDescription = "Notifications"
                                    )
                                }
                            }
                            IconButton(onClick = { showProfileScreen = true }) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "Profile"
                                )
                            }
                        }
                    )
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    tabs.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (selectedTab) {
                    0 -> HomeScreen(
                        onScanQRClick = { showScanQRScreen = true },
                        onCampusMapClick = { showCampusMapScreen = true }
                    )
                    1 -> TimetableScreen()
                    2 -> TasksScreen()
                    3 -> CoursesScreen()
                    4 -> DiscussionScreen(
                        messages = messages,
                        currentUserId = currentUser?.id ?: "",
                        currentUserName = currentUser?.name ?: "Guest",
                        currentUserRole = currentUser?.role ?: com.example.yearprojectmobile.data.UserRole.STUDENT,
                        rateLimitError = messageError,
                        onSendMessage = { message, isAnnouncement ->
                            universityViewModel.sendMessage(message, isAnnouncement) { }
                        },
                        onClearError = {
                            universityViewModel.clearMessageError()
                        }
                    )
                }
            }
        }
    }
}

