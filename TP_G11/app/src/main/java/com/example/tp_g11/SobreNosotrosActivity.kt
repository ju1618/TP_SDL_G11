package com.example.tp_g11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SobreNosotrosActivity : AppCompatActivity() {

    lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre_nosotros)



        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}