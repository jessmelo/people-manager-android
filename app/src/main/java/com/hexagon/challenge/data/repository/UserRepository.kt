package com.hexagon.challenge.data.repository

import com.hexagon.challenge.data.dao.UserDao
import com.hexagon.challenge.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository (private val userDao: UserDao) {
    fun getAll(): Flow<List<User>> = userDao.getAll()
    fun getById(id: Int): Flow<User> = userDao.getById(id)
    suspend fun update(user: User) = userDao.update(user)
    fun loadAllByIds(userIds: IntArray): List<User> = userDao.loadAllByIds(userIds)
    fun findByName(name: String): User = userDao.findByName(name)
    suspend fun insert(user: User) = userDao.insert(user)
    fun insertAll(vararg users: User) = userDao.insertAll(*users)
    fun delete(user: User) = userDao.delete(user)
}