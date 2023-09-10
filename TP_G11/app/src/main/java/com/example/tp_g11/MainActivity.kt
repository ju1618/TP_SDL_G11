package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var btnCrearMain : Button
    lateinit var btnIniciarMain : Button
    lateinit var btnListaCiudades: Button
    lateinit var toolbar : Toolbar

    // Funcion que se ejecuta al iniciar un Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);

        saludarUsuario()

        btnCrearMain = findViewById(R.id.btnCrearMain)

        btnCrearMain.setOnClickListener {
            val intentCreate = Intent(this, CrearUsuarioActivity::class.java)
            startActivity(intentCreate)
            finish()
        }




        btnIniciarMain = findViewById(R.id.btnIniciarMain)

        btnIniciarMain.setOnClickListener{
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            finish()

        }

        btnListaCiudades = findViewById(R.id.btnListaCiudades)

        btnListaCiudades.setOnClickListener{
            val intentMostrarLista = Intent(this,ListaCiudadesActivity::class.java)
            startActivity(intentMostrarLista)
            finish()
        }

    }

    private fun saludarUsuario() {
        var bundle: Bundle? = intent.extras

        if(bundle != null){
            var usuario = bundle?.getString("nombre")
            Toast.makeText(this, "Bienvenid@ $usuario", Toast.LENGTH_LONG).show()
        }
    }
}