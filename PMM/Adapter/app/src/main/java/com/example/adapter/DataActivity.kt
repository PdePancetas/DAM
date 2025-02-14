package com.example.adapter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class DataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_data)

        findViewById<ImageView>(R.id.imagen).setImageResource(intent.getIntExtra("imagen",R.drawable.error))
        findViewById<TextView>(R.id.genre).text = "Género/s: "+intent.getStringExtra("genre")
        findViewById<TextView>(R.id.sinopsis).text = intent.getStringExtra("sinopsis")
        findViewById<TextView>(R.id.duration).text = ""+intent.getIntExtra("duration",0)+" min"

    }
}