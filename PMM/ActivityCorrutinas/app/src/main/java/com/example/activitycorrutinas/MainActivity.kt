package com.example.activitycorrutinas

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import com.example.activitycorrutinas.databinding.ActivityMainBinding
import kotlin.getValue

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bHilo1 = findViewById<Button>(R.id.btnHilo1)
        val bInmediato = findViewById<Button>(R.id.btnTareaInmediata)
        val bDescarga = findViewById<Button>(R.id.btnDescargar)
        val texto = findViewById<TextView>(R.id.textView)
        val bar = findViewById<ProgressBar>(R.id.progressBar)
        texto.movementMethod = android.text.method.ScrollingMovementMethod()
        bHilo1.setOnClickListener {
            // Observa los cambios en el LiveData
            viewModel.text.observe(this) { newText ->
                texto.text = newText.toString()
            }
            viewModel.performBackgroundTaskHilo1()

        }

        viewModel.bar.observe(this) { value ->
            bar.progress = value
        }

        bDescarga.setOnClickListener {
            // Observa los cambios en el LiveData
            viewModel.text.observe(this) { newText ->
                texto.text = newText.toString()

            }

            bDescarga.isEnabled = false
            viewModel.performBackgroundTask10Seconds()
            bDescarga.isEnabled = true

        }

        bInmediato.setOnClickListener {
            viewModel.text.observe(this) { newText ->
                texto.text = newText.toString()
            }
            viewModel.performBackgroundTaskImmediate()
        }



    }

}