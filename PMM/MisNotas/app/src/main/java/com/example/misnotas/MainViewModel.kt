package com.example.misnotas

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.misnotas.MisNotasApp.Companion.database
import com.example.misnotas.database.TaskDao
import com.example.misnotas.database.TaskEntity
import com.example.misnotas.database.TaskDatabase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class MainViewModel : ViewModel() {

    // CoroutineScope associated with the ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _tarea = MutableLiveData<List<TaskEntity>>()
    val tarea: LiveData<List<TaskEntity>> get() = _tarea

    fun obtenerTareas(){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                // Run the database query on the IO dispatcher
                val tasks = withContext(Dispatchers.IO) {
                    MisNotasApp.database.taskDao().getAllTasks()
                }
                // Update LiveData on the main thread
                _tarea.postValue(tasks)

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }


    fun anyadirTarea(task:TaskEntity){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {

                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    MisNotasApp.database.taskDao().addTask(task)
                }

                val recoveryTask:List<TaskEntity> = withContext(Dispatchers.IO) {
                    MisNotasApp.database.taskDao().getAllTasks()
                }

                // Update LiveData on the main thread
                _tarea.postValue(recoveryTask)

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    fun actualizarTarea(task:TaskEntity){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {

                // Perform database operations on IO dispatcher
                task.isDone = !task.isDone

                val ident = withContext(Dispatchers.IO) {
                    MisNotasApp.database.taskDao().updateTask(task)
                }

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    fun borrarTarea(task:TaskEntity){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    MisNotasApp.database.taskDao().deleteTask(task)
                }
                val recoveryTask:List<TaskEntity> = withContext(Dispatchers.IO) {
                    MisNotasApp.database.taskDao().getAllTasks()
                }

                // Update LiveData on the main thread
                _tarea.postValue(recoveryTask)

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        // Cancel all coroutines when the ViewModel is cleared (e.g., when the associated UI component is destroyed)
        viewModelScope.cancel()
    }
}
