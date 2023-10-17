package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView

class DetailsActivity : AppCompatActivity() {

    val gson = Gson()


    lateinit var btnVolveraLista: Button
    lateinit var btnVolverMain : Button

    lateinit var temp: TextView
    lateinit var ciudadTexto: TextView
    lateinit var imagenClima: ImageView
    lateinit var tempDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //val pronosticoExtendido = findViewById<RecyclerView>(R.id.pronosticoExtendidoRecyclerView)
        //pronosticoExtendido.layoutManager = LinearLayoutManager(this)


        ciudadTexto = findViewById<TextView>(R.id.nombreCiudad)
        temp = findViewById<TextView>(R.id.temp)
        tempDesc = findViewById<TextView>(R.id.tempDesc)
        imagenClima = findViewById<ImageView>(R.id.imagenClima)

        val json = intent.getStringExtra("Ciudad")
        val tipoCiudad = object : TypeToken<Base>() {}.type
        val ciudad: Base = gson.fromJson(json, tipoCiudad)
        val condicion: Int = ciudad.weather.id

        lateinit var listaPronostico: List<Pronostico>

        //TODO falta hacer el resto del pronostico
        //TODO falta poner el resto de los diferentes climas
        //TODO sacar los checkbox y poner algo mejor

        when (condicion) {
            1 -> { //Algo nublado
                imagenClima.setImageResource(R.drawable.algo_nublado)
            } 2 -> { //Parcialmente nublado
                imagenClima.setImageResource(R.drawable.parcialmente_nublado)
            } 3-> { //Nublado con lloviznas
                imagenClima.setImageResource(R.drawable.llovizna)
            } 4-> {
            imagenClima.setImageResource(R.drawable.tormenta_sin_precipitacion)
            } 5-> {
            imagenClima.setImageResource(R.drawable.nevada)
            } 10 -> { //Cubierto con neblina
            imagenClima.setImageResource(R.drawable.cubierto_con_neblina)
            } else -> {
                imagenClima.setImageResource(R.drawable.soleado)
        }
        }

        ciudadTexto.text = ciudad.name
        temp.text = ciudad.weather.tempDesc
        tempDesc.text = ciudad.weather.description

        val servicioSMN = retrofit.create(SMNService::class.java)

        /*
        val dia1 = servicioSMN.getPronostico(1, ciudad.fid)
        val dia2 = servicioSMN.getPronostico(2, ciudad.fid)
        val dia3 = servicioSMN.getPronostico(3, ciudad.fid)


       dia1.enqueue(object: Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val pronostico1 =  response.body()?.string()
                    if (pronostico1 != null) {

                        val dia1Clima = Gson().fromJson(pronostico1, Pronostico::class.java)


                        //listaPronostico.add(dia1Clima)



                        //val adaptador =  PronosticoAdaptador(listaPronostico)
                        //pronosticoExtendido.adapter = adaptador


                    }






            } else {
                // Handle the error response
            }
        }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            // Handle network failure
        }
    })
    */


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


}
