package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaCiudadesActivity : AppCompatActivity() {
    lateinit var btnMostrar : Button
    lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ciudades)

        val vistaReciclada = findViewById<RecyclerView>(R.id.recyclerCiudades)
        vistaReciclada.layoutManager= LinearLayoutManager(this)
        vistaReciclada.adapter=CiudadAdaptador(CiudadesProvider.ciudadesLista)

        btnMostrar=findViewById(R.id.btnMostrar)

        btnMostrar.setOnClickListener {
            val intentMostrar = Intent(this, DetailsActivity::class.java)

            //if(cbBA.isChecked){
            //   intentMostrar.putExtra("ciudad", "Buenos Aires")
            //}

            startActivity(intentMostrar)
            finish()
        }

        btnVolver=findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intentVolver=Intent(this,MainActivity::class.java)
            startActivity(intentVolver)
            finish()
        }
    }
}