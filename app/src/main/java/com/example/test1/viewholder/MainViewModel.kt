package com.example.test1.viewholder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test1.constant.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
import com.example.test1.constant.NetworkState
import com.example.test1.database.DB
import com.example.test1.database.NotiDao
import com.example.test1.datasource.NotifyDataSourceFactory
import com.example.test1.model.Hit
import com.example.test1.view.Listing
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val postRepository: NotiRepository
    private val dao: NotiDao
    private var executor: Executor? = null
    private var networkState: LiveData<NetworkState>
    private var articleLiveData: LiveData<PagedList<Hit>>? = null
    val factoty = NotifyDataSourceFactory()
    val allPost: LiveData<List<Hit>>
        get() = postRepository.getMutableLiveData()

    fun callback(){

    }

    init {
        dao = DB.getDatabase(application).NotiDao()
        postRepository = NotiRepository()
        executor = Executors.newFixedThreadPool(5)

        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(DEFAULT_NETWORK_PAGE_SIZE)
                .setPageSize(DEFAULT_NETWORK_PAGE_SIZE)
        articleLiveData = LivePagedListBuilder<String, Hit>(factoty, pagedListConfig.build())
                .setFetchExecutor(executor!!)
                .build()
        networkState = factoty.sourceLiveData.switchMap {
            it.initialLoad
        }
    }
    fun getNetWorkState(): Listing<Hit>{
        return Listing(
                networkState = factoty.sourceLiveData.switchMap {
                    it.networkState
                },
                retry = {
                    factoty.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    factoty.sourceLiveData.value?.invalidate()
                },
                refreshState = networkState
        )
    }
    fun getArticleLiveData(): LiveData<PagedList<Hit>>? {
        return articleLiveData
    }
    //fun getDataDB(): LiveData<List<Hit>> = dao.getAllDB()

}



