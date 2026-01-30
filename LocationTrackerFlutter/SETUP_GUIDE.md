# Flutter Setup Guide

## Step 1: Install Flutter

### Windows
```bash
# Download Flutter SDK from: https://flutter.dev/docs/get-started/install/windows
# Extract to C:\flutter
# Add to PATH: C:\flutter\bin
```

### Mac
```bash
# Download Flutter SDK from: https://flutter.dev/docs/get-started/install/macos
# Extract to ~/flutter
# Add to PATH in ~/.zshrc or ~/.bash_profile
export PATH="$PATH:$HOME/flutter/bin"
```

### Linux
```bash
# Download Flutter SDK from: https://flutter.dev/docs/get-started/install/linux
# Extract to ~/flutter
# Add to PATH in ~/.bashrc
export PATH="$PATH:$HOME/flutter/bin"
```

## Step 2: Verify Installation

```bash
flutter doctor
```

Fix any issues shown by `flutter doctor`.

## Step 3: Setup Project

```bash
cd LocationTrackerFlutter
flutter pub get
```

## Step 4: Run the App

### On Android
```bash
# Connect Android device or start emulator
flutter devices
flutter run
```

### On iOS (Mac only)
```bash
# Open iOS Simulator or connect iPhone
flutter devices
flutter run -d ios
```

## Step 5: Test Location

### Android Emulator
1. Click "..." (Extended Controls)
2. Go to "Location"
3. Enter coordinates or click map
4. Click "Send"

### iOS Simulator
1. Debug → Location → Custom Location
2. Enter coordinates
3. Click OK

## Common Issues

### Android: Gradle build fails
```bash
cd android
./gradlew clean
cd ..
flutter clean
flutter pub get
flutter run
```

### iOS: Pod install fails
```bash
cd ios
pod deintegrate
pod install
cd ..
flutter run
```

### Permission denied
- Android: Settings → Apps → Location Tracker → Permissions → Location → Allow
- iOS: Settings → Privacy → Location Services → Location Tracker → While Using

## Build for Release

### Android APK
```bash
flutter build apk --release
```

### iOS (Mac only)
```bash
flutter build ios --release
# Then open ios/Runner.xcworkspace in Xcode
```

Done! 🎉
