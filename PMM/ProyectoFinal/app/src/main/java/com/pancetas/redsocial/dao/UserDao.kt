package com.pancetas.redsocial.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pancetas.redsocial.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): MutableList<User>

    @Insert
    suspend fun addUser(user : User):Long

    @Query("SELECT * FROM users where id like :id")
    suspend fun getUserById(id: Int): User

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}