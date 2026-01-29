# Code Explanation - MainActivity.kt

This document explains the key components of the location tracker implementation.

## 📦 Imports

```kotlin
import android.Manifest  // Permission constants
import com.google.android.gms.location.*  // Location services
import com.google.android.gms.maps.*  // Google Maps
```

## 🏗️ Class Structure

### MainActivity extends AppCompatActivity, implements OnMapReadyCallback

- **AppCompatActivity**: Base class for activities using support library features
- **OnMapReadyCallback**: Interface to receive notification when map is ready

## 🔑 Key Components

### 1. FusedLocationProviderClient
```kotlin
private lateinit var fusedLocationClient: FusedLocationProviderClient
```
**Purpose**: Provides location updates from GPS and network sources
**Why**: Intelligently combines multiple location sources for best accuracy

### 2. GoogleMap
```kotlin
private lateinit var map: GoogleMap
```
**Purpose**: Reference to the map object for displaying location
**Why**: Needed to add markers and move camera

### 3. LocationCallback
```kotlin
private lateinit var locationCallback: LocationCallback
```
**Purpose**: Receives location updates asynchronously
**Why**: Called automatically when device location changes

### 4. Permission Launcher
```kotlin
private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
) { permissions -> ... }
```
**Purpose**: Modern way to request runtime permissions
**Why**: Replaces deprecated onRequestPermissionsResult()

## 🔄 Lifecycle Flow

### onCreate()
1. Inflate layout with ViewBinding
2. Initialize FusedLocationProviderClient
3. Get map fragment and request map asynchronously
4. Setup LocationCallback to handle updates

### onMapReady()
1. Called when map is loaded and ready
2. Check if location permission is granted
3. If yes: enable location features
4. If no: request permission

### onResume()
1. Called when app comes to foreground
2. Restart location updates if permission granted

### onPause()
1. Called when app goes to background
2. Stop location updates to save battery

## 🔐 Permission Handling

### Step 1: Check Permission
```kotlin
private fun hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}
```
**What it does**: Checks if permission is already granted
**Returns**: true if granted, false if not

### Step 2: Request Permission
```kotlin
private fun requestLocationPermission() {
    requestPermissionLauncher.launch(
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
}
```
**What it does**: Shows system permission dialog
**Requests**: Both fine and coarse location

### Step 3: Handle Result
```kotlin
{ permissions ->
    when {
        permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
            enableMyLocation()
        }
        // ... handle other cases
    }
}
```
**What it does**: Processes user's permission decision
**Actions**: Enable location if granted, show message if denied

## 📍 Location Tracking

### Enable Location
```kotlin
private fun enableMyLocation() {
    map.isMyLocationEnabled = true  // Show blue dot
    startLocationUpdates()           // Start tracking
}
```
**What it does**: 
1. Enables blue dot on map showing device location
2. Starts requesting location updates

### Start Location Updates
```kotlin
private fun startLocationUpdates() {
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,  // Use GPS
        5000  // Update every 5 seconds
    ).build()
    
    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        mainLooper
    )
}
```
**What it does**: Configures and starts location updates
**Configuration**:
- **Priority**: HIGH_ACCURACY = GPS-based
- **Interval**: 5000ms = 5 seconds
- **Callback**: locationCallback receives updates
- **Looper**: mainLooper = run on main thread

### Location Callback
```kotlin
locationCallback = object : LocationCallback() {
    override fun onLocationResult(locationResult: LocationResult) {
        locationResult.lastLocation?.let { location ->
            updateLocationOnMap(location)
        }
    }
}
```
**What it does**: Called automatically when location changes
**Action**: Updates marker on map with new location

## 🗺️ Map Updates

