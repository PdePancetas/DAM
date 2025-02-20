package com.example.bd1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bd1.Database.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class MainViewModel : ViewModel() {

    // CoroutineScope associated with the ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private val _entity = MutableLiveData<TaskEntity>()
    val entity: LiveData<TaskEntity> get() = _entity

    fun anyadirTarea(task:TaskEntity){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                if (task.nombre.isEmpty() && task.apellido.isEmpty())
                    throw CancellationException("Nombre y apellido están vacíos")


                task.dni = obtenerMaxDni() + 1
                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    Bd1App.database.taskDao().anadeAutor(task)
                    _text.postValue("")
                }

                // Update LiveData on the main thread
                //_tarea.postValue(recoveryTask)
                _entity.postValue(task)

            } catch (e : CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
                _text.postValue(e.message)
            }

        }

    }
    suspend fun obtenerMaxDni():Int{
        var dato:Int
        withContext(Dispatchers.IO) { // Ejecutar en segundo plano
            dato = Bd1App.database.taskDao().getMaxDni() // Obtener dato antes
            if (dato != null) {
                Log.d("msg","dato integrado")
            }
        }
        return dato
    }
    override fun onCleared() {
        super.onCleared()
        // Cancel all coroutines when the ViewModel is cleared (e.g., when the associated UI component is destroyed)
        viewModelScope.cancel()
    }
}
