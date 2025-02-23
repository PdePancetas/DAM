package com.pancetas.redsocial.repositories

import com.pancetas.redsocial.app.RedSocialApp
import com.pancetas.redsocial.dao.UserDao
import com.pancetas.redsocial.model.User

class UserRepository() {

    private val userDao: UserDao = RedSocialApp.database.userDao()

    suspend fun insertUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}
