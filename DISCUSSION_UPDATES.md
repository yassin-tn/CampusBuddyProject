# Discussion Board Updates - Message Order & Rate Limiting

## Date: December 20, 2025

## Issues Fixed

### 1. ✅ Message Order Fixed - Newest at Bottom
**Problem**: Messages were appearing in reverse order (newest at top instead of bottom).

**Root Cause**: Database query used `ORDER BY timestamp DESC` (descending = newest first)

**Solution**: Changed to `ORDER BY timestamp ASC` (ascending = oldest first)

**File Modified**: `Daos.kt`
```kotlin
// BEFORE:
@Query("SELECT * FROM messages ORDER BY timestamp DESC")

// AFTER:
@Query("SELECT * FROM messages ORDER BY timestamp ASC")
```

**Result**: 
- ✅ Oldest messages appear at the top
- ✅ Newest messages appear at the bottom
- ✅ Auto-scroll takes you to the latest message
- ✅ Natural chat experience (like WhatsApp, Messenger, etc.)

---

### 2. ✅ 10-Minute Rate Limiting for Students
**Problem**: Students could spam messages without any restriction.

**Security Requirement**: Students should only be able to send one message every 10 minutes.

**Exemptions**: Teachers and admins are NOT rate limited (they can send unlimited messages).

**Solution Implemented**:

#### A. Added Rate Limiting in ViewModel
**File**: `UniversityViewModel.kt`

```kotlin
// Track last message time
private var lastMessageTime: Long = 0
private val MESSAGE_COOLDOWN_MS = 10 * 60 * 1000L // 10 minutes

// Error state for rate limit messages
private val _messageError = MutableStateFlow<String?>(null)
val messageError: StateFlow<String?> = _messageError.asStateFlow()

fun sendMessage(messageText: String, isAnnouncement: Boolean, onError: ((String) -> Unit)? = null) {
    val user = _currentUser.value ?: return

    // Check rate limiting ONLY for students
    if (user.role == UserRole.STUDENT) {
        val currentTime = System.currentTimeMillis()
        val timeSinceLastMessage = currentTime - lastMessageTime

        if (lastMessageTime > 0 && timeSinceLastMessage < MESSAGE_COOLDOWN_MS) {
            val remainingMinutes = ((MESSAGE_COOLDOWN_MS - timeSinceLastMessage) / 60000).toInt() + 1
            val errorMsg = "Please wait $remainingMinutes more minute(s) before sending another message"
            _messageError.value = errorMsg
            return // Block the message
        }
    }

    // Send message...
    
    // Update last message time ONLY for students
    if (user.role == UserRole.STUDENT) {
        lastMessageTime = System.currentTimeMillis()
    }
}
```

#### B. Added Error Display in UI
**File**: `DiscussionScreen.kt`

```kotlin
// Show snackbar when rate limit is hit
val snackbarHostState = remember { SnackbarHostState() }

LaunchedEffect(rateLimitError) {
    rateLimitError?.let {
        snackbarHostState.showSnackbar(
            message = it,
            duration = SnackbarDuration.Long
        )
        onClearError()
    }
}
```

**Features**:
- ✅ Snackbar appears at bottom of screen
- ✅ Shows remaining wait time (e.g., "Please wait 8 more minutes...")
- ✅ Auto-dismisses after a few seconds
- ✅ Clear error message for users

---

## How It Works

### Message Order:
```
[Oldest Message - Top]
Message 1 (10:00 AM)
Message 2 (10:05 AM)
Message 3 (10:10 AM)
Message 4 (10:15 AM)
[Newest Message - Bottom] ← Auto-scrolls here
```

### Rate Limiting Flow:

#### For Students:
```
1. Student sends message at 10:00 AM ✅ (Sent successfully)
2. Student tries to send at 10:05 AM ❌ (Blocked - "Wait 5 more minutes")
3. Student tries to send at 10:09 AM ❌ (Blocked - "Wait 1 more minute")
4. Student sends message at 10:11 AM ✅ (Sent successfully)
```

