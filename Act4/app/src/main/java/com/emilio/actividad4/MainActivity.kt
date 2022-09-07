package com.emilio.actividad4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "request"
    private lateinit var queue: RequestQueue
    lateinit var recyclerView: RecyclerView
    lateinit var datos : ArrayList<ArrayList<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)

    }

    override fun onStop() {
        super.onStop()

        // si hacemos stop lo ideal es detener la queue
        queue.cancelAll(TAG)
    }

    // NOTA DE ESTE METODO
    // el argumento que recibimos es el widget que mando llamar a este metodo
    override fun onClick(row: View) {
        // tenemos un solo escucha para todos los rows!
        // podemos obtener la ubicacion de una row con referencia a la vista
        val position = recyclerView.getChildLayoutPosition(row)
        Toast.makeText(
            this,
            datos[position][0],
            Toast.LENGTH_SHORT
        ).show()

        val intent= Intent(this, MoreInfoActivity::class.java)
        intent.putExtra("nombre",datos[position][0])
        intent.putExtra("anio",datos[position][1])
        intent.putExtra("imagen",datos[position][2])

        startActivity(intent)
    }

    fun jalarDatos(view: View?) {

        datos = ArrayList()
//        datos.add("Fido")
//        datos.add("Fifi")
//        datos.add("Firulais")
//        datos.add("Solovino")

        val url = "https://bitbucket.org/itesmguillermorivas/partial2/raw/992b45809954609ff8521e14f8f70f359c68a3ea/videojuegos.json"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                // procesar logica del json array
                for(i in 0 until response.length()) {
                    val actual = response.getJSONObject(i)
                    Log.wtf("JSON-REQUEST", actual.getString("nombre"))
                    Log.wtf("JSON-REQUEST", actual.getString("anio"))
                    Log.wtf("JSON-REQUEST", actual.getString("imagen"))

                    val datosActuales = ArrayList<String>()
                    datosActuales.add(actual.getString("nombre"))
                    datosActuales.add(actual.getString("anio"))
                    datosActuales.add(actual.getString("imagen"))

                    datos.add(datosActuales)

                    val plataformas = actual.getJSONArray("plataformas")
                    for(j in 0 until plataformas.length()) {

                        Log.wtf("JSON-REQUEST", plataformas.getString(j))

                    }
                }
                // ADAPTER
                val adapter = InfoAdapter(datos,this)

                // GUI
                recyclerView = findViewById(R.id.reclyclerView)

                var llm = LinearLayoutManager(this)
                llm.orientation = LinearLayoutManager.VERTICAL
                var glm = GridLayoutManager(this, 3)

                // terminamos asignando al recycler view referencias a objetos necesarios
                recyclerView.layoutManager = llm
                recyclerView.adapter = adapter
            },
            { error ->
                Toast.makeText(
                    this,
                    "error: $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply {
            tag = TAG
        }

        queue.add(jsonArrayRequest)

    }
}