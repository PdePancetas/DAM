package com.example.publicaciones.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User):Long

    @Query("SELECT * FROM user_entity")
    fun getAllUsers(): List<User>

    @Query("SELECT id FROM user_entity where usuario = :u and pass = :p")
    fun getIdUser(u:String,p:String): Int

            @Query("SELECT * FROM user_entity where id = :id")
        fun getUserById(id: Int): User

    @Transaction
    @Query("SELECT * FROM user_entity")
    fun getUsersWithPublications(): UserWithPublications

    @Transaction
    @Query("SELECT * FROM user_entity where id = :id")
    fun getUserWithPublications(id:Int): UserWithPublications

    /*
            @Update
            fun updateTask(taskEntity: TaskEntity)

            @Delete
            fun deleteTask(taskEntity: TaskEntity)
            */

}
