package com.emilio.actividad4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InfoAdapter(var laditos : ArrayList<ArrayList<String>>,
                     var listener : View.OnClickListener)
    : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    // 1era cosa que hay que hacer
    // ViewHolder
    // ViewHolder es similar al binding: un objeto con referencias a los elementos de una vista

    class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var texto1 : TextView
        var texto2 : TextView

        // bloque de inicializacion
        // bloque de codigo que se corre TODAS las veces que sea crea un objeto

        init {

            texto1 = itemView.findViewById(R.id.nombreRow)
            texto2 = itemView.findViewById(R.id.anioRow)
        }

    }

    // momento de creacion de una vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {

        // igual que con fragmentos inflamos la view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        view.setOnClickListener(listener)

        val viewHolder = InfoViewHolder(view)

        return InfoViewHolder(view)
    }

    // momento de asociacion de vista con datos
    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.texto1.text = laditos[position][0]
        holder.texto2.text = laditos[position][1]
    }

    // obtener la cantidad de datos a mostrar
    override fun getItemCount(): Int {
        return laditos.size
    }
}