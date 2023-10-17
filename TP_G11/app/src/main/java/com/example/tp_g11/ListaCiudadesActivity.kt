package com.example.tp_g11

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import android.content.Intent
import com.google.gson.reflect.TypeToken
import android.util.Log
import android.widget.Button


class ListaCiudadesActivity: AppCompatActivity() {

    lateinit var btnVolveraLista: Button
    lateinit var btnVolverMain : Button

    val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ciudades)

        val json = intent.getStringExtra("Ciudades")
        val tipoCiudad = object : TypeToken<List<Base>>() {}.type
        val ciudadJson:  List<Base> = gson.fromJson(json, tipoCiudad)
        val ciudades = ordenarCiudades(ciudadJson)

        val vistaCiudades =  findViewById<RecyclerView>(R.id.recyclerCiudades)

        vistaCiudades.layoutManager = LinearLayoutManager(this)


        val recyclerViewClickListener = RecyclerViewClickListener(
            this,
            vistaCiudades,
            object : RecyclerViewClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val ciudadSeleccionada = ciudades?.get(position) // Obtener la ciudad seleccionada
                    Log.i("CIUDADSELECCIONADA:", ciudadSeleccionada.toString())
                    iniciarDetalleActivity(ciudadSeleccionada)

                }
            })

        vistaCiudades.adapter = CiudadAdaptador(ciudades)

        vistaCiudades.addOnItemTouchListener(recyclerViewClickListener)

        btnVolveraLista = findViewById(R.id.btnVolveraLista)
        btnVolveraLista.setOnClickListener {
            val intentLista=Intent(this,ListaProvinciasActivity::class.java)
            startActivity(intentLista)
            finish()
        }

        btnVolverMain=findViewById(R.id.btnVolverMain)
        btnVolverMain.setOnClickListener {
            val intentMain=Intent(this,MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }


    }

private fun iniciarDetalleActivity(ciudadSeleccionada: Base?) {
    val intent = Intent(this, DetailsActivity::class.java)

    val cSeleccionada = gson.toJson(ciudadSeleccionada)
    intent.putExtra("Ciudad", cSeleccionada)
    startActivity(intent)

}

    fun ordenarCiudades(data: List<Base>): List<Base> { //Ordenar la lista de manera alfabeticamente

        return data.sortedBy { it.name }

    }

}