package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson


class ListaProvinciasActivity : AppCompatActivity() {
    lateinit var btnVolveraLista: Button
    lateinit var btnVolverMain : Button

    val gson = Gson()○

    lateinit var listaProvincias: List<Base>
    lateinit var dataFiltrada: List<Base>
    lateinit var provinciaSeleccionada: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_provincias)


        val vistaReciclada = findViewById<RecyclerView>(R.id.recyclerProvincias)

        vistaReciclada.layoutManager = LinearLayoutManager(this)

        val recyclerViewClickListener = RecyclerViewClickListener(
            this,
            vistaReciclada,
            object : RecyclerViewClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    provinciaSeleccionada = dataFiltrada.get(position).province // Obtener la provincia seleccionada
                    iniciarListaCiudadesActivity(filtrarListaCiudadesProvincia(listaProvincias, provinciaSeleccionada.toString()))

                }
            })

        vistaReciclada.addOnItemTouchListener(recyclerViewClickListener)

        val servicioSMN = retrofit.create(SMNService::class.java)
        servicioSMN.getClima().enqueue(object : Callback<List<Base>> {


            override fun onResponse(call: Call<List<Base>>, response: Response<List<Base>>) {
                if (response.isSuccessful) {

                    listaProvincias = response.body() ?: emptyList()
                    dataFiltrada = filtrarProvincias(listaProvincias) //filtrar la lista para que figure solamente una provincia, y de manera alfabetica.
                    vistaReciclada.adapter = ProvinciaAdaptador(dataFiltrada)



                } else {
                    // Handle the error response
                }
            }

            override fun onFailure(call: Call<List<Base>>, t: Throwable) {
                // Handle network failure
            }
        })

        btnVolveraLista = findViewById(R.id.btnVolveraLista)
        btnVolveraLista.setOnClickListener {
            val intentLista=Intent(this,ListaProvinciasActivity::class.java)
            startActivity(intentLista)
            finish()
        }

        btnVolverMain= findViewById(R.id.btnVolverMain)
        btnVolverMain.setOnClickListener {
            val intentMain=Intent(this,MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }


    }

    private fun iniciarListaCiudadesActivity(listaCiudades: List<Base>) {
        val intent = Intent(this, ListaCiudadesActivity::class.java)

        val ciudades = gson.toJson(listaCiudades)

        intent.putExtra("Ciudades", ciudades)
        startActivity(intent)

    }
}



fun filtrarProvincias(data: List<Base>): List<Base> { //Devolver una provincia unica para el Recycler View
    val provinciaUnica = HashSet<String>()
    // Sort the data based on the "province" property
    val sortedData = data.sortedBy { it.province }


    // Create a new list with unique provinces
    val filteredData = sortedData.filter {
        val isUnique = provinciaUnica.add(it.province)
        isUnique
    }

    return filteredData

}

fun filtrarListaCiudadesProvincia(data: List<Base>, provincia: String): List<Base> {
    return data.filter { it.province == provincia }

}