# Troubleshooting Guide

Common issues and their solutions for the Simple Location Tracker App.

## 🔴 Critical Issues

### Issue: App Crashes Immediately on Launch

**Symptoms:**
- App opens and immediately closes
- Error in Logcat about API key

**Causes:**
1. Missing Google Maps API key
2. Invalid API key format

**Solutions:**
```
1. Open app/src/main/AndroidManifest.xml
2. Find: android:value="YOUR_API_KEY_HERE"
3. Replace with: android:value="AIza...your-actual-key"
4. Rebuild and run
```

**Verify:**
- API key should start with "AIza"
- No spaces or quotes around the key
- Key is between the quotation marks

---

### Issue: Map Shows Blank/Gray Tiles

**Symptoms:**
- App launches successfully
- Map area is gray or blank
- No map tiles loading

**Causes:**
1. Invalid or restricted API key
2. Maps SDK not enabled
3. No internet connection
4. API key restrictions too strict

**Solutions:**

**Step 1: Verify API Key**
```
1. Go to https://console.cloud.google.com/
2. Select your project
3. Go to Credentials
4. Check your API key is listed
5. Copy and verify it matches AndroidManifest.xml
```

**Step 2: Enable Maps SDK**
```
1. In Google Cloud Console
2. Go to "APIs & Services" → "Library"
3. Search "Maps SDK for Android"
4. Click it and ensure it shows "API Enabled"
5. If not, click "Enable"
```

**Step 3: Check Restrictions**
```
1. Go to Credentials → Your API Key
2. Under "Application restrictions":
   - For testing: Select "None"
   - For production: Use "Android apps" with correct package name
3. Under "API restrictions":
   - Select "Restrict key"
   - Ensure "Maps SDK for Android" is checked
4. Save changes
```

**Step 4: Check Internet**
```
- Verify device/emulator has internet connection
- Try opening a browser
- Check Wi-Fi/data is enabled
```

---

### Issue: Location Permission Dialog Never Appears

**Symptoms:**
- App launches
- Map loads
- No permission dialog shown
- No location displayed

**Causes:**
1. Permission already denied permanently
2. App already has permission
3. Device location services disabled

**Solutions:**

**Step 1: Clear App Data**
```
Device Settings → Apps → Simple Location Tracker → 
Storage → Clear Data → Relaunch app
```

**Step 2: Reinstall App**
```
1. Uninstall the app completely
2. Rebuild in Android Studio
3. Install and run again
4. Permission dialog should appear
```

**Step 3: Check Device Settings**
```
Device Settings → Location → 
Ensure location is turned ON
```

---

## 🟡 Common Issues

### Issue: Location Not Updating

**Symptoms:**
- Map loads correctly
- Initial location shows
- Marker doesn't move when device moves

**Causes:**
1. Device not actually moving
2. Indoor location (weak GPS)
3. Location updates not starting
4. App in background

**Solutions:**

**For Physical Device:**
```
1. Go outside (GPS works better outdoors)
2. Walk at least 10-20 meters
3. Wait 5-10 seconds for update
4. Check toast messages for coordinate changes
```

**For Emulator:**
```
1. Click "..." (Extended controls)
2. Go to "Location" tab
3. Enter new coordinates
4. Click "Send"
5. Watch marker update
```

**Check Code:**
```kotlin
// Verify in MainActivity.kt:
// - startLocationUpdates() is called
// - locationCallback is registered
// - No errors in Logcat
```

---

### Issue: "This app won't run without Google Play Services"

**Symptoms:**
- Dialog appears on launch
- App won't proceed

**Causes:**
1. Emulator doesn't have Google Play Services
2. Google Play Services outdated
3. Using wrong emulator image

**Solutions:**

**For Emulator:**
```
1. Open AVD Manager
2. Create new virtual device
3. When selecting system image:
   - Choose one with "Google Play" icon
   - NOT "Google APIs" (different!)
4. Download if needed
5. Create and launch
```

**For Physical Device:**
```
1. Open Play Store
2. Search "Google Play Services"
3. Update if available
4. Restart device
```

---

### Issue: Permission Denied - App Shows Toast

**Symptoms:**
- Permission dialog appears
- User clicks "Deny"
- Toast: "Location permission denied"
- Map shows but no location

**This is Expected Behavior!**

**Solutions:**

**To Grant Permission:**
```
Option 1: Reinstall app and click "Allow"

Option 2: Manual grant
Device Settings → Apps → Simple Location Tracker → 
Permissions → Location → Allow
```

**To Test Denial Handling:**
```
This is actually correct behavior!
The app gracefully handles denial without crashing.
```

---

## 🟢 Minor Issues

### Issue: Marker Jumps Around

**Symptoms:**
- Location marker moves erratically
- Coordinates jump significantly

**Causes:**
1. GPS signal weak (indoor)
2. Switching between GPS and network location
3. Normal GPS behavior

**Solutions:**
```
1. Go outside for better GPS signal
2. Wait for GPS to stabilize (30-60 seconds)
3. This is normal GPS behavior indoors
```

**Not a Bug:**
- GPS accuracy varies (5-50 meters typical)
- Indoor GPS is inherently less accurate

---

### Issue: Toast Messages Annoying

**Symptoms:**
- Toast appears every 5 seconds
- Covers part of map

**This is By Design!**

**To Disable (Optional):**
```kotlin
// In MainActivity.kt, comment out:
// Toast.makeText(
//     this,
//     "Lat: ${...}, Lng: ${...}",
//     Toast.LENGTH_SHORT
// ).show()
```

---

### Issue: Blue Dot and Marker Both Showing

**Symptoms:**
- Blue dot (system location indicator)
- Red marker (custom marker)
- Both visible on map

