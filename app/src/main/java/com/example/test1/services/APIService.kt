package com.example.test1.services

import com.example.test1.model.NotifyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet&u=5bd2ec89a7262a092eb062f7&l=10")
    fun getDataInit() : Call<NotifyResponse?>

    @GET("notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet&u=5bd2ec89a7262a092eb062f7&l=10")
    fun getDataAfter(@Query("a") a:String?=null) : Call<NotifyResponse?>


}