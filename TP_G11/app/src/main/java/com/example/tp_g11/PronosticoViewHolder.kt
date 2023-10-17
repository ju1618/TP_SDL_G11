package com.example.tp_g11

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PronosticoViewHolder(view : View): RecyclerView.ViewHolder(view) {

    val nombreCiudad = view.findViewById<TextView>(R.id.ciudades)
    fun render(ciudad: Pronostico){
        //cambiamos los valores en el layout
        //nombreCiudad.text = ciudad.name
    }
}