package com.example.test1.viewholder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test1.constant.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
import com.example.test1.database.DB
import com.example.test1.database.NotiDao
import com.example.test1.model.Data
import com.example.test1.model.PostRepository
import com.example.test1.model.datasource.NotifyDataSource
import com.example.test1.model.datasource.NotifyDataSourceFactory
import com.example.test1.model.notify.Hit
import com.example.test1.utils.NetworkState
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val postRepository: PostRepository
    private val dao: NotiDao
    private var executor: Executor? = null
    private val networkState: LiveData<NetworkState>? = null
    private var articleLiveData: LiveData<PagedList<Hit>>? = null


    val allPost: LiveData<List<Data>>
        get() = postRepository.getMutableLiveData()

    init {
        dao = DB.getDatabase(application).NotiDao()
        postRepository = PostRepository()

        executor = Executors.newFixedThreadPool(5);
        val factoty = NotifyDataSourceFactory()
        var pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(DEFAULT_NETWORK_PAGE_SIZE)
                .setPageSize(DEFAULT_NETWORK_PAGE_SIZE)
        articleLiveData = LivePagedListBuilder<String, Hit>(factoty, pagedListConfig.build())
                .setFetchExecutor(executor!!)
                .build()


    }
    fun getNetWorkState(): LiveData<NetworkState>?{
        return networkState
    }
    fun getArticleLiveData(): LiveData<PagedList<Hit>>? {
        return articleLiveData
    }
    fun getDataDD(): LiveData<List<Hit>> = dao.getAllDB()
}



