package com.example.misnotas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    fun getAllTasks(): MutableList<TaskEntity>

    @Insert
    fun addTask(taskEntity : TaskEntity):Long

    @Query("SELECT * FROM task_entity where id like :id")
    fun getTaskById(id: Long): TaskEntity

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)
}