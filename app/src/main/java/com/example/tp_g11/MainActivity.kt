package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var btnCrearMain: Button
    lateinit var btnIniciarMain: Button
    lateinit var btnListaCiudades: Button
    lateinit var toolbar : Toolbar

    // Funcion que se ejecuta al iniciar un Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saludarUsuario()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnCrearMain = findViewById(R.id.btnCrearMain)

        btnCrearMain.setOnClickListener {
            val intentCreate = Intent(this, CrearUsuarioActivity::class.java)
            startActivity(intentCreate)
            finish()
        }

        btnIniciarMain = findViewById(R.id.btnIniciarMain)

        btnIniciarMain.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            finish()

        }

        btnListaCiudades = findViewById(R.id.btnListaCiudades)

        btnListaCiudades.setOnClickListener {
            val intentMostrarLista = Intent(this, ListaCiudadesActivity::class.java)
            startActivity(intentMostrarLista)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sobrenosotros) {
            val intentSobreNosotros = Intent(this, SobreNosotrosActivity::class.java)
            startActivity(intentSobreNosotros)
        }

        if (item.itemId == R.id.action_listaciudades) {
            val intentListaCiudades = Intent(this, ListaCiudadesActivity::class.java)
            startActivity(intentListaCiudades)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saludarUsuario() {
        Toast.makeText(this, "usuario Logueado", Toast.LENGTH_SHORT).show()

    }
}

//Buscar como hacer un logoff
//buscar como hacer q los botones cambien su visibilidad