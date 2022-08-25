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
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore

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

        // Crear hashmaps
        val perrito = hashMapOf(
            "nombre" to binding.nombrePerrito.text.toString(),
            "edad" to binding.edadPerrito.text.toString()
        )

        // 1er paso - obtener referencia a coleccion
        val coleccion : CollectionReference =
            Firebase.firestore.collection("perritos")

        // 2do paso - solicitar guardar dato
        val taskAdd = coleccion.add(perrito)

        taskAdd.addOnSuccessListener { doc ->
            Toast.makeText(this, "id: ${doc.id}", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show()

            Log.e("FIRESTORE",  "error: $error")
        }
    }

    fun queryFirestore(view: View?) {
        // Solicitar datos en firestore

        // 1 - query "tradicional", no real time, solicitamos datos, recibimos, fin de comunicacion
        val collection = Firebase.firestore.collection("perritos")

        val queryTask = collection.get()

        queryTask.addOnSuccessListener { res ->
            // Solicitud exitosa
            Toast.makeText(this, "QUERY EXITOSO", Toast.LENGTH_SHORT).show()

            for(docActual in res) {
                Log.d(
                    "FIRESTORE",
                    "${docActual.id} ${docActual.data}"
                )
            }
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "ERROR AL OBTENER DATOS", Toast.LENGTH_SHORT).show()

            Log.e("FIRESTORE",  "error: $error")
        }

        // 2 - escuchando updates en tiempo real, suscribimos a una collection y escuchamos cambios
        collection.addSnapshotListener{ datos, e ->
            // Verificamos si hay excepcion
            if(e != null) {

                // terminar ejecucion de metodo
                // comandos con @ - limitados a scope
                return@addSnapshotListener
            }

            // si llegamos aqui no hubo excepcion, todo OK

            Log.d("FIRESTORE", "HUBO CAMBIOS")


            // !! - assert non-nullable
            // declarando forzosamente al compilador
            // que una llamada no es nula (aunque si pueda ser)
            for(cambios in datos!!.documentChanges){

                when(cambios.type){
                    DocumentChange.Type.ADDED ->
                        Log.d("FIRESTORE REALTIME",
                        "agregado: ${cambios.document.data}")

                    DocumentChange.Type.MODIFIED ->
                        Log.d("FIRESTORE REALTIME",
                            "modificado: ${cambios.document.data}")

                    DocumentChange.Type.REMOVED ->
                        Log.d("FIRESTORE REALTIME",
                            "removido: ${cambios.document.data}")
                }
            }
        }
    }
}