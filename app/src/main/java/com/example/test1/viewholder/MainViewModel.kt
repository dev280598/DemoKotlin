package com.example.test1.viewholder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.test1.database.DB
import com.example.test1.database.NotiDao
import com.example.test1.model.Data
import com.example.test1.model.PostRepository
import com.example.test1.model.notify.Hit


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val postRepository: PostRepository
    private val dao:NotiDao

    val allPost: LiveData<List<Data>>
        get() = postRepository.getMutableLiveData()

    init {
        dao = DB.getDatabase(application).NotiDao()
        postRepository = PostRepository()
    }

    fun getDataDD():LiveData<List<Hit>> = dao.getAllDB()


    }



