package com.emilio.actividad3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NOMBRE = "nombre"
private const val EDAD = "edad"

/**
 * A simple [Fragment] subclass.
 * Use the [DataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataFragment : Fragment() {
    private var nombre: String? = null
    private var edad: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            nombre = it.getString(NOMBRE)
            edad = it.getInt(EDAD)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data, container, false)

        view.findViewById<TextView>(R.id.name).apply {
            text = nombre
        }

        view.findViewById<TextView>(R.id.age).apply {
            text = edad.toString()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(nombre: String, edad: Int) : DataFragment {

            val humano = DataFragment()
            val datos = Bundle()
            datos.putString(NOMBRE, nombre)
            datos.putInt(EDAD, edad)
            humano.arguments = datos
            return humano
        }
    }
}