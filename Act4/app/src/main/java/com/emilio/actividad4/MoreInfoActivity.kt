package com.emilio.actividad4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MoreInfoActivity : AppCompatActivity() {

    private lateinit var nombre : TextView
    private lateinit var anio : TextView
    private lateinit var imagen : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        nombre = findViewById(R.id.nombreMoreInfo)
        anio = findViewById(R.id.anioMoreInfo)
        imagen = findViewById(R.id.urlMoreInfo)

        nombre.text = intent.getStringExtra("nombre")
        anio.text = intent.getStringExtra("anio")
        imagen.text = intent.getStringExtra("imagen")
    }


    fun irPaAtras(view: View?) {
        finish()
    }
}