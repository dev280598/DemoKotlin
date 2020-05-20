package com.example.test1.model.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.database.NotiDao
import com.example.test1.model.notify.Hit
import com.example.test1.services.NotifyService
import com.example.test1.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.Executor

class NotifyDataSource(
        var user: String,
        var scope: CoroutineScope?,
        var service: NotifyService?,
        var dao: NotiDao?,
        var retryExecutor: Executor?
) : PageKeyedDataSource<String, Hit>() {
    var a = 0
    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor?.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(
            params: LoadInitialParams<String>,
            callback: LoadInitialCallback<String, Hit>
    ) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        scope?.launch {
            try {
                val response =
                        service?.getNotify(user, params.requestedLoadSize)
                                ?.await()
                response?.hits?.hits?.let {
                    it.forEach {
                        if (LIST_KEY.containsKey(it.source?.iv104)) {
                            when (it.source?.iv104) {
                                "noti_friend_mention_user_in_comment",
                                "noti_user_react_post_user_tagged",
                                "halo_noti_share_post",
                                "halo_noti_create_post" -> {
                                    it.source?.title =
                                            it.source?.fi101[0].iv102 + " " + LIST_KEY[it.source?.iv104]
                                }
                                "noti_friend_invite_like" -> {
                                    if (it.source?.fi102.lastOrNull()?.iv109.equals("page")) {
                                        it.source?.title =
                                                it.source?.fi101[0].iv102 + " " + LIST_KEY[it.source?.iv104] + " " + it.source?.fi102.lastOrNull()?.iv102
                                    }
                                }
                                else -> {
//                                    it.source.title =
                                            it.source!!.fi101[0].iv102 + " " + LIST_KEY[it.source?.iv104] + " " + it.source?.iv107
                                }
                            }
                        } else {
                            it.source?.title = ""
                        }
                    }
                    SaveDataToDB(dao, it)
                    callback.onResult(it, null, makeSort(it.lastOrNull()?.sort))
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    Log.d("AAA","DATA"+it)
                }
            } catch (ioException: IOException) {
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error(ioException.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {
        networkState.postValue(NetworkState.LOADING)
        scope?.launch {
            try {
                val response = service?.getnotifyAfter(
                        user,
                        params.requestedLoadSize,
                        params.key
                )?.await()

                response?.hits?.hits?.let {

                    it.forEach {
                        if (LIST_KEY.containsKey(it.source?.iv104)) {
                            when (it.source?.iv104) {
                                "noti_friend_mention_user_in_comment",
                                "noti_user_react_post_user_tagged",
                                "halo_noti_share_post",
                                "halo_noti_create_post" -> {
                                    it.source?.title =
                                            it.source!!.fi101[0].iv102 + " " + LIST_KEY[it.source?.iv104]
                                }
                                "noti_friend_invite_like" -> {
                                    if (it.source?.fi102?.lastOrNull()?.iv109.equals("page")) {
                                        it.source?.title =
                                                it.source!!.fi101[0].iv102 + " " + LIST_KEY[it.source?.iv104] + it.source?.fi102.lastOrNull()?.iv102
                                    }
                                }
                                else -> {
                                    it.source?.title =
                                            it.source!!.fi101[0].iv102 + " " + LIST_KEY[it.source?.iv104] + " " + it.source?.iv107
                                }
                            }
                        } else {
                            it.source?.title = ""
                        }
                    }
                    callback.onResult(it, makeSort(it.lastOrNull()?.sort))

                }
                networkState.postValue(NetworkState.LOADED)

            } catch (ioException: IOException) {
                retry = { loadAfter(params, callback) }
                networkState.postValue(NetworkState.error(ioException.message ?: "unknown err"))
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {}

    private fun makeSort(objects: List<Any>?): String {
        return try {
            objects?.run {
                val stringBuilder = StringBuilder("[")
                for (i in objects.indices) {
                    when (val o = objects[i]) {
                        is Long -> {
                            stringBuilder.append((o as Long).toLong())
                        }
                        is Double -> {
                            stringBuilder.append((o as Double).toDouble())
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

    private suspend fun SaveDataToDB(dao: NotiDao?, hits: List<Hit>) {
        dao?.deleteAll()
         dao?.insert(hits)
    }
}