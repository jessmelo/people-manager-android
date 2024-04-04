package com.hexagon.challenge

import android.app.Application
import androidx.room.Room
import com.hexagon.challenge.data.AppDatabase
import com.hexagon.challenge.data.repository.UserRepository

class HexagonApplication : Application() {
    lateinit var repository: UserRepository

    companion object {
        private lateinit var database: AppDatabase

        fun getRepository(): UserRepository {
            return UserRepository(getDatabase().userDao())
        }

        fun getDatabase(): AppDatabase {
            return database
        }
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        repository = UserRepository(database.userDao())
    }
}
