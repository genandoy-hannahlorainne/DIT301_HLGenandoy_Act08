# 📍 Simple Location Tracker App

A location tracker app that shows your real-time GPS location on a map using OpenStreetMap.

## 📦 Available Versions

This project has **TWO versions**:

1. **Native Android** (Kotlin) - `SimpleLocationTrackerApp/`
   - Android only
   - Native performance
   - Best for learning Android development

2. **Cross-Platform** (Flutter) - `LocationTrackerFlutter/`
   - ✅ **Works on Android AND iOS**
   - Single codebase
   - Faster development
   - **Recommended for cross-platform needs**

Choose the version that fits your needs!

## ✨ Features

- Real-time GPS location tracking
- Interactive map (zoom, pan, rotate)
- Automatic location updates every 5 seconds
- Shows latitude and longitude coordinates
- No API key needed - 100% FREE!

## 🚀 Quick Start

### Native Android Version

**Location**: `SimpleLocationTrackerApp/`

1. Open in Android Studio
2. Click Run ▶️
3. Grant location permission
4. Done! 🎉

### Flutter Version (Android + iOS)

**Location**: `LocationTrackerFlutter/`

1. Install Flutter SDK
2. Run `flutter pub get`
3. Run `flutter run`
4. Done! 🎉

See each folder's README for detailed instructions.

## 🔐 Permissions

- **ACCESS_FINE_LOCATION** - For precise GPS location
- **ACCESS_COARSE_LOCATION** - For network-based location
- **INTERNET** - For loading map tiles

## � How It Works

1. App requests location permission
2. User grants permission
3. App gets GPS location from device
4. Location displayed on OpenStreetMap
5. Marker updates automatically as you move

## 🛠️ Tech Stack

### Native Android Version
- **Language**: Kotlin
- **Maps**: OSMDroid (OpenStreetMap)
- **Location**: Google Play Services Location API
- **Min SDK**: Android 7.0 (API 24)
- **Platform**: Android only

### Flutter Version
- **Language**: Dart
- **Framework**: Flutter 3.0+
- **Maps**: flutter_map (OpenStreetMap)
- **Location**: geolocator package
- **Platforms**: Android + iOS

## 📸 Screenshots

Add these 3 screenshots before submitting:
1. `screenshots/permission_request.png` - Permission dialog
2. `screenshots/map_location.png` - Map with location marker
3. `screenshots/location_update.png` - Updated location

## 🐛 Troubleshooting

| Problem | Solution |
|---------|----------|
| Blank map | Check internet connection |
| No location | Enable GPS in device settings |
| App crashes | Grant location permission |
| Location not updating | Go outside for better GPS signal |

## 🆚 Which Version Should I Use?

| Feature | Native Android | Flutter |
|---------|---------------|---------|
| **Platforms** | Android only | Android + iOS |
| **Language** | Kotlin | Dart |
| **Setup Time** | 2 minutes | 5 minutes |
| **Performance** | Native | Near-native |
| **Learning** | Android dev | Cross-platform dev |
| **Best For** | Android-only projects | Multi-platform apps |

**Recommendation**: 
- Need iOS support? → Use **Flutter version**
- Android only? → Either works, **Native** is simpler
- Learning cross-platform? → Use **Flutter version**

## 📚 Documentation

### Native Android Version
- [Android App README](SimpleLocationTrackerApp/README.md)
- [Quick Start Guide](documents/QUICK_START.md)
- [Setup Guide](documents/SETUP_GUIDE.md)
- [Troubleshooting](documents/TROUBLESHOOTING.md)

### Flutter Version
- [Flutter App README](LocationTrackerFlutter/README.md)
- [Flutter Setup Guide](LocationTrackerFlutter/SETUP_GUIDE.md)

## 📝 Before Submitting

- [ ] App runs successfully
- [ ] Location permission works
- [ ] Map displays correctly
- [ ] Captured 3 screenshots
- [ ] Added your name below

## 👨‍💻 Author

**[Your Name]**  
Hannah Lorainne Genandoy
3rd Year PUP-T Student

---

<div align="center">

**No API Keys • No Payment • No Hassle**

</div>
