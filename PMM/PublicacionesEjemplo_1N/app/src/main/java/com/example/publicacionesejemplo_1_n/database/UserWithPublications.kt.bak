package com.example.publicacionesejemplo_1_n.database

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithPublications(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val publicaciones: List<Publication>
)