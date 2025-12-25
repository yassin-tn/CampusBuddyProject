package com.example.yearprojectmobile.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Timetable : Screen("timetable")
    object Tasks : Screen("tasks")
    object Courses : Screen("courses")
    object Library : Screen("library")
    object Profile : Screen("profile")
    object ScanQR : Screen("scan_qr")
    object CampusMap : Screen("campus_map")
}

