package com.example.actividad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import android.view.View
import android.content.Intent
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var db : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        db.saveGreet(1, "Hello!")
        db.saveGreet(2, "Glad to have you back")

        var greeting1 = findViewById<TextView>(R.id.greetingText1)
        greeting1.text = db.getGreet(1)
        var greeting2 = findViewById<TextView>(R.id.greetingText2)
        greeting2.text = db.getGreet(2)

    }

    fun switchHobby (view: View?) {
        val intent = Intent(this, Hobbies::class.java)
        startActivity(intent)
    }

    fun switchFriends (view: View?) {
        val intent = Intent(this, Friends::class.java)
        startActivity(intent)
    }
}
