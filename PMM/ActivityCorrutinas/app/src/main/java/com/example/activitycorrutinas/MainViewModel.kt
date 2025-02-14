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

    private val _text = MutableLiveData<StringBuilder>()
    private val _bar = MutableLiveData<Int>()
    val text: LiveData<StringBuilder> get() = _text
    val bar: LiveData<Int> get() = _bar
    var strbuilder: StringBuilder = StringBuilder()
    fun performBackgroundTask10Seconds() {
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                strbuilder.append("Descarga: Descarga Comenzada\n")
                _text.postValue(strbuilder) // Actualiza el LiveData
                var cont = 0
                _bar.postValue(cont)
                while(cont!=10) {
                    delay(1000)
                    cont++
                    _bar.postValue(cont)
                }

                strbuilder.append("Descarga: Descarga finalizada\n")
                _text.postValue(strbuilder) // Actualiza el LiveData
            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }
    }

    fun performBackgroundTaskHilo1() {

        viewModelScope.launch {
            try{

                strbuilder.append("Hilo1: Primera opción\n")
                _text.postValue(strbuilder)
                delay(2000)
                strbuilder.append("Hilo1: Segunda opción\n")
                _text.postValue(strbuilder)
            } catch(e: CancellationException){
                println("Coroutine was canceled")
            }
        }

    }

    fun performBackgroundTaskImmediate(){
        viewModelScope.launch{
            strbuilder.append("Inmediata: Tarea inmediata finalizada\n")
            _text.postValue(strbuilder)
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Cancel all coroutines when the ViewModel is cleared (e.g., when the associated UI component is destroyed)
        viewModelScope.cancel()
    }
}
