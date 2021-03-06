package com.example.test1.datasource

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.room.Query
import com.example.test1.R
import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.constant.NetworkState
import com.example.test1.database.DB
import com.example.test1.database.NotiDao
import com.example.test1.model.Hit
import com.example.test1.model.NotifyResponse
import com.example.test1.services.APIClient
import com.example.test1.services.onclickCallBack
import com.example.test1.viewholder.MainViewModel
import kotlinx.android.synthetic.main.post_list_item.view.*
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class NotifyDataSource(private val notiDao: NotiDao) : PageKeyedDataSource<String, Hit>() {
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    var userDataService = APIClient.client
    private var retryExecutor: Executor? = Executors.newFixedThreadPool(1)
    private var retry: (() -> Any)? = null
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor?.execute {
                it.invoke()
            }
        }
    }
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Hit>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        try {
            val call = userDataService.getDataInit()
            call.enqueue(object : Callback<NotifyResponse?> {

                override fun onResponse(call: Call<NotifyResponse?>, response: Response<NotifyResponse?>) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.hits?.let { it1 ->

                            val db = notiDao.getAllDB()

                            it1.forEach { it ->

                                if (LIST_KEY.containsKey(it.source.iv104)) {
                                    it.source.title =
                                            it.source.fi101[0].iv102 + " " + LIST_KEY[it.source.iv104 + ""]
                                } else {
                                    it.source.title = ""
                                }
                                db.forEach {it2->
                                    if (it2._id==it._id){
                                        it.checked = true
                                    }
                                }
                            }
//                                SaveDataToDB(dao, it)
                                callback.onResult(it1, null, makeSort(it1.lastOrNull()?.sort))
                                networkState.postValue(NetworkState.LOADED)
                                initialLoad.postValue(NetworkState.LOADED)
                        }
                    }else{
                        networkState.postValue(NetworkState(NetworkState.Status.FAILED, response.message()))
                        initialLoad.postValue(NetworkState(NetworkState.Status.FAILED, response.message()))
                    }
                }
                override fun onFailure(call: Call<NotifyResponse?>, t: Throwable) {
                    val errorMessage =  " Caused by:" + " " + t.message
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage))
                    retry = {
                        loadInitial(params,callback)
                    }
                }
            })
        }catch (ioException: IOException){

        }
    }
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {
        Log.i("Loading After", "Loading After " + params.key + " Count " + params.requestedLoadSize)
        networkState.postValue(NetworkState.LOADING)
        val call = userDataService.getDataAfter(params.key)

        call.enqueue(object : Callback<NotifyResponse?> {
            override fun onResponse(call: Call<NotifyResponse?>, response: Response<NotifyResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.hits?.hits?.let { it1 ->
                        val db = notiDao.getAllDB()

                            it1.forEach { it ->
                                if (LIST_KEY.containsKey(it.source.iv104)) {
                                    it.source.title =
                                            it.source.fi101[0].iv102 + " " + LIST_KEY[it.source.iv104 + ""]
                                } else {
                                    it.source.title = ""
                                }
                                db.forEach {it2->
                                    if (it2._id==it._id){
                                        it.checked = true
                                    }
                                }
                            }
                        Log.d("AAA","size: "+ db.size)
//                        SaveDataToDB(dao, it)
                        callback.onResult(it1,makeSort(it1.lastOrNull()?.sort))
                        networkState.postValue(NetworkState.LOADED)
                    }
                }else networkState.postValue(NetworkState(NetworkState.Status.FAILED, response.message()))

            }
            override fun onFailure(call: Call<NotifyResponse?>, t: Throwable) {

                networkState.postValue(NetworkState(NetworkState.Status.FAILED, t.message))
                retry = {
                    loadAfter(params,callback)
                }
            }
        })
    }
    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {
    }
    private fun makeSort(objects: List<Any>?): String {
        return try {
            objects?.run {
                val stringBuilder = StringBuilder("[")
                for (i in objects.indices) {
                    when (val o = objects[i]) {
                        is Long -> {
                            stringBuilder.append((o ).toLong())
                        }
                        is Double -> {
                            stringBuilder.append((o ).toDouble())
                        }
                        is String -> {
                            stringBuilder.append("\"")
                            stringBuilder.append(o)
                            stringBuilder.append("\"")
                        }
                    }
                    if (i < objects.size - 1) {
                        stringBuilder.append(",")
                    }
                }
                stringBuilder.append("]")
                stringBuilder.toString()
            } ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
    private fun SaveDataToDB(dao: NotiDao?, hits: List<Hit>) {
        dao?.deleteAll()
        dao?.insert(hits)
    }
}