package com.example.actividad2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Friends : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var hobby: EditText
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        db = DBHelper(this)

        name = findViewById(R.id.name)
        hobby = findViewById(R.id.hobbyPerson)
    }

    fun saveFriend(view: View?) {
        db.savePerson(name.text.toString(), hobby.text.toString())
        Toast.makeText(this, "Saving data...", Toast.LENGTH_SHORT).show()
    }

    fun searchFriend(view: View?) {
        val nameExists = db.findPerson(name.text.toString(),2)
        if (nameExists!=""){
            hobby.setText(nameExists)
            Toast.makeText(this, nameExists,Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "This name does not exist",Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteFriend(view: View?){
        db.deletePerson(name.text.toString())
        Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show()
    }
}