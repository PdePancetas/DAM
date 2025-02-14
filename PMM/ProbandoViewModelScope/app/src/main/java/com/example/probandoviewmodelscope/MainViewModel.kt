package com.example.activitycorrutinas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class MainViewModel : ViewModel() {

    // CoroutineScope associated with the ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text
    var i = 0
    fun performBackgroundTask() {
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                _text.postValue("Tarea en hilo comenzada") // Actualiza el LiveData
                delay(10000)
                _text.postValue("Tarea en hilo Finalizada") // Actualiza el LiveData
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
