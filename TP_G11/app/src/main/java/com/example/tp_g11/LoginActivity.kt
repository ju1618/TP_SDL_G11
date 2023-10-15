package com.example.tp_g11

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


    class LoginActivity : AppCompatActivity() {
        lateinit var etUsuario: EditText
        lateinit var etPass: EditText
        lateinit var cbRecordar: CheckBox
        lateinit var btnIniciar: Button

        private lateinit var sharedPreferences: SharedPreferences

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            etUsuario = findViewById(R.id.etUsuario)
            etPass = findViewById(R.id.etPass)
            cbRecordar = findViewById(R.id.cbRecordar)
            btnIniciar = findViewById(R.id.btnIniciar)

            var preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            var usuarioGuardado = preferencias.getString("nombre_usuario", "")
            var passwordGuardado = preferencias.getString("password_usuario", "")

            // Checkea si el usuario está guardado
            if (usuarioGuardado != "" && passwordGuardado != "") {
                redirectMainActivity()
            }

            btnIniciar.setOnClickListener {
                var nombreUsuario = etUsuario.text.toString()
                var passwordUsuario = etPass.text.toString()

                if (nombreUsuario != "" && passwordUsuario != "") {
                    // Verifica si existe el usuario en la base de datos
                    if (verificarUsuario(nombreUsuario, passwordUsuario)) {
                        Toast.makeText(this, "Logueado correctamente", Toast.LENGTH_SHORT).show()

                        // Si el usuario hace clic en recordar, muestra la notificación de recordatorio
                        if (cbRecordar.isChecked) {
                            mostrarNotificacionDeRecordatorio()
                            Toast.makeText(this, "Usuario recordado", Toast.LENGTH_SHORT).show()
                            sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("nombre_usuario", nombreUsuario).apply()
                            sharedPreferences.edit().putString("password_usuario", passwordUsuario).apply()
                            redirectMainActivity()
                        } else {
                            redirectMainActivity()
                        }
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
            val listaUser: List<Usuario> = AppDatabase.getDatabase(applicationContext).usuarioDao().getAll()
            return listaUser.any { it.nombre_usuario == user && it.password == password }
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

/*
class LoginActivity : AppCompatActivity() {
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var cbRecordar: CheckBox
    lateinit var btnIniciar: Button


    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        cbRecordar = findViewById(R.id.cbRecordar)
        btnIniciar = findViewById(R.id.btnIniciar)



        var preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString("nombre_usuario", "")
        var passwordGuardado = preferencias.getString("password_usuario", "")


        //Cheack si el usuario está guardado
        if (usuarioGuardado != "" && passwordGuardado != "") {
            //val intent= Intent(this, Menu::class.java)
            redirectMainActivity()
        }

        btnIniciar.setOnClickListener {

            var nombreUsuario = etUsuario.text.toString()
            var passwordUsuario = etPass.text.toString()


            if(nombreUsuario!=""&&passwordUsuario!=""){
                //Verifica si existe el usuario en la base de datos
                if(verificarUsuario(nombreUsuario,passwordUsuario)){
                    Toast.makeText(this, "Logueado correctamente", Toast.LENGTH_SHORT).show()

                    //Si existe lo logueo y si se marca el Checkbox se guarda y muestra la notificacion
                    if(cbRecordar.isChecked){
                        mostrarNotificacionDeRecordatorio()
                        Toast.makeText(this, "Usuario recordado", Toast.LENGTH_SHORT).show()

                        //Seteamos la informacion por defecto en el preference
                        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("nombre_usuario",nombreUsuario).apply()
                        sharedPreferences.edit().putString("password_usuario",passwordUsuario).apply()

                        //No se marcó el CheckBox, simplemente redirige a la pantalla principal(Main)
                        redirectMainActivity()
                    }else{
                        //No se marcó el CheckBox, simplemente redirige a la pantalla principal(Main)
                        redirectMainActivity()
                    }
                }else{
                    //Si no existe, muestra un mensaje de error
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Se ingresaron datos vacíos, muestra un mensaje de error
                Toast.makeText(this, "Por favor, ingresa el usuario y la contraseña", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun performLogout() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loginIntent)
    }

    private fun redirectMainActivity() {
        val intentMain = Intent(this, MainActivity::class.java)
        startActivity(intentMain)
        finish()
    }

    private fun verificarUsuario(user: String, password: String): Boolean {
        val listaUser: List<Usuario> = AppDatabase.getDatabase(applicationContext).usuarioDao().getAll()
        return listaUser.any { it.nombre_usuario == user && it.password == password }
    }

    private fun mostrarNotificacionDeRecordatorio() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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

        val intentLogout = Intent("ACTION_LOGOUT")
        val pendingIntentLogout =
            PendingIntent.getBroadcast(this, 0, intentLogout, PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.ic_notification_icon2)
            .setContentTitle("Hola Usuari@")
            .setContentText("Verifica el clima en tu zona")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntentAbrir)
            .addAction(R.drawable.ic_logout, "Logout", pendingIntentLogout)
            .setAutoCancel(true)

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        val notificationId = 1
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }
}*/


    /*
    // Agrega la función performLogout() para realizar el logout
    private fun performLogout() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Envía una difusión para realizar el logout
        val logoutIntent = Intent("ACTION_LOGOUT")
        sendBroadcast(logoutIntent)

        // Redirige al usuario a la pantalla de inicio de sesión (LoginActivity) o a la actividad deseada
        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loginIntent)
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
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("channel_id", "Nombre del Canal", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val intentAbrir = Intent(this, MainActivity::class.java) // Cambia LoginActivity a MainActivity para abrir la actividad principal
        intentAbrir.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntentAbrir = PendingIntent.getActivity(this, 0, intentAbrir,
            PendingIntent.FLAG_IMMUTABLE)

        // Crear un intent para la acción de Logout
        val intentLogout = Intent("ACTION_LOGOUT")
        val pendingIntentLogout = PendingIntent.getBroadcast(this, 0, intentLogout, PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.ic_notification_icon2)
            .setContentTitle("Hola Usuari@")
            .setContentText("Verifica el clima en tu zona")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntentAbrir) // Agregar PendingIntent para abrir la app
            .addAction(R.drawable.ic_logout, "Logout", pendingIntentLogout) // Agregar la acción de Logout

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        val notificationId = 1  // Debe ser un valor único para cada notificación
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }
}

*/