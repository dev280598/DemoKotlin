package com.example.test1.model.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test1.constant.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
import com.example.test1.database.NotiDao
import com.example.test1.model.notify.Hit
import com.example.test1.services.NotificationRepositoryInterface
import com.example.test1.services.NotifyService
import com.example.test1.utils.Listing

import kotlinx.coroutines.CoroutineScope

import java.util.concurrent.Executor

abstract class NotifyRepositories(private val notifyService: NotifyService, private val dao: NotiDao) : NotificationRepositoryInterface {
    fun getListingNotifyOnl(usre: String, scope: CoroutineScope,networkExecutor: Executor?): Listing<Hit> {
        val factoty = NotifyDataSourceFactory(usre, scope, notifyService, dao, networkExecutor)
        var pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(DEFAULT_NETWORK_PAGE_SIZE)
                .setPageSize(DEFAULT_NETWORK_PAGE_SIZE)
        val livePagedList = LivePagedListBuilder<String, Hit>(factoty, pagedListConfig.build())
                .setFetchExecutor(networkExecutor!!)
                .build()
        val refreshState = factoty.sourceLiveData.switchMap {
            it.initialLoad
        }
        return Listing(
                pagedList = livePagedList,
                networkState = factoty.sourceLiveData.switchMap {
                    it.networkState
                },
                retry = {
                    factoty.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    factoty.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState
        )
    }

    override fun getDB(): LiveData<List<Hit>> {
        return dao.getAllDB()
    }
}


