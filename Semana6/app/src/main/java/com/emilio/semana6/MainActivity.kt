package com.emilio.semana6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

// shared prefs
// prefs - preferencias
// mecanismo para almacenamiento local
// almacenan informacion en un archivo XML local
// sirven para cargarla en runtime ocmo un objeto en kotlin

class MainActivity : AppCompatActivity() {

    private val PREFS_ARCHIVO = "prefs.xml"
    private val PREFS_NOMBRE = "nombre"
    private val PREFS_EDAD = "edad"
    private val PREFS_CIUDAD = "ciudad"

    lateinit var sharedPrefsIn : EditText
    lateinit var sharedPrefsOut : TextView
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // obtener referencia a widgets
        sharedPrefsIn = findViewById(R.id.mainSharedPrefIn)
        sharedPrefsOut = findViewById(R.id.mainSharedPrefOut)


        // Obtener objeto shared preferences
        // que contendra las tuplas llave-valor que conformen el archivo XML

        sharedPrefs = getSharedPreferences(PREFS_ARCHIVO, Context.MODE_PRIVATE)

        // escritura de prueba de shared prefs
        // primero necesitamos obtener un objeto para edicion
        with(sharedPrefs.edit()) {
            putString(PREFS_NOMBRE, "Pepito")

            // 2 opciones paa ver cambios reflejados

            // apply actualiza en memoria
            // apply()

            // 2da opcion es commit
            // actualiza en achivo
            // puede bloquar ejecucion
            commit()
        }

        sharedPrefsOut.text = sharedPrefs.getString(PREFS_NOMBRE, "no hay nombre todavia")
        Toast.makeText(this, getString(R.string.main_toast), Toast.LENGTH_SHORT).show()
    }

    fun guardar(v: View?) {

        with(sharedPrefs.edit()) {

            putString(PREFS_CIUDAD, sharedPrefsIn.text.toString())
            commit()
        }
    }

    fun borrarCiudad(v: View?) {
        with(sharedPrefs.edit()) {
            remove(PREFS_CIUDAD)
            commit()
        }
    }

    fun borrarTodo(v:View?) {

        with(sharedPrefs.edit()) {
            clear()
            commit()
        }
    }

    fun mostrar(v:View?) {
        // lectura de prueba de shared prefs

        Toast.makeText(
            this,
            sharedPrefs.getString(PREFS_NOMBRE, "nombre"),
            Toast.LENGTH_SHORT
        ).show()

        // lectura de prueba de shared prefs

        Toast.makeText(
            this,
            sharedPrefs.getString(PREFS_CIUDAD, "ciudad"),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun cambiarAPhoto(v:View?) {
        val intent = Intent(this, PhotoActivity::class.java)
        startActivity(intent)
    }
}