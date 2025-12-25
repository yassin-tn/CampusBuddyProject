# Campus Buddy - Mobile Application

A comprehensive Android mobile application for university campus management, developed as a course project for Mobile Development.

## 📱 Features

### Student Features
- **Attendance Management**: QR code scanning for class attendance
- **Discussion Board**: Campus-wide discussion forum with rate limiting (10-minute cooldown)
- **Campus Map**: Interactive map showing all campus buildings and facilities
- **Schedule Management**: View and manage class schedules
- **Profile Management**: Update personal information and settings

### Teacher Features
- **Attendance Tracking**: View student attendance records
- **QR Code Generation**: Generate attendance QR codes for classes
- **Discussion Moderation**: Post without rate limits
- **Student Management**: View enrolled students

### Admin Features
- **User Management**: Manage student and teacher accounts
- **System Administration**: Full access to all features
- **Data Analytics**: View system-wide statistics

## 🛠 Technology Stack

### Frontend
- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit for native Android
- **Material 3**: Material Design components and theming
- **Navigation Component**: App navigation management

### Backend & Database
- **Firebase Firestore**: Real-time NoSQL cloud database
- **Firebase Authentication**: User authentication (currently using local auth)
- **Kotlin Coroutines**: Asynchronous programming
- **Flow**: Reactive data streams

### Additional Libraries
- **CameraX**: Camera access for QR scanning
- **ZXing**: QR code generation and scanning
- **Coil**: Image loading and caching
- **Room**: Local database (for offline support)

### Build System
- **Gradle (Kotlin DSL)**: Build automation
- **KSP**: Kotlin Symbol Processing

## 📋 Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK (API 24+)
- Git

## 🚀 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yassin-tn/CampusBuddyProject.git
   cd CampusBuddyProject
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Gradle**
   - Android Studio will automatically detect and sync Gradle
   - Wait for dependencies to download

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click "Run" (Shift+F10) or use the green play button

## 👤 Test Accounts

The application includes pre-configured test accounts for demonstration purposes. Account credentials are configured in the application source code for course evaluation.

- **Student Account**: Standard user with attendance and discussion features
- **Teacher Account**: Elevated privileges for QR generation and attendance tracking
- **Admin Account**: Full system access for user management

> **Note**: For security evaluation, test credentials are available in the source code documentation.

## 📂 Project Structure

```
YearProjectMobile/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/yearprojectmobile/
│   │   │   │   ├── data/           # Data models and repositories
│   │   │   │   ├── screens/        # UI screens (Compose)
│   │   │   │   ├── ui/             # UI components and theme
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/                # Resources (layouts, strings, etc.)
│   │   │   └── AndroidManifest.xml
│   │   └── test/                   # Unit tests
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml          # Dependency versions
├── build.gradle.kts                # Root build file
├── settings.gradle.kts
└── README.md
```

## 🔧 Configuration

### Firebase Setup (Optional - currently using local auth)
1. Create a Firebase project at https://console.firebase.google.com
2. Add an Android app to your Firebase project
3. Download `google-services.json`
4. Place it in `app/` directory
5. Update `build.gradle.kts` to enable Firebase

### Database Structure

The app uses Firestore with the following collections:
- **users**: User profiles (students, teachers, admins)
- **attendance**: Attendance records
- **discussions**: Discussion board messages
- **schedules**: Class schedules

## 🎯 Key Features Implementation

### Discussion Board Rate Limiting
- Students: 10-minute cooldown between messages
- Teachers/Admins: No rate limiting
- Messages display newest at bottom

### Campus Map
- Interactive building markers
- Color-coded by building type (Academic, Administrative, Library, etc.)
- Click markers for building details

### QR Code Attendance
- Teachers generate time-limited QR codes
- Students scan to mark attendance
- Real-time attendance tracking

## 🐛 Known Issues & Solutions

### Issue: Compilation errors with duplicate functions
**Solution**: Delete `CampusMapScreen.kt` and `CampusMapScreenNew.kt`, keep only `CampusMapScreenFixed.kt`

### Issue: ScanQRScreen null pointer errors
**Solution**: Already fixed - added null-safe operators (`?.` and `!!`)

### Issue: KSP version mismatch warnings
**Solution**: Update `kotlin` version to 2.1.0 in `libs.versions.toml`

## 📝 Development Notes

### Authentication Flow
Currently uses hardcoded credentials for demo purposes. In production:
1. Implement Firebase Authentication
2. Add password reset functionality
3. Implement secure token storage

### Database
- Local authentication with predefined users
- Firestore for discussion board and attendance
- Consider adding offline support with Room

## 🤝 Contributing

This is a course project. For academic integrity:
- Do not directly copy this code for your submissions
- Use as reference/learning material only
- Credit this repository if you use any code

## 📄 License

This project is developed as a university course project. All rights reserved.

## 👨‍💻 Author

**Yassin TN**
- GitHub: [@yassin-tn](https://github.com/yassin-tn)
- Project: Mobile Development Course

## 🙏 Acknowledgments

- Course instructor and teaching assistants
- Firebase documentation
- Jetpack Compose documentation
- Android developer community

---

**Note**: This is an educational project created for a Mobile Development course. It demonstrates concepts in Android development, Kotlin, Jetpack Compose, and Firebase integration.

