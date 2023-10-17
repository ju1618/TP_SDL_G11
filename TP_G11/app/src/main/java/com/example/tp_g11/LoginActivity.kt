package com.example.tp_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class LoginActivity : AppCompatActivity() {
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var cbRecordar: CheckBox
    lateinit var btnIniciar: Button
    lateinit var toolbar : Toolbar
    lateinit var backButton: ImageButton


    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        cbRecordar = findViewById(R.id.cbRecordar)
        btnIniciar = findViewById(R.id.btnIniciar)
        backButton = findViewById(R.id.backButton)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        backButton = findViewById(R.id.backButton)
        supportActionBar?.title = ""

        backButton.setOnClickListener {
            onBackPressed()
        }




        var preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString("nombre_usuario", "")
        var passwordGuardado = preferencias.getString("password_usuario", "")


        //Cheack si el usuario está guardado
        if (usuarioGuardado != "" && passwordGuardado != "") {
            //val intent= Intent(this, Menu::class.java)
            redirectMainActivity()
        }

        backButton.setOnClickListener {
            super.onBackPressed()
        }

        btnIniciar.setOnClickListener {

            var nombreUsuario = etUsuario.text.toString()
            var passwordUsuario = etPass.text.toString()


            if(nombreUsuario!=""&&passwordUsuario!=""){
                //verifica si existe en bd
                if(verificarUsuario(nombreUsuario,passwordUsuario)){
                    Toast.makeText(this, "Logueado ok", Toast.LENGTH_SHORT).show()

                    //si existe lo logueo y si puse recuerdame lo guardo
                    if(cbRecordar.isChecked){
                        mostrarNotificacionDeRecordatorio()
                        Toast.makeText(this, "Usuario recordado", Toast.LENGTH_SHORT).show()
                        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("nombre_usuario", nombreUsuario).apply()
                        sharedPreferences.edit().putString("password_usuario", passwordUsuario).apply()
                        redirectMainActivity()

                        //redirijo a Main
                        redirectMainActivity()
                    }else{
                        //solo logueo
                        redirectMainActivity()
                    }
                }else{
                    //si no existe un mensaje error
                    Toast.makeText(this, "usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    private fun redirectMainActivity() {

        val intentMain = Intent(this, MainActivity::class.java)

        startActivity(intentMain)
        finish()

    }

    private fun verificarUsuario(user: String, password: String): Boolean {

        val listaUser :List<Usuario> = AppDatabase.getDatabase(applicationContext).usuarioDao().getAll()

        return listaUser.any { it.nombre_usuario == user && it.password == password }
        //esta funcion tendria que devolver si existe o no el usuario en db nada mas
    }

    private fun mostrarNotificacionDeRecordatorio() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Nombre del Canal",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val intentAbrir = Intent(this, MainActivity::class.java)
        intentAbrir.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntentAbrir = PendingIntent.getActivity(
            this,
            0,
            intentAbrir,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.ic_notification_icon2)
            .setContentTitle("Hola Usuari@")
            .setContentText("Verifica el clima en tu zona")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntentAbrir)
            .setAutoCancel(true)


        val notificationManagerCompat = NotificationManagerCompat.from(this)
        val notificationId = 1
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }
}