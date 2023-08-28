package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox

class ListaCiudadesActivity : AppCompatActivity() {
    lateinit var btnMostrar : Button
    lateinit var btnVolver: Button
    lateinit var cbBA: CheckBox
    lateinit var cbRos: CheckBox
    lateinit var cbCor: CheckBox
    lateinit var cbSal: CheckBox
    lateinit var cbEsc: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ciudades)
        cbBA=findViewById(R.id.cbBA)
        cbRos=findViewById(R.id.cbRos)
        cbCor=findViewById(R.id.cbCor)
        cbSal=findViewById(R.id.cbSal)
        cbEsc=findViewById(R.id.cbEsc)

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