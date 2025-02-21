package com.example.publicacionesejemplo_1_n

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.publicacionesejemplo_1_n.database.Publication
import com.example.publicacionesejemplo_1_n.database.PublicationDao
import com.example.publicacionesejemplo_1_n.database.User
import com.example.publicacionesejemplo_1_n.database.UserDao

@Database(entities = [User::class,Publication::class], version = 1)
abstract class PublicacionesDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun publicationDao(): PublicationDao

}