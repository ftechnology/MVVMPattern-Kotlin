package com.faruk.mvvmpattern.ui.data

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.faruk.mvvmpattern.R
import com.faruk.mvvmpattern.base.BaseViewModel
import com.faruk.mvvmpattern.model.Data
import com.faruk.mvvmpattern.model.DataDao
import com.faruk.mvvmpattern.network.DataApi
import javax.inject.Inject

class DataListViewModel(private val DataDao: DataDao):BaseViewModel(){
    @Inject
    lateinit var dataApi: DataApi
    val dataListAdapter: DataListAdapter = DataListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadData() }

    private lateinit var subscription: Disposable

    init{
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadData(){
        subscription = Observable.fromCallable { DataDao.all }
                .concatMap {
                    dbPostList ->
                        if(dbPostList.isEmpty())
                            dataApi.getDataList().concatMap {
                                            apiPostList -> DataDao.insertAll(*apiPostList.toTypedArray())
                                 Observable.just(apiPostList)
                                       }
                        else
                            Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrieveDataListSuccess(result) },
                        { onRetrieveDataListError() }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveDataListSuccess(postList:List<Data>){
        dataListAdapter.updatePostList(postList)
    }

    private fun onRetrieveDataListError(){
        errorMessage.value = R.string.post_error
    }
}