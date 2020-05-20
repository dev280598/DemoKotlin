package com.example.test1.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.example.test1.Retrofit.APIClient
import com.example.test1.constant.Util.convertData
import com.example.test1.database.NotiDao
import com.example.test1.model.notify.Hit
import com.example.test1.model.notify.NotifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class PostRepository()  {

    private var post = ArrayList<Data>()
    private val mutableLiveData = MutableLiveData<List<Data>>()
    private var dao: NotiDao? = null
//
    fun getMutableLiveData(): MutableLiveData<List<Data>> {

        val userDataService = APIClient.client
        val call = userDataService.post

        call?.enqueue(object : Callback<NotifyResponse?> {
            override fun onResponse(call: Call<NotifyResponse?>, response: Response<NotifyResponse?>) {
                val Post = response.body()
                if (Post != null && Post.hits!!.hits != null) {

                    post = convertData(Post.hits!!.hits) as ArrayList<Data>
                    mutableLiveData.value = post
                    Post.hits?.hits?.forEach {
                        val test = listOf<Hit>()
                        dao?.insert(test)
                    }

                }
            }
            override fun onFailure(call: Call<NotifyResponse?>, t: Throwable) {
                mutableLiveData.value=null
            }
        })
        return mutableLiveData
    }

}