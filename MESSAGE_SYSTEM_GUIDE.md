# Message System Implementation Guide

## Overview
The message system has been fully implemented and connected to the database. Messages are now persisted and shared between all users in real-time.

## Architecture

### 1. Database Layer (`MessageEntity` & `MessageDao`)
- **Location**: `app/src/main/java/com/example/yearprojectmobile/database/`
- **MessageEntity**: Stores message data in Room database
- **MessageDao**: Provides database operations (insert, get all, get announcements, delete)

### 2. Repository Layer (`UniversityRepository`)
- **Location**: `app/src/main/java/com/example/yearprojectmobile/repository/UniversityRepository.kt`
- **Methods**:
  - `getAllMessages()`: Returns Flow of all messages
  - `getAnnouncements()`: Returns Flow of announcement messages only
  - `sendMessage(message: Message)`: Inserts a message into database
  - `deleteMessage(message: Message)`: Deletes a message

### 3. ViewModel Layer (`UniversityViewModel`)
- **Location**: `app/src/main/java/com/example/yearprojectmobile/viewmodel/UniversityViewModel.kt`
- **State**:
  - `messages`: StateFlow<List<Message>> - Observable list of all messages
- **Methods**:
  - `sendMessage(messageText: String, isAnnouncement: Boolean)`: Creates and saves a message

### 4. UI Layer (`DiscussionScreen`)
- **Location**: `app/src/main/java/com/example/yearprojectmobile/screens/DiscussionScreen.kt`
- **Features**:
  - Real-time message display
  - Auto-scroll to latest message
  - Message input with announcement toggle (teachers only)
  - Visual indicators for teachers and announcements

### 5. Integration in MainScreen
- **Location**: `app/src/main/java/com/example/yearprojectmobile/screens/MainScreen.kt`
- **Connects**:
  - Messages from ViewModel
  - Current user info
  - sendMessage callback to ViewModel

## How It Works

### Sending a Message:
1. User types message in `DiscussionScreen`
2. Clicks send button → calls `onSendMessage` callback
3. Callback invokes `universityViewModel.sendMessage()`
4. ViewModel creates `Message` object with current user info
5. Message saved to database via `repository.sendMessage()`
6. Database Flow updates automatically
7. All observers (including other screens) receive the new message

### Receiving Messages:
1. `UniversityViewModel` observes `repository.getAllMessages()` Flow
2. Updates `_messages` StateFlow when database changes
3. `MainScreen` collects `messages` StateFlow
4. Passes messages to `DiscussionScreen`
5. `DiscussionScreen` displays messages in LazyColumn

## User Authentication

### Login System:
- **Location**: `MainActivity.kt` - `LoginScreen` composable
- **Process**:
  1. User enters ID and password
  2. Calls `universityViewModel.login(userId, password)`
  3. ViewModel queries database via repository
  4. If successful, sets `currentUser` in ViewModel
  5. User info used for message attribution

### Default Accounts (in database):
- **Student**: 
  - ID: `2021CS042`
  - Password: `student123`
  - Name: Sarah Johnson

- **Teacher**: 
  - ID: `PROF001`
  - Password: `teacher123`
  - Name: Dr. Robert Smith

## Features

### For All Users:
- ✅ Send messages
- ✅ View all messages
- ✅ See sender name and timestamp
- ✅ Auto-scroll to latest messages
- ✅ Real-time updates

### For Teachers Only:
- ✅ Send announcements (highlighted in red)
- ✅ Messages marked with star icon
- ✅ Special visual styling

## Data Persistence

The app uses **Room Database** with the following benefits:
- ✅ Messages persist across app restarts
- ✅ Automatic data synchronization
- ✅ Type-safe database operations
- ✅ Built-in Flow support for reactive updates

## Testing the System

1. **Login** with one of the demo accounts
2. Navigate to **Discussion** tab (bottom navigation)
3. **Type a message** in the input field at bottom
4. **Click send** button
5. Message appears immediately in the chat
6. **Restart the app** - messages are still there!
7. **Login with different account** - see the same messages

## Troubleshooting

### Messages not appearing?
- Check that you're logged in
- Verify database initialization completed
- Check ViewModel is connected in MainScreen

### Messages disappear after restart?
- Database should persist data automatically
- Check Room database configuration
- Verify AppDatabase callback is running

## Future Enhancements

Possible improvements:
- [ ] Message editing/deletion by sender
- [ ] Rich text formatting
- [ ] Image/file attachments
- [ ] Private messaging between users
- [ ] Push notifications for new messages
- [ ] Message search functionality
- [ ] Reaction emojis
- [ ] Thread/reply functionality

