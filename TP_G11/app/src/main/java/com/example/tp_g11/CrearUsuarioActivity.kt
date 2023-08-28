package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CrearUsuarioActivity : AppCompatActivity() {

    lateinit var btnCrear : Button
    lateinit var etNombreCreate: EditText
    lateinit var etApellidoCreate: EditText
    lateinit var etmaillCreate: EditText
    lateinit var etUsuarioCreate: EditText
    lateinit var etPassCreate: EditText
    lateinit var etPassRepeat: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)

        btnCrear=findViewById(R.id.btnCrearUsuario)
        etNombreCreate=findViewById(R.id.etNombreCreate)
        etApellidoCreate=findViewById(R.id.etApellidoCreate)
        etmaillCreate=findViewById(R.id.etmaillCreate)
        etUsuarioCreate=findViewById(R.id.etUsuarioCreate)
        etPassCreate=findViewById(R.id.etPassCreate)
        etPassRepeat=findViewById(R.id.etPassRepeat)

        val nombreUsuario=etUsuarioCreate.text.toString()

        btnCrear.setOnClickListener{
            Toast.makeText(this,"Usuario Registrado", Toast.LENGTH_SHORT).show()

            val intentMain = Intent(this, MainActivity::class.java)

            intentMain.putExtra("nombre", nombreUsuario)
            startActivity(intentMain)
            finish()

        }
    }
}