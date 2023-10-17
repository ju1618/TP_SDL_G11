package com.example.tp_g11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.util.Log

class CiudadAdaptador(private val listaCiudad: List<Base>): RecyclerView.Adapter<CiudadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return CiudadViewHolder(inflater.inflate(R.layout.item_ciudad,parent,false))
    }

    override fun onBindViewHolder(holder: CiudadViewHolder, position: Int) {
        val item=listaCiudad[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {

        return listaCiudad.size
    }
}