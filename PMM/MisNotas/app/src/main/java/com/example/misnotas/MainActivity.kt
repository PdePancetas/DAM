package com.example.misnotas

import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.misnotas.database.TaskEntity
import com.example.misnotas.ui.theme.MisNotasTheme
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var adapter: TasksAdapter
    lateinit var recyclerView: RecyclerView

    // Inicializa el viewModel
    //by viewModels() se usa para inicializar un ViewModel de forma eficiente y segura
    // dentro del ciclo de vida de la UI.
    private val viewModel: MainViewModel by viewModels()
    lateinit var btnAddTask:Button
    lateinit var etTask: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvTask)
        // Observa los cambios en el LiveData
        viewModel.tarea.observe(this) { newTask ->
            //texto.text = newText
            //Aquí cargamos la info en el recycler
            adapter = TasksAdapter(newTask, { viewModel.actualizarTarea(it) }, {viewModel.borrarTarea(it)})
            recyclerView = findViewById(R.id.rvTask)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }

        //Método en segundo plano
        viewModel.obtenerTareas()

        btnAddTask = findViewById(R.id.btnAddTask)
        etTask = findViewById(R.id.etTask)

        btnAddTask.setOnClickListener {
            viewModel.anyadirTarea(TaskEntity(name = etTask.text.toString()))
        }
    }

}
