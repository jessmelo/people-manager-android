package com.hexagon.challenge

import android.app.Application
import com.hexagon.challenge.data.AppDatabase
import com.hexagon.challenge.data.repository.UserRepository

class HexagonApplication : Application() {
    lateinit var repository: UserRepository

    companion object {
        private lateinit var database: AppDatabase

        fun getRepository(): UserRepository {
            return UserRepository(getDatabase().userDao())
        }

        private fun getDatabase(): AppDatabase {
            return database
        }
    }

    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.getInstance(this)
        repository = UserRepository(database.userDao())
    }
}
