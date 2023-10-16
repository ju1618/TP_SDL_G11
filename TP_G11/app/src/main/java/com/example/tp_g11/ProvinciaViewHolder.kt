package com.example.tp_g11
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ProvinciaViewHolder(view : View): ViewHolder(view){
    val nombreProvincia = view.findViewById<TextView>(R.id.provincias)
    fun render(provincia: Base){
        //cambiamos los valores en el layout
        nombreProvincia.text=provincia.province
    }
}