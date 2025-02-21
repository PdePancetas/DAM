package com.example.publicacionesejemplo_1_n.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var usuario:String,
    var pass:String

)
