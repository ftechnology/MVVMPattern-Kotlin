package com.faruk.mvvmpattern.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.faruk.mvvmpattern.model.Data
import com.faruk.mvvmpattern.model.DataDao

@Database(entities = [Data::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}