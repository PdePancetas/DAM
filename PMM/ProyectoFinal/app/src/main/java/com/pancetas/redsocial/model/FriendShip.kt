package com.pancetas.redsocial.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "friendShips",
    primaryKeys = ["userAid","userBid"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userAid"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userBid"],
            onDelete = CASCADE
        ),
    ]
)
data class FriendShip (
    val userAid: Int,
    val userBid: Int
)