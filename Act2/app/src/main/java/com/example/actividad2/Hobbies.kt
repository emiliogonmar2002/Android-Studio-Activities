package com.example.actividad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Hobbies : AppCompatActivity() {

    private lateinit var textview: TextView
    private lateinit var hobbyInput: EditText
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobbies)

        db = DBHelper(this)
        textview = findViewById(R.id.hobbyText)
        hobbyInput = findViewById(R.id.hobbyInput)
        updateHobby()
    }

    fun saveHobby(view: View?) {
        db.updatePerson("UserMain", hobbyInput.text.toString())
        textview.text = hobbyInput.text.toString()
        Toast.makeText(this, "Saving data...", Toast.LENGTH_SHORT).show()
    }

    fun updateHobby(){
        if(db.getHobby() != null){
            textview.text = db.getHobby()
        }
    }
}