#### For Teachers:
```
1. Teacher sends message at 10:00 AM ✅ (Sent)
2. Teacher sends message at 10:01 AM ✅ (Sent - NO RESTRICTION)
3. Teacher sends message at 10:02 AM ✅ (Sent - NO RESTRICTION)
... unlimited messages!
```

---

## Files Modified

1. **`Daos.kt`**
   - Changed message query from `DESC` to `ASC`
   - Affects: Message ordering in UI

2. **`UniversityViewModel.kt`**
   - Added `lastMessageTime` tracking
   - Added `MESSAGE_COOLDOWN_MS` constant (10 minutes)
   - Added `messageError` StateFlow
   - Updated `sendMessage()` with rate limiting logic
   - Added `clearMessageError()` function
   - Only applies to students (teachers bypass)

3. **`DiscussionScreen.kt`**
   - Added `rateLimitError` parameter
   - Added `onClearError` callback
   - Added `SnackbarHost` for error display
   - Added `LaunchedEffect` to show errors

4. **`MainScreen.kt`**
   - Collect `messageError` from ViewModel
   - Pass `rateLimitError` to DiscussionScreen
   - Pass `onClearError` callback

---

## Testing Instructions

### Test Message Order:
1. ✅ Login with any account
2. ✅ Go to Discussion tab
3. ✅ Send a message
4. ✅ Verify it appears at the BOTTOM
5. ✅ Scroll up to see older messages at TOP
6. ✅ Send another message
7. ✅ Auto-scrolls to bottom to show new message

### Test Rate Limiting (Students):
1. ✅ Login as Student: `2021CS042` / `student123`
2. ✅ Go to Discussion tab
3. ✅ Send a message - Should work ✅
4. ✅ Try to send another immediately
5. ✅ See snackbar: "Please wait 10 more minutes..."
6. ✅ Wait 10 minutes (or change system time for testing)
7. ✅ Try to send again - Should work ✅

### Test No Rate Limit (Teachers):
1. ✅ Login as Teacher: `PROF001` / `teacher123`
2. ✅ Go to Discussion tab
3. ✅ Send a message - Works ✅
4. ✅ Send another immediately - Works ✅
5. ✅ Send 10 messages in a row - All work ✅
6. ✅ No rate limiting for teachers!

---

## Configuration

### To Change Rate Limit Duration:
Edit `UniversityViewModel.kt`:
```kotlin
// Current: 10 minutes
private val MESSAGE_COOLDOWN_MS = 10 * 60 * 1000L

// For 5 minutes:
private val MESSAGE_COOLDOWN_MS = 5 * 60 * 1000L

// For 30 minutes:
private val MESSAGE_COOLDOWN_MS = 30 * 60 * 1000L
```

### To Disable Rate Limiting:
Comment out the rate limiting check in `sendMessage()`:
```kotlin
// if (user.role == UserRole.STUDENT) {
//     // ... rate limiting code ...
// }
```

---

## Security Benefits

### Why Rate Limiting?
1. **Prevents Spam**: Students can't flood the discussion board
2. **Reduces Database Load**: Fewer write operations
3. **Better Moderation**: Teachers can manage discussions more easily
4. **Fair Usage**: Everyone gets equal opportunity to participate
5. **Professional Environment**: Encourages thoughtful messages

### What's Protected:
- ✅ Students: 10-minute cooldown
- ✅ Clear error messages
- ✅ Time remaining shown
- ✅ Teachers unrestricted (for announcements and moderation)

### What's NOT Affected:
- ❌ Teachers can send unlimited messages
- ❌ Viewing messages (anyone can view anytime)
- ❌ Other app features (only discussion affected)

---

## User Experience

### For Students:
- **First Message**: Sends immediately ✅
- **Too Soon**: "Please wait X more minutes..." snackbar appears
- **After Cooldown**: Can send next message ✅
- **Clear Feedback**: Always know when you can send next

### For Teachers:
- **No Restrictions**: Send anytime, anywhere
- **Announcements**: Can make multiple announcements
- **Moderation**: Can respond to students immediately

