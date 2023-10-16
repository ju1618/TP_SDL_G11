package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CrearUsuarioActivity : AppCompatActivity() {

    lateinit var btnCrear : Button
    lateinit var etmaillCreate: EditText
    lateinit var etUsuarioCreate: EditText
    lateinit var etPassCreate: EditText
    lateinit var etPassRepeat: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)

        btnCrear = findViewById(R.id.btnCrearUsuario)
        etmaillCreate = findViewById(R.id.etmaillCreate)
        etUsuarioCreate = findViewById(R.id.etUsuarioCreate)
        etPassCreate = findViewById(R.id.etPassCreate)
        etPassRepeat = findViewById(R.id.etPassRepeat)

        btnCrear.setOnClickListener {
            val nombreUsuario = etUsuarioCreate.text.toString()
            val passwordUsuario = etPassCreate.text.toString()
            val passwordUsuarioRepeat = etPassRepeat.text.toString()
            val mailUsuario = etmaillCreate.text.toString()

            if (nombreUsuario.isEmpty() || passwordUsuario.isEmpty() || mailUsuario.isEmpty() || passwordUsuarioRepeat.isEmpty()) {
                Toast.makeText(this, "Completa todos los datos", Toast.LENGTH_SHORT).show()
            } else {
                if (passwordUsuario != passwordUsuarioRepeat) {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                } else {

                    lifecycleScope.launch {
                        val newUser = Usuario(nombreUsuario, passwordUsuario, mailUsuario)
                        withContext(Dispatchers.IO) {
                            val context = this@CrearUsuarioActivity // Obtener el contexto de la actividad actual
                            AppDatabase.getDatabase(context).usuarioDao().insertUsuario(newUser)
                        }
                    }
                    Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                    finish()
                }
            }
        }
    }
}
