package com.example.test1.viewholder

import androidx.lifecycle.MutableLiveData
import com.example.test1.services.APIClient
import com.example.test1.database.NotiDao
import com.example.test1.model.Hit
import com.example.test1.model.NotifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class PostRepository()  {

    private var post = ArrayList<Hit>()
    private val mutableLiveData = MutableLiveData<List<Hit>>()
    private var dao: NotiDao? = null
    fun getMutableLiveData(): MutableLiveData<List<Hit>> {

        val userDataService = APIClient.client
        val call = userDataService.getDataInit()

        call.enqueue(object : Callback<NotifyResponse?> {
            override fun onResponse(call: Call<NotifyResponse?>, response: Response<NotifyResponse?>) {
                val Post = response.body()
                if (Post != null) {
                    mutableLiveData.value = post
                    Post.hits.hits?.forEach {
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