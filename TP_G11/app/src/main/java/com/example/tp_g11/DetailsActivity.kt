package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.w3c.dom.Text

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

        ciudadTexto = findViewById<TextView>(R.id.nombreCiudad)
        temp = findViewById<TextView>(R.id.temp)
        tempDesc = findViewById<TextView>(R.id.tempDesc)

        val json = intent.getStringExtra("Ciudad")
        val tipoCiudad = object : TypeToken<Base>() {}.type
        val ciudad: Base = gson.fromJson(json, tipoCiudad)
        val condicion: Int = ciudad.weather.id

        //TODO falta hacer el resto del pronostico
        //TODO falta poner el resto de los diferentes climas
        //TODO sacar los checkbox y poner algo mejor

        when (condicion) {
            1 -> { //Algo nublado
                imagenClima.setImageResource(R.drawable.algo_nublado)
                imagenClima.visibility = View.VISIBLE
            }
            2 -> { //Parcialmente nublado
                imagenClima.setImageResource(R.drawable.parcialmente_nublado)
                imagenClima.visibility = View.VISIBLE
         /*   } 10 -> { //Cubierto con neblina
            imagenClima.setImageResource(R.drawable.cubierto_con_neblina)
            imagenClima.visibility = View.VISIBLE
        */}
        }

        ciudadTexto.text = ciudad.name
        temp.text = ciudad.weather.tempDesc
        tempDesc.text = ciudad.weather.description


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
