package com.example.tp_g11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PronosticoAdaptador(private val listaDia: List<Pronostico>): RecyclerView.Adapter<PronosticoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PronosticoViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return PronosticoViewHolder(inflater.inflate(R.layout.item_clima,parent,false))
    }

    override fun onBindViewHolder(holder: PronosticoViewHolder, position: Int) {
        val item=listaDia[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return 3
    }
}