package com.example.test1.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.adapter.NotiAdapter
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.model.Data
import com.example.test1.viewholder.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var notiAdapter: NotiAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = activityMainBinding.viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        notiAdapter = NotiAdapter()
        //mainViewModel?.getArticleLiveData()?.observe(this, { pagedList -> notiAdapter.submitList(pagedList) })

        recyclerView.adapter = notiAdapter
        allPost
        btn_retry.setOnClickListener {
            mainViewModel?.getDataDD()?.observeForever {
                allPost
                btn_retry.visibility = View.GONE
            }
        }
    }
    private val allPost: Unit
        private get() {
            mainViewModel!!.allPost.observe(this, Observer<List<Data?>?> {
                if(it?.size==null){
                    btn_retry.visibility=View.VISIBLE
                }else{
                    notiAdapter!!.setPostList(it as ArrayList<Data>?)

                }
            })
        }
    }


