package com.example.usergenerator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [UserEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao
}