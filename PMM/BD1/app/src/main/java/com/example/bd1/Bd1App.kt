package com.example.bd1

import android.app.Application
import androidx.room.Room
import com.example.bd1.Database.TaskDatabase

class Bd1App: Application() {

    companion object {
        lateinit var database: TaskDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, TaskDatabase::class.java, "autores-db").build()
    }
}