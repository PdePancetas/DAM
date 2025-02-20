package com.example.bd1.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    fun anadeAutor(taskEntity : TaskEntity):Long

    @Query("SELECT max(dni) FROM task_entity")
    fun getMaxDni(): Int

    @Query("SELECT * FROM task_entity where dni like :id")
    fun getTaskById(id: Long): TaskEntity

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)


}