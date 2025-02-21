package com.example.publicacionesejemplo_1_n

import android.app.Application
import androidx.room.Room

class PublicacionesApp:Application() {
    companion object {
        lateinit var database: PublicacionesDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, PublicacionesDatabase::class.java, "publicaciones-db").build()
    }
}