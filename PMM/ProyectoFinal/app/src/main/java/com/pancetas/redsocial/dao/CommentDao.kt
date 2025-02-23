package com.pancetas.redsocial.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pancetas.redsocial.model.Comment

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): MutableList<Comment>

    @Insert
    suspend fun addComment(com : Comment):Long

    @Update
    suspend fun updateComment(com: Comment)

    @Delete
    suspend fun deleteComment(com: Comment)

    @Query("SELECT * FROM comments WHERE userId = :userId")
    suspend fun getCommentsByUserId(userId: Int): List<Comment>

}