package com.faruk.mvvmpattern.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.faruk.mvvmpattern.model.db.AppDatabase
import com.faruk.mvvmpattern.ui.data.DataListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "data").build()
            @Suppress("UNCHECKED_CAST")
            return DataListViewModel(db.dataDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}