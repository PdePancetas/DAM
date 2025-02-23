package com.pancetas.redsocial.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "publications",
    foreignKeys = [ //Establece la relación que hay entre usuarios y publicaciones (1:N respectivamente)
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = CASCADE
        )
    ]
)
data class Publication (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val content: String,
    val likes: Int,
)
