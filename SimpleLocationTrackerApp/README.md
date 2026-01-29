# Simple Location Tracker App

A mobile application that displays the user's live GPS location on a map using Android's location services and OpenStreetMap (OSMDroid).

## App Description

This app demonstrates the integration of device GPS sensors with Google Maps to provide real-time location tracking. When launched, the app requests location permissions from the user and displays their current position on an interactive map. As the user moves, the location marker updates automatically to reflect their new position.

## Features

- **Real-time Location Tracking**: Displays user's current GPS coordinates on a map
- **Location Updates**: Automatically updates the marker as the device moves
- **Permission Handling**: Properly requests and handles location permissions at runtime
- **Visual Feedback**: Shows latitude and longitude in toast messages
- **Interactive Map**: Users can zoom and pan the map interface

## Permissions Used

The app requires the following permissions:

### ACCESS_FINE_LOCATION
- **Purpose**: Provides precise GPS-based location data
- **Usage**: Enables high-accuracy location tracking using GPS satellites
- **Privacy**: Requested at runtime; user can deny access

### ACCESS_COARSE_LOCATION
- **Purpose**: Provides approximate location using network-based positioning
- **Usage**: Fallback option for location when GPS is unavailable
- **Privacy**: Requested at runtime; user can deny access

### INTERNET
- **Purpose**: Required for loading Google Maps tiles
- **Usage**: Downloads map data from Google's servers

## How GPS Location is Obtained

### Location Provider
The app uses **FusedLocationProviderClient** from Google Play Services, which intelligently combines multiple location sources:

1. **GPS Satellites**: Provides high accuracy (5-10 meters) but uses more battery
2. **Network-based**: Uses Wi-Fi and cellular towers for faster, less accurate positioning
3. **Sensor Fusion**: Combines accelerometer and other sensors for improved accuracy

### Location Request Configuration
- **Priority**: `PRIORITY_HIGH_ACCURACY` for GPS-based positioning
- **Update Interval**: 5 seconds (5000ms)
- **Callback**: Receives location updates asynchronously

### Location Update Flow
1. App checks if location permissions are granted
2. If not granted, requests permissions from user
3. Once granted, enables location tracking on the map
4. Requests location updates from FusedLocationProviderClient
5. Receives location updates via LocationCallback
6. Updates marker position and camera on the map
7. Displays coordinates in a toast message

## Setup Instructions

### Prerequisites
- Android Studio (latest version)
- Android device or emulator
- **NO API KEY REQUIRED** - Uses free OpenStreetMap!

### Installation
1. Clone this repository
2. Open the project in Android Studio
3. Sync Gradle files (wait for dependencies to download)
4. Run the app on a device or emulator
5. That's it! No API key or payment info needed!

### Testing
- **Physical Device**: Recommended for accurate GPS testing
- **Emulator**: Use the emulator's location controls to simulate movement
- **No Setup Required**: Just run and grant location permission!

## Screenshots

### Permission Request
![Permission Request](screenshots/permission_request.png)
*The app requests location permissions when first launched*

### Map with Location
![Map Location](screenshots/map_location.png)
*User's current location displayed on the map with a marker*

### Location Update
![Location Update](screenshots/location_update.png)
*The marker updates as the user moves, showing real-time tracking*

## Technical Implementation

### Key Components
- **MainActivity**: Main activity that hosts the map and handles permissions
- **FusedLocationProviderClient**: Manages location updates
- **OSMDroid MapView**: Displays the interactive OpenStreetMap interface
- **LocationCallback**: Receives location updates asynchronously
- **ActivityResultContracts**: Modern permission request handling

### Dependencies
- OSMDroid: `org.osmdroid:osmdroid-android:6.1.18` (100% FREE!)
- Google Play Services Location: `com.google.android.gms:play-services-location:21.3.0`
- AndroidX Core KTX
- Material Design Components

## Privacy & Best Practices

✅ **Runtime Permissions**: Requests permissions only when needed
✅ **Graceful Degradation**: Handles permission denial without crashing
✅ **Battery Efficiency**: Removes location updates when app is paused
✅ **User Feedback**: Clearly shows location coordinates and updates
✅ **Secure API Key**: API key should be restricted in production

## Common Issues & Solutions

### App crashes on launch
- Ensure internet connection is available
- Check that location permissions are granted

### Location not updating
- Verify location permissions are granted
- Enable location services in device settings
- Check that GPS is enabled (not just Wi-Fi positioning)

### Map shows blank/gray tiles
- Verify internet connection
- Check that INTERNET permission is in the manifest
- Wait a few seconds for tiles to download

## Future Enhancements (Not Implemented)

- Background location tracking
- Location history and route tracking
- Geofencing capabilities
- Multiple location markers
- Distance and speed calculations
- Save locations to database

## Why OpenStreetMap?

This app uses **OpenStreetMap (OSMDroid)** instead of Google Maps because:
- ✅ **100% FREE** - No payment info required
- ✅ **No API Key** - Works immediately
- ✅ **Open Source** - Community-driven maps
- ✅ **No Limits** - Unlimited map loads
- ✅ **Privacy** - No tracking by Google

Perfect for student projects and learning!

## License

This project is created for educational purposes as part of a Mobile Development course.

## Author

[Your Name]
Mobile Development Student

---

**Note**: This app uses OpenStreetMap, which is completely free and requires no API key or payment information!
