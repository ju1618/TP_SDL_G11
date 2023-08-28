package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var cbRecordar: CheckBox
    lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        cbRecordar= findViewById(R.id.cbRecordar)
        btnIniciar = findViewById(R.id.btnIniciar)


        btnIniciar.setOnClickListener{
            var mensaje= "Iniciar Sesion"
            val nombreUsuario = etUsuario.text.toString()
            if(nombreUsuario.isEmpty()||etPass.text.toString().isEmpty()){
                mensaje+= " - Faltan Datos"

            }else{
                mensaje+= " - Inicio Correcto"
                if(cbRecordar.isChecked){
                    mensaje+= " (Recordar Usuario)"
                }
                val intentMain = Intent(this, MainActivity::class.java)

                intentMain.putExtra("nombre", nombreUsuario)
                startActivity(intentMain)
                finish()
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}