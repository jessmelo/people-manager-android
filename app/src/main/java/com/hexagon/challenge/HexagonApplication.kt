package com.hexagon.challenge

import android.app.Application
import com.hexagon.challenge.data.AppDatabase
import com.hexagon.challenge.data.repository.UserRepository
import com.hexagon.challenge.ui.SharedViewModel

class HexagonApplication : Application() {
    val sharedViewModel: SharedViewModel by lazy { SharedViewModel() }
    lateinit var repository: UserRepository

    companion object {
        private lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.getInstance(this)
        repository = UserRepository(database.userDao())
    }
}
