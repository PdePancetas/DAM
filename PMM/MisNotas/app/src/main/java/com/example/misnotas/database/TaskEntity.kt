package com.example.misnotas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var name:String = "",
    var isDone:Boolean = false
)