package com.example.actividad2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.widget.Toast

class DBHelper (context : Context?) : SQLiteOpenHelper(context, DB_FILE, null, 1) {
    companion object {
        private const val DB_FILE = "PeopleDB.db"

        // Friends Table
        private const val TABLE_PEOPLE = "people"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_HOBBY = "hobby"

        // Greetings Table
        private const val TABLE_GREETINGS = "greetings"
        private const val COLUMN_GREETING = "greeting"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_PEOPLE(" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_HOBBY TEXT)"
        db?.execSQL(query)

        val queryGreetings = "CREATE TABLE $TABLE_GREETINGS(" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_GREETING TEXT)"
        db?.execSQL(queryGreetings)
    }

    override fun onUpgrade(db: SQLiteDatabase?, anterior: Int, actual: Int) {
        val query = "DROP TABLE IF EXISTS ?"
        val args = arrayOf(TABLE_PEOPLE)
        var args1 = arrayOf(TABLE_GREETINGS)

        db?.execSQL(query, args)
        db?.execSQL(query, args1)

        onCreate(db)
    }

    fun savePerson(name: String, hobby: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_HOBBY, hobby)

        writableDatabase.insert(TABLE_PEOPLE, null, values)
    }

    fun deletePerson(name: String) : Int {
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(name)

        return writableDatabase.delete(TABLE_PEOPLE, clause, args)
    }

    fun updatePerson(name: String, hobby: String) : Int {
        val values = ContentValues()
        values.put(COLUMN_HOBBY, hobby)
        return writableDatabase.update(TABLE_PEOPLE, values, "$COLUMN_NAME = ?", arrayOf(name))
    }

    fun findPerson(name: String, index: Int) : String {
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(name)

        val cursor = readableDatabase.query(TABLE_PEOPLE, null, clause, args, null, null, null)

        var result = ""

        if(cursor.moveToFirst()) {
            result = cursor.getString(index)
        }

        return result
    }

    fun getHobby(): String {
        //Always get the first hobbby
        val query = "SELECT * FROM $TABLE_PEOPLE WHERE id = 1;"
        val cursor = readableDatabase.rawQuery(query, null)

        //If there's a hobby return it, if not return blank
        if(cursor.moveToFirst()){
            return cursor.getString(2) //Returns second column of the query result
        }
        return ""
    }

    fun getGreet(id: Int) : String{
        //Get a Greet from database given an id
        val query = "SELECT * FROM $TABLE_GREETINGS WHERE id = ${id};"
        val cursor = readableDatabase.rawQuery(query, null)

        if(cursor.moveToFirst()){
            return cursor.getString(1) //Returns second column of the query result
        }
        return ""
    }

    fun saveGreet(id: Int, greeting: String){
        //Basically an arbitrary way to populate the "Greets" table with given id's and text.
        val values = ContentValues()
        values.put(COLUMN_ID, id)
        values.put(COLUMN_GREETING, greeting)
        writableDatabase.insert(TABLE_GREETINGS, null, values)
    }
}