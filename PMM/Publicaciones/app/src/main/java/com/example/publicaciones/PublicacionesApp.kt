package com.example.publicaciones

import android.app.Application
import androidx.room.Room
import com.example.publicaciones.database.PublicacionesDatabase

class PublicacionesApp:Application() {
    companion object {
        lateinit var database: PublicacionesDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, PublicacionesDatabase::class.java, "publicaciones-db").build()
    }
}