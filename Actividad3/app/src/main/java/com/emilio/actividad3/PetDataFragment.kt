package com.emilio.actividad3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PET_NOMBRE = "petNombre"
private const val PET_EDAD= "petEdad"

/**
 * A simple [Fragment] subclass.
 * Use the [PetDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PetDataFragment : Fragment() {
    private var petNombre: String? = null
    private var petEdad: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            petNombre = it.getString(PET_NOMBRE)
            petEdad = it.getInt(PET_EDAD)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pet_data, container, false)

        view.findViewById<TextView>(R.id.petName).apply {
            text = petNombre
        }

        view.findViewById<TextView>(R.id.petAge).apply {
            text = petEdad.toString()
        }

        return view
    }

    companion object {

        // método estático para creación de instancias
        // PROBLEMA - los fragmentos están obligados a tener un constructor
        // default (sin argumentos)
        // esto se vuelve problema cuando necesitamos argumentos!

        // factory
        // - https://en.wikipedia.org/wiki/Factory_method_pattern

        @JvmStatic
        fun newInstance(nombre: String, edad: Int) : PetDataFragment {

            val perrito = PetDataFragment()
            val datos = Bundle()
            datos.putString(PET_NOMBRE, nombre)
            datos.putInt(PET_EDAD, edad)
            perrito.arguments = datos
            return perrito
        }
    }
}