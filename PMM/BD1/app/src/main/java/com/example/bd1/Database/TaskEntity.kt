package com.example.bd1.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var dni:Int = 0,
    var nombre:String = "",
    var apellido:String = ""
)