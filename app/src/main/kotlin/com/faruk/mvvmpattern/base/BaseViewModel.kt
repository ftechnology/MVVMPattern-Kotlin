package com.faruk.mvvmpattern.base

import android.arch.lifecycle.ViewModel
import com.faruk.mvvmpattern.injection.component.DaggerViewModelInjector
import com.faruk.mvvmpattern.injection.component.ViewModelInjector
import com.faruk.mvvmpattern.injection.module.NetworkModule
import com.faruk.mvvmpattern.ui.data.DataViewModel
import com.faruk.mvvmpattern.ui.data.DataListViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is DataListViewModel -> injector.inject(this)
            is DataViewModel -> injector.inject(this)
        }
    }
}