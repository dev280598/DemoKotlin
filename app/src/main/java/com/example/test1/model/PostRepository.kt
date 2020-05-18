package com.example.test1.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.test1.Retrofit.APIClient
import com.example.test1.Retrofit.APIService

import com.example.test1.constant.Util.convertData
import com.example.test1.database.DB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PostRepository  {

    private var post = ArrayList<Data>()
    private val mutableLiveData = MutableLiveData<List<Data>>()
//
    fun getMutableLiveData(): MutableLiveData<List<Data>> {

        val userDataService = APIClient.client
        val call = userDataService.post

        call?.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                val Post = response.body()
                if (Post != null && Post.hits!!.hits != null) {
                    post = convertData(Post.hits!!.hits) as ArrayList<Data>

                    mutableLiveData.value = post
                }
            }
            override fun onFailure(call: Call<Post?>, t: Throwable) {
                mutableLiveData.value=null

            }
        })
        return mutableLiveData
    }

}