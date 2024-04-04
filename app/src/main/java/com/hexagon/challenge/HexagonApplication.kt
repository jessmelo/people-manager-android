package com.hexagon.challenge

import AppDatabase
import android.app.Application
import com.hexagon.challenge.data.repository.UserRepository

class HexagonApplication: Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }
}