package com.faruk.mvvmpattern.network

import io.reactivex.Observable
import com.faruk.mvvmpattern.model.Data
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface DataApi {
    /**
     * Get the list of the data from the API
     */
    @GET("response.json")
    fun getDataList(): Observable<List<Data>>
}