---

## Visual Examples

### Message Order (Before Fix):
```
❌ WRONG:
[Newest] Message 4 (10:15 AM) ← Top
         Message 3 (10:10 AM)
         Message 2 (10:05 AM)
[Oldest] Message 1 (10:00 AM) ← Bottom
```

### Message Order (After Fix):
```
✅ CORRECT:
[Oldest] Message 1 (10:00 AM) ← Top
         Message 2 (10:05 AM)
         Message 3 (10:10 AM)
[Newest] Message 4 (10:15 AM) ← Bottom (auto-scroll here)
```

### Rate Limit Error Display:
```
┌─────────────────────────────────────┐
│  Discussion Board                    │
├─────────────────────────────────────┤
│                                      │
│  [Message 1]                         │
│  [Message 2]                         │
│  [Message 3]                         │
│                                      │
├─────────────────────────────────────┤
│  ⚠️ Please wait 8 more minute(s)    │
│     before sending another message   │
└─────────────────────────────────────┘
   [Text Input]  [Send]
```

---

## Technical Details

### Rate Limiting Algorithm:
```kotlin
currentTime = System.currentTimeMillis()
timeSinceLastMessage = currentTime - lastMessageTime

if (timeSinceLastMessage < 10_MINUTES) {
    remainingTime = 10_MINUTES - timeSinceLastMessage
    remainingMinutes = ceil(remainingTime / 60000)
    showError("Wait $remainingMinutes more minutes")
    return // Block message
}

// Allow message
lastMessageTime = currentTime
sendToDatabase()
```

### Message Ordering Query:
```sql
-- Gets messages from oldest to newest
SELECT * FROM messages ORDER BY timestamp ASC;

-- timestamp values:
-- 1703001000000 (oldest)
-- 1703001300000
-- 1703001600000
-- 1703001900000 (newest)
```

---

## Performance Impact

### Database Query:
- ✅ No performance impact (same query, different order)
- ✅ Index on `timestamp` column recommended

### Rate Limiting:
- ✅ Minimal overhead (single timestamp comparison)
- ✅ No database queries needed for rate check
- ✅ In-memory tracking only

### Memory Usage:
- ✅ Only 1 Long variable per ViewModel instance
- ✅ Negligible memory footprint

---

## Future Enhancements

Possible improvements:
- [ ] Per-user rate limiting (track each student separately)
- [ ] Configurable cooldown per user role
- [ ] Admin panel to adjust rate limits
- [ ] Warning at 9 minutes ("You can send next message in 1 minute")
- [ ] Countdown timer in UI
- [ ] Different limits for different discussion boards
- [ ] Persistent rate limiting (survives app restart)

---

## Troubleshooting

### Issue: Student can still spam messages
**Check**: 
- Verify user role is `UserRole.STUDENT`
- Check `lastMessageTime` is being updated
- Ensure cooldown constant is correct

### Issue: Teacher is rate limited
**Check**:
- Verify user role is `UserRole.TEACHER`
- Rate limit check should skip teachers
- Check the `if (user.role == UserRole.STUDENT)` condition

### Issue: Messages still appear newest first
**Check**:
- Verify Daos.kt has `ORDER BY timestamp ASC`
- Rebuild project (clean + rebuild)
- Check database migration completed

### Issue: Error message not showing
**Check**:
- Snackbar host added to Scaffold
- `rateLimitError` passed to DiscussionScreen
- `LaunchedEffect` is triggered

---

## Summary

✅ **Message Order**: Fixed - newest at bottom  
✅ **Rate Limiting**: Implemented - 10 minutes for students  
✅ **Teacher Exemption**: Teachers unlimited messages  
✅ **User Feedback**: Clear error messages with countdown  
✅ **Security**: Prevents spam and abuse  
✅ **UX**: Professional chat experience  

**All changes are live and ready to test!** 🚀

---

*For testing, remember to use the correct login credentials:*
- Student: `2021CS042` / `student123`
- Teacher: `PROF001` / `teacher123`