### Update Location on Map
```kotlin
private fun updateLocationOnMap(location: Location) {
    val latLng = LatLng(location.latitude, location.longitude)
    
    // Remove old marker
    currentMarker?.remove()
    
    // Add new marker
    currentMarker = map.addMarker(
        MarkerOptions()
            .position(latLng)
            .title("Current Location")
    )
    
    // Move camera
    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    
    // Show coordinates
    Toast.makeText(this, "Lat: ${location.latitude}, Lng: ${location.longitude}", 
                   Toast.LENGTH_SHORT).show()
}
```
**What it does**:
1. Creates LatLng object from location
2. Removes previous marker (prevents duplicates)
3. Adds new marker at current position
4. Animates camera to new position (zoom level 15)
5. Shows toast with coordinates

## 🔧 Key Concepts Explained

### Why FusedLocationProviderClient?
- **Smart**: Automatically chooses best location source
- **Efficient**: Manages battery usage
- **Accurate**: Combines GPS, Wi-Fi, and cellular data

### Why LocationCallback?
- **Asynchronous**: Doesn't block main thread
- **Automatic**: Called when location changes
- **Efficient**: Only runs when needed

### Why Remove Old Marker?
- **Clean UI**: Prevents marker clutter
- **Performance**: Fewer objects to render
- **Clarity**: Shows only current location

### Why Animate Camera?
- **User Experience**: Smooth transition
- **Context**: Keeps user location centered
- **Awareness**: User sees they're being tracked

### Why Stop Updates in onPause()?
- **Battery**: GPS uses significant power
- **Privacy**: Don't track when app not visible
- **Resources**: Free up system resources

## 🎯 Location Update Flow

```
1. App starts
   ↓
2. onCreate() initializes components
   ↓
3. Map loads → onMapReady() called
   ↓
4. Check permission
   ↓
5a. If granted → enableMyLocation()
5b. If not → requestLocationPermission()
   ↓
6. startLocationUpdates()
   ↓
7. FusedLocationProviderClient starts tracking
   ↓
8. Device location changes
   ↓
9. LocationCallback.onLocationResult() called
   ↓
10. updateLocationOnMap() updates UI
   ↓
11. Repeat steps 8-10 every 5 seconds
```

## 🔍 Important Details

### Security Check
```kotlin
try {
    map.isMyLocationEnabled = true
} catch (e: SecurityException) {
    Toast.makeText(this, "Location permission error", Toast.LENGTH_SHORT).show()
}
```
**Why**: Even with permission checks, SecurityException can occur
**Solution**: Wrap in try-catch for safety

### Null Safety
```kotlin
locationResult.lastLocation?.let { location ->
    updateLocationOnMap(location)
}
```
**Why**: lastLocation can be null if no location available
**Solution**: Use safe call (?.) and let to handle null case

### lateinit vs var
```kotlin
private lateinit var map: GoogleMap  // Will be initialized later
private var currentMarker: Marker? = null  // Can be null
```
**lateinit**: For non-null types initialized later
**nullable (?)**: For types that can be null

## 📱 Testing Tips

### On Physical Device
- Walk around outside for GPS signal
- Watch marker follow your movement
- Check toast messages for coordinates

### On Emulator
- Use Extended Controls (...)
- Go to Location tab
- Enter coordinates or click map
- Click "Send" to simulate movement

## 🐛 Common Issues

### Map is blank
- **Cause**: Missing or invalid API key
- **Check**: AndroidManifest.xml has correct key

### No location updates
- **Cause**: Permission denied or GPS disabled
- **Check**: Permission granted and location services enabled

### App crashes
- **Cause**: Missing API key or Google Play Services
- **Check**: API key added and device has Play Services

## 💡 Learning Points

1. **Runtime Permissions**: Must request at runtime for dangerous permissions
2. **Lifecycle Management**: Start/stop updates based on app state
3. **Asynchronous Operations**: Location updates don't block UI
4. **Resource Management**: Clean up resources when not needed
5. **Error Handling**: Always handle permission denial and null cases

## 🎓 Extension Ideas

Want to enhance the app? Try:
- Add distance calculation between points
- Show speed and altitude
- Save location history
- Add geofencing
- Show path traveled
- Calculate total distance

---

**Understanding this code?** You now know how to:
- Request runtime permissions
- Use Google Maps API
- Track device location
- Handle lifecycle events
- Manage resources efficiently
