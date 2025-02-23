package com.pancetas.redsocial.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pancetas.redsocial.dao.CommentDao
import com.pancetas.redsocial.dao.FriendShipDao
import com.pancetas.redsocial.dao.PubDao
import com.pancetas.redsocial.dao.UserDao
import com.pancetas.redsocial.model.*
import com.pancetas.redsocial.dao.*


@Database(entities = [User::class, Publication::class, Comment::class, FriendShip::class], version = 1)
abstract class SocialDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pubDao(): PubDao
    abstract fun comDao(): CommentDao
    abstract fun friendDao(): FriendShipDao
}