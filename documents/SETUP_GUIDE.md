# Setup Guide - Simple Location Tracker App

This guide will help you get the app running on your device or emulator.

## ✨ Good News!

**NO API KEY REQUIRED!** This app uses OpenStreetMap, which is completely free and requires no setup.

## Step 1: Open Project (30 seconds)

1. **Open Android Studio**
2. **File → Open**
3. **Select this project folder**
4. **Wait for Gradle sync** (bottom right corner)

## Step 2: Run the App (1 minute)

### On Physical Device (Recommended for GPS Testing)

1. **Enable Developer Options**
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
   - Go back to Settings → Developer Options
   - Enable "USB Debugging"

2. **Connect Device**
   - Connect your phone via USB
   - Allow USB debugging when prompted

3. **Run App**
   - Click the green "Run" button in Android Studio
   - Select your device
   - Wait for installation

4. **Grant Permissions**
   - When the app launches, tap "Allow" for location permissions
   - Make sure GPS is enabled in your device settings

### On Emulator

1. **Create AVD (Android Virtual Device)**
   - Tools → Device Manager
   - Click "Create Device"
   - Select a device (e.g., Pixel 5)
   - Select a system image (API 30 or higher recommended)
   - Click "Finish"

2. **Run on Emulator**
   - Click the green "Run" button
   - Select your emulator
   - Wait for the emulator to start

3. **Simulate Location**
   - Click the "..." (Extended controls) in the emulator toolbar
   - Go to "Location"
   - Enter coordinates or use the map to set a location
   - Click "Send"

## Step 3: Test the App (5 minutes)

### Test Checklist

- [ ] App launches without crashing
- [ ] Permission dialog appears
- [ ] After granting permission, map loads
- [ ] Marker appears at your location
- [ ] Toast message shows coordinates
- [ ] Moving the device/emulator updates the marker

### Testing Location Updates

**On Physical Device:**
- Walk around outside (GPS works best outdoors)
- Watch the marker update as you move

**On Emulator:**
- Use Extended Controls → Location
- Change the coordinates
- Click "Send" to simulate movement
- Watch the marker update

## Step 4: Take Screenshots (5 minutes)

1. **Permission Request Screenshot**
   - Uninstall and reinstall the app
   - Launch it
   - Take screenshot when permission dialog appears

2. **Map Location Screenshot**
   - After granting permission
   - Wait for map to load and marker to appear
   - Take screenshot

3. **Location Update Screenshot**
   - Move to a different location
   - Wait for marker to update
   - Take screenshot showing the new position

4. **Save Screenshots**
   - Save all screenshots to the `screenshots/` folder
   - Name them: `permission_request.png`, `map_location.png`, `location_update.png`

## Troubleshooting

### Map shows blank/gray tiles
- **Cause**: No internet connection
- **Solution**: Check internet connection and try again
- **Solution**: Wait a few seconds for tiles to download

### App crashes on launch
- **Cause**: Permission denied
- **Solution**: Uninstall app, reinstall, and grant permission

### Location not showing
- **Cause**: Permission denied
- **Solution**: Uninstall app, reinstall, and grant permission
- **Cause**: GPS disabled
- **Solution**: Enable location services in device settings
- **Cause**: Indoor location
- **Solution**: Go outside for better GPS signal

### Gradle sync fails
- **Cause**: Missing dependencies
- **Solution**: Check internet connection and sync again
- **Solution**: Update Android Studio to the latest version

## Why OpenStreetMap?

This app uses **OpenStreetMap (OSMDroid)** instead of Google Maps:

### Advantages
- ✅ **100% FREE** - No payment info required
- ✅ **No API Key** - Works immediately
- ✅ **No Setup** - Just run the app
- ✅ **Open Source** - Community-driven maps
- ✅ **No Limits** - Unlimited map loads
- ✅ **Privacy** - No tracking

### Perfect For
- Student projects
- Learning Android development
- Quick prototypes
- Apps without budget

## Additional Resources

- [OSMDroid Documentation](https://github.com/osmdroid/osmdroid)
- [Android Location Guide](https://developer.android.com/training/location)
- [Request Runtime Permissions](https://developer.android.com/training/permissions/requesting)

## Comparison: OpenStreetMap vs Google Maps

| Feature | OpenStreetMap | Google Maps |
|---------|---------------|-------------|
| Cost | FREE | FREE with limits |
| API Key | Not required | Required |
| Payment Info | Not required | Required |
| Setup Time | 0 minutes | 10-15 minutes |
| Map Quality | Excellent | Excellent |
| Offline Support | Yes | Limited |
| Privacy | High | Lower |

## Next Steps

1. ✅ Run the app
2. ✅ Test location tracking
3. ✅ Capture screenshots
4. ✅ Review `LAB_REQUIREMENTS_CHECKLIST.md`
5. ✅ Submit to GitHub

---

**Need Help?** Check `TROUBLESHOOTING.md` or `README.md`

**Ready to Submit?** See `LAB_REQUIREMENTS_CHECKLIST.md`
