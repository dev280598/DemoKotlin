package com.example.test1.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.R
import com.example.test1.adapter.NotiAdapter
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.model.notify.Hit
import com.example.test1.viewholder.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var notiAdapter: NotiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = activityMainBinding.viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        notiAdapter = NotiAdapter()

        observeData()
        recyclerView.adapter = notiAdapter
        //allPost
        btn_retry.setOnClickListener {
            mainViewModel?.getDataDB()?.observeForever {
               // allPost
                btn_retry.visibility = View.GONE
            }
        }
    }
    fun observeData(){
        mainViewModel?.getArticleLiveData()?.observeForever {
            notiAdapter?.submitList(it)
        }
        mainViewModel?.getNetWorkState()?.observeForever {
            notiAdapter?.setNetworkState(it)
        }
    }
//    private val allPost: Unit
//        private get() {
//            mainViewModel?.allPost?.observe(this, Observer<List<Hit?>?> {
//                if(it?.size==null){
//                    btn_retry.visibility=View.VISIBLE
//                }else{
//                    Log.d("AAWW","Size : " + it.size)
//                  notiAdapter?.setPostList(it as ArrayList<Hit>?)
//                }
//            })
//        }
  }



