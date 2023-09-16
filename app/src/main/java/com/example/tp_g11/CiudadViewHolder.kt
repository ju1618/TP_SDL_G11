package com.example.tp_g11
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CiudadViewHolder(view : View): ViewHolder(view){
    val nombreCiudad = view.findViewById<TextView>(R.id.ciudades)
    fun render(ciudad: Ciudad){
        //cambiamos los valores en el layout
        nombreCiudad.text=ciudad.nombre
    }
}