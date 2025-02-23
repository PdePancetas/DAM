package com.pancetas.redsocial.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pancetas.redsocial.model.FriendShip

@Dao
interface FriendShipDao {

    @Query("SELECT * FROM friendShips")
    suspend fun getAllFriendShips(): MutableList<FriendShip>

    @Insert
    suspend fun insertFriendship(friend: FriendShip)

    @Update
    suspend fun updateFriendShip(friend: FriendShip)

    @Delete
    suspend fun deleteFriendShip(friend: FriendShip)

    @Query("SELECT * FROM friendships WHERE userAId = :userId OR userBId = :userId")
    suspend fun getFriendsByUserId(userId: Int): List<FriendShip>

}