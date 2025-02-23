package com.pancetas.redsocial.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pancetas.redsocial.model.Publication
import com.pancetas.redsocial.model.User

@Dao
interface PubDao {

    @Query("SELECT * FROM publications")
    suspend fun getAllPublications(): MutableList<Publication>

    @Insert
    suspend fun addPublication(pub : Publication):Long

    @Update
    suspend fun updatePublication(pub: Publication)

    @Delete
    suspend fun deletePublication(pub: Publication)

    @Query("SELECT * FROM publications WHERE userId = :userId")
    suspend fun getPublicationsByUserId(userId: Int): List<Publication>
}