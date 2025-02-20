package com.example.publicaciones

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.publicaciones.database.User
import com.example.publicaciones.ui.theme.PublicacionesTheme

class MainActivity : AppCompatActivity() {

    lateinit var visor:TextView
    var datos:StringBuilder = StringBuilder()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        visor = findViewById(R.id.txtVisor)

        // Observa los cambios en el LiveData
        viewModel.tarea.observe(this) { usuariosbd ->

            for (i in 1..usuariosbd.size) {
                datos.append(usuariosbd[i-1].usuario+"\n")
            }
            visor.text = datos.toString()
        }
        //Obtener usuarios
        viewModel.obtenerUsuarios()

        // Observa los cambios en el LiveData
        viewModel.publis.observe(this) { usuPublic ->

            for (i in 1..usuPublic.publicaciones.size) {
                Log.i("msg",usuPublic.publicaciones[i-1].titulo)
                datos.append(usuPublic.publicaciones[i-1].titulo  +"\n")
            }
            visor.text = datos.toString()
        }
        //Obtener usuarios
        viewModel.obtenerPublisUsuario(1)

    }


    }

