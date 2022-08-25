package com.example.firebasepm

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.firebasepm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun registrar(view: View?) {
        var emailStr = binding.email.text.toString()
        var pwdStr = binding.password.text.toString()
        var authTask = Firebase.auth.createUserWithEmailAndPassword(emailStr, pwdStr)

        authTask.addOnCompleteListener(this) { resultado ->

            if(resultado.isSuccessful) {
                Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show()
            } else {
                Log.wtf("FIREBASE-DEV", "error: ${resultado.exception}")
            }
        }
    }

    fun login(view: View?) {
        var emailStr = binding.email.text.toString()
        var pwdStr = binding.email.text.toString()
        var authTask = Firebase.auth.signInWithEmailAndPassword(emailStr, pwdStr)

        authTask.addOnCompleteListener(this) { resultado ->
            if(resultado.isSuccessful) {

                Toast.makeText(this, "LOGIN EXITOSO", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "ERROR EN LOGIN", Toast.LENGTH_SHORT).show()
                Log.wtf("FIREBASE-DEV", "error: ${resultado.exception?.message}")
            }
        }
    }

    fun logout(view: View?) {
        Toast.makeText(this, "LOGOUT EXITOSO", Toast.LENGTH_SHORT).show()
        Firebase.auth.signOut()
    }

    fun verificarUsuario() {

        // OJO - para su aplicacion va a ser necesario que verifiquen la validez del usuario actual
        if(Firebase.auth.currentUser == null) {
            // Significa que hay necesidad de revalidar el usuario
            // Puedes redireccionar o terminar esta actividad
            Toast.makeText(this, "REVALIDA!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "USUARIO: ${Firebase.auth.currentUser?.email}", Toast.LENGTH_SHORT).show()
        }
    }

    // Funcionalidad comun
    // Verificar validez de usuario en el ciclo de vida
    override fun onStart() {
        super.onStart()

        verificarUsuario()
    }

    fun verificarUsuarioGUI (view: View?) {
        verificarUsuario()
    }

    fun registrarFirestore(view : View?) {

    }

    fun queryFirestore(view: View?) {
        
    }
}