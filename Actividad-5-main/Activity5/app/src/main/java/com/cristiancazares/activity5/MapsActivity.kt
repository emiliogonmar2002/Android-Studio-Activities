package com.cristiancazares.activity5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cristiancazares.activity5.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val PREFS_FILE = "prefs.xml"
    private val PREFS_POS = "pos"
    private var PREFS_COUNT = "count"
    var count = 0
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        count = sharedPrefs.getString(PREFS_COUNT, "0")!!.toInt()



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

        val classroom = LatLng(20.734752245761037, -103.45739214109862)
        mMap.addMarker(MarkerOptions().position(classroom).title("Edificio 4"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(classroom, 15f))

        for (i in 1..count){
            var currentPos = sharedPrefs.getString("${PREFS_POS}${i}", "")
            val latlong = currentPos!!.split(",").toTypedArray()
            val latitude = latlong[0].toDouble()
            val longitude = latlong[1].toDouble()
            var pos = LatLng(latitude, longitude)
            Log.wtf("POS_TEST", currentPos)
            mMap.addMarker(
                MarkerOptions()
                    .position(pos)
                    .title("")
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    )
            )
        }

        mMap.setOnMapClickListener { pos ->
            var posStr = "${pos.latitude},${pos.longitude}"
            mMap.addMarker(
                MarkerOptions()
                    .position(pos)
                    .title("")
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    )
            )

            Toast.makeText(
                this,
                pos.toString(),
                Toast.LENGTH_SHORT
            ).show()

            savePos(posStr)
        }

        mMap.setOnMarkerClickListener { marker ->
            Toast.makeText(
                this,
                marker.position.toString(),
                Toast.LENGTH_SHORT
            ).show()
            true
        }
    }

    fun savePos(pos: String){
        count += 1
        var currentPos = "${PREFS_POS}${count}"
        with(sharedPrefs.edit()){
            putString(currentPos, pos)
            commit()
        }
        with(sharedPrefs.edit()){
            putString(PREFS_COUNT, count.toString())
            commit()
        }
    }
}