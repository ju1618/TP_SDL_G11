package com.example.tp_g11
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CiudadAdaptador(val listaCiudades: List<Ciudad>):RecyclerView.Adapter<CiudadViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return CiudadViewHolder(inflater.inflate(R.layout.item_ciudad,parent,false))
    }

    override fun onBindViewHolder(holder: CiudadViewHolder, position: Int) {
        val item=listaCiudades[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return listaCiudades.size
    }

}