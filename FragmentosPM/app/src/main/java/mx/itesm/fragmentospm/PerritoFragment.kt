package mx.itesm.fragmentospm

import android.content.Context
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.RuntimeException

private const val NOMBRE = "nombre"
private const val EDAD = "edad"

/**
 * A simple [Fragment] subclass.
 * Use the [PerritoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerritoFragment : Fragment() {

    private var nombre: String? = null
    private var edad: Int? = null

    // vamos a estar usando observer
    private var listener: Callback? = null

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
        val view = inflater.inflate(R.layout.fragment_perrito, container, false)

        view.findViewById<TextView>(R.id.perritoNombre).apply {
            text = nombre
        }

        view.findViewById<TextView>(R.id.perritoEdad).apply {
            text = edad.toString()
        }

        view.findViewById<Button>(R.id.perritoBoton).setOnClickListener {

            listener?.ejecutar()
        }

        return view
    }

    // vamos a usar un metodo del ciclo de vida para asignar al listener
    // metodo que forma parte de la vida de un fragmento
    // se invoca al anexarlo a la actividad
    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if(context is Callback){
            context
        } else {
            throw RuntimeException("ES NECESARIO IMPLEMENTAR LA INTERFAZ")
        }
    }

    // Interfaz es la definicion de un tipo
    // Para definir un tipo usamos una clase "regular", clase abstracta a interfaz

    // interfaz es un tipo en donde no hay implementacion
    // solo forzamos una firma en otro tipo
    // sirve para definir actividades que sabemos se tienen que hacer pero no especificamos
    // el "como"

    interface Callback {

        // aqui nada mas ponemos la firma de los metodos
        // firma: nombre, lista de parametros (en orden), tipo de retorno
        fun ejecutar()
    }

    companion object {

        // método estático para creación de instancias
        // PROBLEMA - los fragmentos están obligados a tener un constructor
        // default (sin argumentos)
        // esto se vuelve problema cuando necesitamos argumentos!

        // factory
        // - https://en.wikipedia.org/wiki/Factory_method_pattern

        @JvmStatic
        fun newInstance(nombre: String, edad: Int) : PerritoFragment {

            val perrito = PerritoFragment()
            val datos = Bundle()
            datos.putString(NOMBRE, nombre)
            datos.putInt(EDAD, edad)
            perrito.arguments = datos
            return perrito
        }
        /*
         = PerritoFragment().apply {
                arguments = Bundle().apply {
                    putString(NOMBRE, nombre)
                    putInt(EDAD, edad)
                }
            }
         */
    }
}