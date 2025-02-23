package com.example.misnotas

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.misnotas.database.TaskEntity
import com.google.android.material.textfield.TextInputEditText

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
        viewModel.tareas.observe(this) { newTask ->
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
            etTask.text?.clear()
        }
    }

}