**This is Normal!**

**Explanation:**
- Blue dot: System's "My Location" feature
- Red marker: Our custom location marker
- Both are intentional for demonstration

**To Remove Blue Dot (Optional):**
```kotlin
// In MainActivity.kt, remove:
// map.isMyLocationEnabled = true
```

---

## 🔧 Build Issues

### Issue: Gradle Sync Failed

**Symptoms:**
- "Gradle sync failed" error
- Red text in Build output

**Solutions:**

**Step 1: Check Internet**
```
Gradle needs to download dependencies
Ensure stable internet connection
```

**Step 2: Invalidate Caches**
```
File → Invalidate Caches → 
Invalidate and Restart
```

**Step 3: Clean Build**
```
Build → Clean Project
Build → Rebuild Project
```

**Step 4: Update Gradle**
```
File → Project Structure → Project
Update Gradle version if prompted
```

---

### Issue: Cannot Resolve Symbol 'GoogleMap'

**Symptoms:**
- Red underlines in MainActivity.kt
- "Cannot resolve symbol" errors

**Causes:**
1. Gradle sync not completed
2. Dependencies not downloaded
3. IDE cache issue

**Solutions:**
```
1. Wait for Gradle sync to complete (bottom right)
2. File → Sync Project with Gradle Files
3. File → Invalidate Caches → Invalidate and Restart
4. Verify libs.versions.toml has play-services entries
```

---

## 📱 Device-Specific Issues

### Issue: Emulator Extremely Slow

**Symptoms:**
- Emulator takes forever to start
- App runs slowly

**Solutions:**
```
1. Enable Hardware Acceleration:
   - Windows: Ensure Hyper-V or HAXM installed
   - Mac: Ensure Hypervisor.framework enabled
   - Linux: Ensure KVM enabled

2. Allocate More RAM:
   - AVD Manager → Edit device
   - Increase RAM to 2048 MB or more

3. Use Smaller Screen:
   - Choose Pixel 4 instead of Pixel 6 Pro
   - Smaller resolution = faster performance
```

---

### Issue: Physical Device Not Detected

**Symptoms:**
- Device connected via USB
- Not showing in Android Studio

**Solutions:**

**Step 1: Enable USB Debugging**
```
1. Settings → About Phone
2. Tap "Build Number" 7 times
3. Go back → Developer Options
4. Enable "USB Debugging"
```

**Step 2: Check USB Connection**
```
1. Try different USB cable
2. Try different USB port
3. Select "File Transfer" mode on device
```

**Step 3: Install Drivers (Windows)**
```
1. Download device-specific USB drivers
2. Install drivers
3. Restart computer
```

---

## 🔍 Debugging Tips

### Enable Verbose Logging

**In Android Studio:**
```
1. Open Logcat (bottom panel)
2. Select your device
3. Filter: "LocationTracker" or "MainActivity"
4. Watch for errors or warnings
```

### Check Permissions in Logcat
```
Look for:
- "Permission granted" or "Permission denied"
- SecurityException messages
- Location update messages
```

### Verify Location Updates
```
Add log statements in MainActivity.kt:

private fun updateLocationOnMap(location: Location) {
    Log.d("LocationTracker", "New location: ${location.latitude}, ${location.longitude}")
    // ... rest of code
}
```

---

## 📞 Getting Help

### Before Asking for Help

**Checklist:**
- [ ] API key added to AndroidManifest.xml
- [ ] Maps SDK for Android enabled in Google Cloud
- [ ] Internet connection working
- [ ] Location services enabled on device
- [ ] Permission granted to app
- [ ] Checked Logcat for errors
- [ ] Tried on different device/emulator

### Information to Provide

When asking for help, include:
1. **Error message** (exact text or screenshot)
2. **Logcat output** (relevant lines)
3. **Device/emulator** (model and Android version)
4. **Steps to reproduce** (what you did)
5. **What you've tried** (solutions attempted)

---

## 🎯 Quick Diagnostic

**Run through this checklist:**

```
1. API Key
   [ ] Added to AndroidManifest.xml
   [ ] Starts with "AIza"
   [ ] Maps SDK enabled in Google Cloud

2. Permissions
   [ ] Declared in AndroidManifest.xml
   [ ] Granted at runtime
   [ ] Location services enabled on device

3. Internet
   [ ] Device/emulator has internet
   [ ] Can open websites in browser

4. Google Play Services
   [ ] Installed on device/emulator
   [ ] Up to date

5. Build
   [ ] Gradle sync successful
   [ ] No red underlines in code
   [ ] App builds without errors

6. Runtime
   [ ] App launches without crashing
   [ ] Map loads (not gray)
   [ ] Permission dialog appears
   [ ] Location shows after granting permission
```

**If all checked:** App should work!
**If any unchecked:** That's your issue - fix it first.

---

## 🆘 Last Resort

### Nuclear Option: Start Fresh

```
1. Close Android Studio
2. Delete .gradle and .idea folders
3. Delete app/build folder
4. Reopen project in Android Studio
5. Let Gradle sync complete
6. Clean and rebuild
7. Run app
```

### Still Not Working?

1. Check SETUP_GUIDE.md for detailed instructions
2. Review CODE_EXPLANATION.md to understand the code
3. Compare your code with the original
4. Check Android Studio version (update if old)
5. Try on a different device/emulator

---

## 📚 Additional Resources

- [Google Maps Platform Support](https://developers.google.com/maps/support)
- [Android Location Guide](https://developer.android.com/training/location)
- [Stack Overflow - Android Tag](https://stackoverflow.com/questions/tagged/android)
- [Android Developers Community](https://developer.android.com/community)

---

**Remember:** Most issues are related to API key configuration or permissions. Double-check those first!
