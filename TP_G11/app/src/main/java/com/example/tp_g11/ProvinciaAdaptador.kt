package com.example.tp_g11
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProvinciaAdaptador(private val listaProvincia: List<Base>):RecyclerView.Adapter<ProvinciaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return ProvinciaViewHolder(inflater.inflate(R.layout.item_provincia,parent,false))
    }

    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        val item=listaProvincia[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return listaProvincia.size
    }



}