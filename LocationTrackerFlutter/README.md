# 📍 Location Tracker (Flutter)

A cross-platform location tracker app that works on **both Android and iOS** using Flutter.

## ✨ Features

- ✅ Works on **Android** and **iOS**
- Real-time GPS location tracking
- Interactive OpenStreetMap
- Automatic location updates
- Shows latitude and longitude
- No API key needed - 100% FREE!

## 🚀 Quick Start

### Prerequisites
- Flutter SDK (3.0.0 or higher)
- Android Studio / Xcode
- Android device/emulator OR iOS device/simulator

### Installation

1. **Install Flutter** (if not installed)
   ```bash
   # Visit: https://flutter.dev/docs/get-started/install
   ```

2. **Clone and setup**
   ```bash
   cd LocationTrackerFlutter
   flutter pub get
   ```

3. **Run on Android**
   ```bash
   flutter run
   ```

4. **Run on iOS** (Mac only)
   ```bash
   flutter run -d ios
   ```

That's it! 🎉

## 📱 Platform Support

| Platform | Status | Requirements |
|----------|--------|--------------|
| Android | ✅ Supported | Android 7.0+ (API 24+) |
| iOS | ✅ Supported | iOS 12.0+ |
| Web | ⚠️ Limited | Location works, but limited features |

## 🔐 Permissions

### Android
- `ACCESS_FINE_LOCATION` - Precise GPS
- `ACCESS_COARSE_LOCATION` - Network location
- `INTERNET` - Map tiles

### iOS
- `NSLocationWhenInUseUsageDescription` - Location access

## 🛠️ Tech Stack

- **Framework**: Flutter 3.0+
- **Language**: Dart
- **Maps**: flutter_map (OpenStreetMap)
- **Location**: geolocator
- **Permissions**: permission_handler

## 📦 Dependencies

```yaml
dependencies:
  geolocator: ^11.0.0          # Location services
  permission_handler: ^11.0.0   # Permission handling
  flutter_map: ^6.1.0          # OpenStreetMap
  latlong2: ^0.9.0             # Coordinates
```

## 🐛 Troubleshooting

### Android Issues
| Problem | Solution |
|---------|----------|
| Permission denied | Grant location in device settings |
| Blank map | Check internet connection |
| Build fails | Run `flutter clean && flutter pub get` |

### iOS Issues
| Problem | Solution |
|---------|----------|
| Permission not working | Check Info.plist has location keys |
| Build fails | Run `cd ios && pod install` |
| Simulator no location | Use Debug → Location → Custom Location |

### General
```bash
# Clean and rebuild
flutter clean
flutter pub get
flutter run

# Check Flutter setup
flutter doctor

# View logs
flutter logs
```

## 📂 Project Structure

```
LocationTrackerFlutter/
├── lib/
│   └── main.dart              # Main app code
├── android/
│   └── app/src/main/
│       └── AndroidManifest.xml # Android permissions
├── ios/
│   └── Runner/
│       └── Info.plist         # iOS permissions
├── pubspec.yaml               # Dependencies
└── README.md                  # This file
```

## 🎯 How to Build

### Android APK
```bash
flutter build apk --release
# Output: build/app/outputs/flutter-apk/app-release.apk
```

### iOS App (Mac only)
```bash
flutter build ios --release
# Then open in Xcode to archive and distribute
```

## 🆚 Flutter vs Native Android

| Feature | Flutter | Native Android |
|---------|---------|----------------|
| **Platforms** | Android + iOS | Android only |
| **Language** | Dart | Kotlin |
| **Code Reuse** | 100% shared | 0% |
| **Development Speed** | Faster | Slower |
| **Performance** | Near-native | Native |
| **Learning Curve** | Moderate | Steeper |

## 🗺️ Why OpenStreetMap?

✅ **100% FREE** - No payment needed  
✅ **No API Key** - Works immediately  
✅ **No Limits** - Unlimited usage  
✅ **Cross-platform** - Same on Android & iOS  

## 📝 Before Submitting

- [ ] App runs on Android
- [ ] App runs on iOS (if you have Mac)
- [ ] Location permission works
- [ ] Map displays correctly
- [ ] Captured screenshots
- [ ] Added your name below

## 👨‍💻 Author

**[Your Name]**  
Mobile Development Student  
[Your Institution]

## 📄 License

Educational project - Free to use for learning

---

<div align="center">

**Made with Flutter ❤️**

**Works on Android & iOS • No API Keys • No Payment**

</div>
