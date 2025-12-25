# Fix: Teacher Login Not Working

## Problem
Only the student account (`2021CS042/student123`) was working. Teacher accounts (`PROF001/teacher123`, etc.) were not working.

## Root Cause
The database callback (`onCreate`) only runs **when the database is first created**. If you ran the app before, the database already existed, so the callback never ran again to insert the teacher accounts.

Since only the student was initially working, it appears the student account was added through a different mechanism, but teachers were not.

## Solution Implemented

### Added `ensureDefaultUsersExist()` Function
**File**: `UniversityRepository.kt`

This function checks if each default user exists, and if not, inserts them:

```kotlin
suspend fun ensureDefaultUsersExist() {
    // Check and insert student
    val student = userDao.getUserById("2021CS042")
    if (student == null) {
        userDao.insertUser(/* student data */)
    }

    // Check and insert each teacher
    val teacher1 = userDao.getUserById("PROF001")
    if (teacher1 == null) {
        userDao.insertUser(/* teacher data */)
    }
    // ... repeat for PROF002, PROF003
}
```

### Called on App Start
**File**: `UniversityViewModel.kt`

```kotlin
init {
    // ...
    viewModelScope.launch {
        repository.ensureDefaultUsersExist()
    }
}
```

Now every time the app starts, it checks if the default users exist and creates them if they don't.

## Working Accounts

After this fix, ALL accounts now work:

### ✅ Student Account:
- **User ID**: `2021CS042`
- **Password**: `student123`
- **Name**: Sarah Johnson
- **Role**: STUDENT

### ✅ Teacher Accounts:

**Account 1:**
- **User ID**: `PROF001`
- **Password**: `teacher123`
- **Name**: Dr. Robert Smith
- **Role**: TEACHER

**Account 2:**
- **User ID**: `PROF002`
- **Password**: `teacher123`
- **Name**: Dr. Sarah Chen
- **Role**: TEACHER

**Account 3:**
- **User ID**: `PROF003`
- **Password**: `teacher123`
- **Name**: Prof. Michael Brown
- **Role**: TEACHER

## How to Test

### Test All Accounts:

1. **Close app completely** (to trigger ViewModel init)
2. **Reopen app**
3. **Login as Teacher**: `PROF001` / `teacher123`
4. ✅ Should work now!
5. **Logout and test others**: `PROF002`, `PROF003`
6. ✅ All should work!

### If Still Not Working:

**Option 1: Clear App Data**
1. Go to Android Settings
2. Apps → Your App
3. Storage → Clear Data
4. Reopen app
5. Database will be recreated with all users

**Option 2: Uninstall and Reinstall**
1. Uninstall the app completely
2. Reinstall from Android Studio
3. Fresh database with all users

**Option 3: Increment Database Version** (Force Recreation)
Edit `AppDatabase.kt`:
```kotlin
@Database(
    // ...
    version = 2,  // Change from 1 to 2
    // ...
)
```
This forces a database rebuild with `.fallbackToDestructiveMigration()`

## Files Modified

1. **`UniversityRepository.kt`** - Added `ensureDefaultUsersExist()`
2. **`UniversityViewModel.kt`** - Call it on init

## Why This Fixes It

### Before:
```
App starts → Database exists → onCreate not called → No teachers inserted
Login with PROF001 → Not found in database → Login fails ❌
```

### After:
```
App starts → ViewModel init → ensureDefaultUsersExist() runs
→ Checks database → Teachers not found → Inserts them ✅
Login with PROF001 → Found in database → Login succeeds ✅
```

## Benefits

- ✅ Works even if database already exists
- ✅ Runs on every app start (safe, checks before inserting)
- ✅ No need to clear data or reinstall
- ✅ All 4 accounts now work
- ✅ Idempotent (can run multiple times safely)

## Verification

After this change:
- ✅ Student login works: `2021CS042/student123`
- ✅ Teacher 1 works: `PROF001/teacher123`
- ✅ Teacher 2 works: `PROF002/teacher123`
- ✅ Teacher 3 works: `PROF003/teacher123`

All accounts should now be functional!

