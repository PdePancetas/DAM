package com.example.misnotas

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.misnotas.database.TaskDatabase

class MisNotasApp: Application() {

    companion object {
        lateinit var database: TaskDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, TaskDatabase::class.java, "tasks-db").build()
    }
}