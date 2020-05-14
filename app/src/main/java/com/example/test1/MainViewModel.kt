package com.example.test1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.test1.model.Data
import com.example.test1.model.PostRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val postRepository: PostRepository
    val allEmployee: LiveData<List<Data>>
        get() = postRepository.getMutableLiveData()

    init {
        postRepository = PostRepository()
    }
}