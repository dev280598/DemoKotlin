package com.example.test1.viewholder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.test1.database.DB
import com.example.test1.database.NotiDao
import com.example.test1.model.Data
import com.example.test1.model.PostRepository
import com.example.test1.model.datasource.NotifyDataSource
import com.example.test1.model.notify.NotifyResponse
import com.example.test1.services.NotificationRepositoryInterface
import com.example.test1.utils.NetworkState


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val postRepository: PostRepository
    private val articleLiveData: LiveData<PagedList<NotifyResponse>>? = null
    private val networkState: LiveData<NetworkState?>? = null
    private val notifyRepositories: NotificationRepositoryInterface? =null
    private var liveDataSource: LiveData<NotifyDataSource>? = null
    private val dao:NotiDao

    val allPost: LiveData<List<Data>>
        get() = postRepository.getMutableLiveData()

    init {
        dao = DB.getDatabase(application).NotiDao()
        postRepository = PostRepository(application,dao)
    }

    fun getDataDD():LiveData<List<com.example.test1.model.notify.Hit>> = dao.getAllDB()
    private val repoResul = notifyRepositories?.getListingNotifyOnl("")

    }



