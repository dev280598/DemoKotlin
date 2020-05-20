package com.example.test1.testDemo

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet&u=5bd2ec89a7262a092eb062f7&l=50")
    fun getNews(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<Response>

    companion object {
        fun getService(): NetworkService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://test-es-api.hahalolo.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(NetworkService::class.java)
        }
    }
}