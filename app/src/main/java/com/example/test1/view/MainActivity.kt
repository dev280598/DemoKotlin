package com.example.test1.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.adapter.PostAdapter
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.model.Data
import com.example.test1.viewholder.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var postAdapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = activityMainBinding.viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        postAdapter = PostAdapter()
        recyclerView.adapter = postAdapter
        allPost
    }
    private val allPost: Unit
        private get() {
            mainViewModel!!.allPost.observe(this, Observer<List<Data?>?> { post
                -> postAdapter!!.setPostList(post as ArrayList<Data>?) })
        }
}