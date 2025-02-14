package com.example.probandoasynctask

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivityCorrutinas : AppCompatActivity() {

    // Kotlin delegate (by viewModels())
    // Inicializa el viewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val bHilo = findViewById<Button>(R.id.btnHilo)
        val bInmediato = findViewById<Button>(R.id.btnInmediato)
        val texto = findViewById<TextView>(R.id.lblResultado)

        bHilo.setOnClickListener {
            // Observa los cambios en el LiveData
            viewModel.text.observe(this) { newText ->
                texto.text = newText
            }
            viewModel.performBackgroundTask()

        }
        bInmediato.setOnClickListener {
            texto.text = "Tarea inmediata finalizada"
        }

    }
}
