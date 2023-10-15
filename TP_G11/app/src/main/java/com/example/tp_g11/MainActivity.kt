package com.example.tp_g11

import android.content.Context
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var btnCrearMain: Button
    lateinit var btnIniciarMain: Button
    lateinit var btnListaCiudades: Button
    lateinit var toolbar : Toolbar

    // Variable para rastrear si el usuario ya ha iniciado sesión
    private var mensajeMostrado = false

    // Funcion que se ejecuta al iniciar un Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenSplash.setKeepOnScreenCondition{ false }

        //saludarUsuario()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnCrearMain = findViewById(R.id.btnCrearMain)
        btnIniciarMain = findViewById(R.id.btnIniciarMain)
        btnListaCiudades = findViewById(R.id.btnListaCiudades)

        btnCrearMain.setOnClickListener {
            val intentCreate = Intent(this, CrearUsuarioActivity::class.java)
            startActivity(intentCreate)
            finish()
        }
        btnIniciarMain.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            finish()

        }
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

        if (item.itemId == R.id.action_logout){
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Datos eliminados", Toast.LENGTH_SHORT).show()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /*private fun saludarUsuario() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val usuarioGuardado = sharedPreferences.getString("nombre_usuario", "")

        if (!usuarioGuardado.isNullOrEmpty()) {
            Toast.makeText(this, "Usuario Logueado", Toast.LENGTH_SHORT).show()
        }
    }*/
}


//buscar como hacer q los botones cambien su visibilidad