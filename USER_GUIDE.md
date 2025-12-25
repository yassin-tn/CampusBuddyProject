# University Student Assistant App - Quick Start Guide

## 🎓 Application Overview

This is a comprehensive mobile application for university students and teachers to manage their academic activities, communicate, and access campus resources.

## 🔐 Login Credentials

### Student Account:
- **User ID**: `2021CS042`
- **Password**: `student123`
- **Name**: Sarah Johnson

### Teacher Accounts:
- **User ID**: `PROF001`
- **Password**: `teacher123`
- **Name**: Dr. Robert Smith

- **User ID**: `PROF002`
- **Password**: `teacher123`
- **Name**: Dr. Sarah Chenàà

## 📱 Main Features

### 1. Home Screen
- Quick access to all features
- Scan QR Code for attendance
- View campus map
- Recent notifications

### 2. Timetable
- View your class schedule
- See course timings
- Organized by days of the week

### 3. Tasks
- Manage assignments
- Track deadlines
- Mark tasks as complete

### 4. Courses
- Browse available courses
- View course details
- See instructor information
- Check enrolled students count

### 5. Discussion Board ✅ **FULLY FUNCTIONAL**
- **Send messages** to all users
- **View messages** from students and teachers
- **Real-time updates** - messages appear instantly
- **Persistent storage** - messages saved in database
- **Teacher announcements** - highlighted in red
- **Auto-scroll** to latest messages
- **Timestamps** on all messages

## 💬 How to Use the Discussion Board

### Sending a Message:

1. **Login** to the app with your credentials
2. Tap on the **Discussion** tab in the bottom navigation
3. Type your message in the text field at the bottom
4. **For Teachers**: Toggle the announcement switch to send as official announcement
5. Tap the **Send** button
6. Your message appears immediately in the chat

### Viewing Messages:

- All messages are displayed in chronological order
- Your messages appear on the right (blue background)
- Other users' messages appear on the left (gray background)
- Teacher messages have a star icon ⭐
- Announcements are highlighted in red with a notification icon 🔔

### Message Features:

- **Sender Name**: Displayed above each message
- **Timestamp**: Shows when the message was sent
- **Role Indicator**: Teachers have a star icon
- **Announcement Badge**: Official announcements are clearly marked
- **Auto-scroll**: Automatically scrolls to newest message

## 🗺️ Campus Map

- Interactive campus map showing all buildings
- Color-coded buildings:
  - 🏛️ Academic buildings (Blue)
  - 📚 Library (Green)
  - 🍽️ Cafeteria (Orange)
  - 🏃 Sports facilities (Red)
  - 🏠 Dormitories (Purple)
  - 🎭 Other facilities (Teal)

## 📱 QR Code Scanner

- Scan QR codes for attendance marking
- Automatic course detection
- Instant attendance confirmation

## 👤 Profile

- View your personal information
- Change theme (Light/Dark mode)
- Sign out

## 🎨 Themes

- **Light Mode**: Clean and professional
- **Dark Mode**: Easy on the eyes
- Toggle anytime from the home screen

## 🔧 Technical Details

### Database:
- **Room Database** for local storage
- All data persists across app restarts
- Automatic synchronization

### Authentication:
- Secure login system
- User role management (Student/Teacher)
- Session management

### Data Storage:
- ✅ Messages stored in database
- ✅ User information persisted
- ✅ Course data cached
- ✅ Attendance records saved

## 🚀 Getting Started

1. **First Time Setup**:
   - App automatically creates database on first launch
   - Default users and courses are pre-loaded
   - No additional setup required

2. **Login**:
   - Use one of the demo accounts listed above
   - Enter User ID and Password
   - Click "Sign In"

3. **Explore**:
   - Navigate through tabs at the bottom
   - Try sending messages in Discussion
   - View campus map and courses

4. **Test Persistence**:
   - Send some messages
   - Close the app completely
   - Reopen the app and login
   - Your messages are still there! ✅

## 🐛 Troubleshooting

### Issue: Messages not appearing
**Solution**: 
- Ensure you're logged in
- Check internet connection (for future features)
- Restart the app

### Issue: Can't login
**Solution**:
- Double-check credentials
- Ensure User ID and Password are correct
- Try one of the demo accounts

### Issue: App crashes
**Solution**:
- Clear app data and cache
- Reinstall the app
- Database will be recreated automatically

## 📊 System Architecture

```
MainActivity
    ├── LoginScreen (Authentication)
    ├── SplashScreen (Initial loading)
    └── MainScreen (Main navigation)
        ├── HomeScreen
        ├── TimetableScreen
        ├── TasksScreen
        ├── CoursesScreen
        └── DiscussionScreen ✅
            ├── UniversityViewModel (State management)
            ├── UniversityRepository (Data access)
            └── AppDatabase (Room database)
```

## 🎯 Key Success: Discussion System

The discussion/messaging system is **fully implemented and working**:

✅ **Database Integration**: Messages stored in Room database  
✅ **Real-time Updates**: Flow-based reactive updates  
✅ **Persistence**: Messages survive app restarts  
✅ **Multi-user**: All users see the same messages  
✅ **Authentication**: Messages attributed to logged-in user  
✅ **Teacher Features**: Announcements and special styling  
✅ **UI Polish**: Auto-scroll, timestamps, role indicators  

## 📝 Notes

- This is a demo application with pre-populated data
- Database is created locally on the device
- All data is stored offline (no server required)
- Perfect for testing and demonstration purposes

## 🎓 For Teachers

Additional features available:
- Send official announcements (toggle in Discussion)
- Messages marked with star icon
- Announcements highlighted in red
- Course management (coming in future updates)

## 🎓 For Students

Features available:
- View all discussions
- Send messages
- See announcements from teachers
- Track courses and attendance

---

**Congratulations!** Your messaging system is now fully functional and integrated with the database. Users can send messages, and they will persist across app sessions and be visible to all users.

