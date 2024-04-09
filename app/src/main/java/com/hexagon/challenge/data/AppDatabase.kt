package com.hexagon.challenge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hexagon.challenge.data.dao.UserDao
import com.hexagon.challenge.data.model.User

@Database(entities = [User::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).createFromAsset("database/user_database.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
