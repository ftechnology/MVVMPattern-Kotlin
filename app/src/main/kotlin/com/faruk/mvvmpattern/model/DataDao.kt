package com.faruk.mvvmpattern.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface DataDao {
    @get:Query("SELECT * FROM Data")
    val all: List<Data>

    @Insert
    fun insertAll(vararg users: Data)
}