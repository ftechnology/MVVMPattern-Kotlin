package com.faruk.mvvmpattern.ui.data

import android.arch.lifecycle.MutableLiveData
import com.faruk.mvvmpattern.base.BaseViewModel
import com.faruk.mvvmpattern.model.Data

class DataViewModel:BaseViewModel() {
    private val dataTitle = MutableLiveData<String>()
    private val dataBody = MutableLiveData<String>()

    fun bind(data: Data){
        dataTitle.value = data.title
        dataBody.value = data.body
    }

    fun getDataTitle():MutableLiveData<String>{
        return dataTitle
    }

    fun getDataBody():MutableLiveData<String>{
        return dataBody
    }
}