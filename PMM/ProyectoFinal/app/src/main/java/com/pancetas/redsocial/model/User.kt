package com.pancetas.redsocial.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dni: String,
    val name: String,
    val email: String,
    val age: Int,
    val nationality: String,
    val pfpicture: ByteArray?,

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        return dni == other.dni
    }

    override fun hashCode(): Int {
        return dni.hashCode()
    }
}