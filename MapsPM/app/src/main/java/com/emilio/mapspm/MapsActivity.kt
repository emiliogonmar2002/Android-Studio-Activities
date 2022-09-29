package com.emilio.mapspm

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.emilio.mapspm.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val PERMISO_UBICACION = 0
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // AS - CASTING
        // cambio arbitrario de tipo
        // OJO - si se trata de hacer casting a un tipo no representable tendremos una excepcion en ejecucion
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        // Add a marker in Home and move the camera
        val home = LatLng(37.38550905105642, -121.996906200148)
        mMap.addMarker(MarkerOptions().position(home).title("Marker in HOME"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 14f))

        mMap.setOnMapClickListener { latlng ->

            mMap.addMarker(
                MarkerOptions()
                    .position(latlng)
                    .title("New Marker in ${latlng.toString()}")
                    .alpha(0.5f)
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_ORANGE
                        )
                    )
            )
        }
    }

    fun habilitarMyLocation() {

        // checar si tenemos permiso
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // si el permiso esta dado corremos funcionalidad
            mMap.isMyLocationEnabled = true
        } else {

            // si no esta dado pedimos permiso

            // enlistamos los permisos a solicitar en un arreglo
            val permisos = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permisos, PERMISO_UBICACION)
        }
    }

    // metodo para escuchar respuesta a solicitud de permiso
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // checamos si el origen es el de la ubicacion y si el permiso fue dado
        if(requestCode == PERMISO_UBICACION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
    }
}