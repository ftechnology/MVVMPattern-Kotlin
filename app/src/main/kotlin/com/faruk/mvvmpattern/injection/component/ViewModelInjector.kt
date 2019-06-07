package com.faruk.mvvmpattern.injection.component

import dagger.Component
import com.faruk.mvvmpattern.injection.module.NetworkModule
import com.faruk.mvvmpattern.ui.data.DataListViewModel
import com.faruk.mvvmpattern.ui.data.DataViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified DataListViewModel.
     * @param dataListViewModel DataListViewModel in which to inject the dependencies
     */
    fun inject(dataListViewModel: DataListViewModel)
    /**
     * Injects required dependencies into the specified DataViewModel.
     * @param dataViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(dataViewModel: DataViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}