package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {
    lateinit var btnVolveraLista: Button
    lateinit var btnVolverMain : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        btnVolveraLista = findViewById(R.id.btnVolveraLista)
        btnVolveraLista.setOnClickListener {
            val intentLista=Intent(this,ListaCiudadesActivity::class.java)
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
