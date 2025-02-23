package com.pancetas.redsocial.app;

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.pancetas.redsocial.database.SocialDatabase

class RedSocialApp : Application() {

    companion object {
        lateinit var database: SocialDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, SocialDatabase::class.java, "social-db").build()
        Log.i("RedSocialApp.kt","Base de datos creada correctamente",)
    }

}
