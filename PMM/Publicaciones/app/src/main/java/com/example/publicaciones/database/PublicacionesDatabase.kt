package com.example.publicaciones.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Publication::class], version = 1)
abstract class PublicacionesDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun publicationDao(): PublicationDao

}