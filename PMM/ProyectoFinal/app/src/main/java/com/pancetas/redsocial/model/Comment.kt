package com.pancetas.redsocial.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "comments",
    foreignKeys = [ //Establece la relación que hay entre publicaciones y comentarios (1:N respectivamente)
        ForeignKey(
            entity = Publication::class,
            parentColumns = ["publicationId"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val publicationId: Int,
    val userId: Int,
    val content: String
)
