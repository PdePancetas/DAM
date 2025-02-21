package com.example.publicacionesejemplo_1_n.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publication_entity")
data class Publication(
    @PrimaryKey(autoGenerate = true)
    var id_publicacion:Int=0,
    var titulo:String,
    var cuerpo:String,
    var user_id:Int
)

