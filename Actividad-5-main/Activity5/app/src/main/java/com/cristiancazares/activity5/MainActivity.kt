package com.cristiancazares.activity5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private val PREFS_FILE = "prefs.xml"
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefs = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
    }


    fun switchToMaps(view: View?){
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun deleteAll(view: View?){
        with(sharedPrefs.edit()){
            clear()
            commit()
        }
    }
}