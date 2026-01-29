package com.example.simplelocationtrackerapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.simplelocationtrackerapp.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var map: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var currentMarker: Marker? = null

    // Permission launcher
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                enableMyLocation()
            }
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                enableMyLocation()
            }
            else -> {
                Toast.makeText(
                    this,
                    "Location permission denied. Cannot show your location.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize OSMDroid configuration
        Configuration.getInstance().load(
            this,
            PreferenceManager.getDefaultSharedPreferences(this)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Setup map
        map = binding.map
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        map.controller.setZoom(15.0)

        // Setup location callback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    updateLocationOnMap(location)
                }
            }
        }

        // Check and request permissions
        when {
            hasLocationPermission() -> {
                enableMyLocation()
            }
            else -> {
                requestLocationPermission()
            }
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun enableMyLocation() {
        if (!hasLocationPermission()) {
            return
        }

        try {
            startLocationUpdates()
        } catch (e: SecurityException) {
            Toast.makeText(this, "Location permission error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startLocationUpdates() {
        if (!hasLocationPermission()) {
            return
        }

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            5000 // Update every 5 seconds
        ).build()

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                mainLooper
            )

            // Get last known location to initialize map
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    updateLocationOnMap(it)
                }
            }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Location permission error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLocationOnMap(location: Location) {
        val geoPoint = GeoPoint(location.latitude, location.longitude)
        
        // Remove old marker
        currentMarker?.let {
            map.overlays.remove(it)
        }
        
        // Add new marker
        currentMarker = Marker(map).apply {
            position = geoPoint
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            title = "Current Location"
            snippet = "Lat: ${String.format("%.4f", location.latitude)}, " +
                     "Lng: ${String.format("%.4f", location.longitude)}"
        }
        map.overlays.add(currentMarker)
        
        // Move camera to current location
        map.controller.animateTo(geoPoint)
        
        // Refresh map
        map.invalidate()
        
        // Show toast with coordinates
        Toast.makeText(
            this,
            "Lat: ${String.format("%.4f", location.latitude)}, " +
                    "Lng: ${String.format("%.4f", location.longitude)}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
        if (hasLocationPermission()) {
            startLocationUpdates()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDetach()
    }
}
