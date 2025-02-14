package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.R

class ControlesEntrada2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.main_activity2_controles_entrada)
        findViewById<TextView>(R.id.nombreText).text = intent.getStringExtra("nombre")
        findViewById<TextView>(R.id.paisText).text = intent.getStringExtra("pais")
        findViewById<TextView>(R.id.generoText).text = intent.getStringExtra("genero")
        findViewById<TextView>(R.id.hobbiesText).text = intent.getStringExtra("hobbies")
    }
}