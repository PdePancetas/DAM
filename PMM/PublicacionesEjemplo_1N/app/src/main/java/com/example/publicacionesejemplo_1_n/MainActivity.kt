package com.example.publicacionesejemplo_1_n

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.publicacionesejemplo_1_n.database.Publication
import com.example.publicacionesejemplo_1_n.database.User
import com.example.publicacionesejemplo_1_n.database.UserWithPublications

class MainActivity : AppCompatActivity() {

    lateinit var visor:TextView
    var datos:StringBuilder = StringBuilder()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val usu: User = User(usuario = "Miguel",pass="Miguel")

        val pub: Publication = Publication(titulo = "publicacion random x2", cuerpo = "Q pasa x2", user_id = 1)

        var pubs: MutableList<Publication> = mutableListOf()
        pubs.plus(pub)
        val userWithPublications: UserWithPublications = UserWithPublications(user = usu, publicaciones = pubs)
        /*
        viewModel.anyadirUsuario(usu)

        viewModel.anyadirPublicacion(pub)
        */
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
                datos.append(usuPublic.publicaciones[i-1].titulo +": "+usuPublic.publicaciones[i-1].cuerpo  +"\n")
            }
            visor.text = datos.toString()
        }
        //Obtener usuarios
        viewModel.obtenerPublisUsuario(1)

    }


}

