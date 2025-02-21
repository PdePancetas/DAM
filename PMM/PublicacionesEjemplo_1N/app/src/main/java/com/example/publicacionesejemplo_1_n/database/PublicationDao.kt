package com.example.publicacionesejemplo_1_n.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PublicationDao {
    @Insert
    fun addPublication(publication : Publication):Long

    @Query("SELECT * FROM publication_entity")
    fun getAllEntities(): Publication

    @Query("SELECT * FROM publication_entity where id_publicacion = :id")
    fun getPublicationById(id: Int): Publication
    /*
            @Update
            fun updateTask(taskEntity: TaskEntity)

            @Delete
            fun deleteTask(taskEntity: TaskEntity)
            */

}
