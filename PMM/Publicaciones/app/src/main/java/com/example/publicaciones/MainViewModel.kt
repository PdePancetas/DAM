package com.example.publicaciones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.publicaciones.database.Publication
import com.example.publicaciones.database.User
import com.example.publicaciones.database.UserWithPublications
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

    private val _tarea = MutableLiveData<List<User>>()
    val tarea: LiveData<List<User>> get() = _tarea

    private val _publis = MutableLiveData<UserWithPublications>()
    val publis: LiveData<UserWithPublications> get() = _publis

    fun anyadirUsuario(usu:User){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {

                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    PublicacionesApp.database.userDao().addUser(usu)

                }

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    fun obtenerUsuarios(){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {

                // Perform database operations on IO dispatcher
                val usuarios = withContext(Dispatchers.IO) {
                    PublicacionesApp.database.userDao().getAllUsers()
                }
                // Update LiveData on the main thread
                _tarea.postValue(usuarios)

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    fun obtenerUsuario(ident:Int){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {

                //task.dni = obtenerdni() + 1
                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    PublicacionesApp.database.userDao().getUserById(ident)

                }

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    fun obtenerPublisUsuario(ident:Int){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {

                // Perform database operations on IO dispatcher
                val usuPublic = withContext(Dispatchers.IO) {
                    PublicacionesApp.database.userDao().getUserWithPublications(ident)

                }
                // Update LiveData on the main thread
                _publis.postValue(usuPublic)

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }

    fun obtenerPublicacionesUsuario(ident:Int){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    PublicacionesApp.database.userDao().getUserById(ident)
                }

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
