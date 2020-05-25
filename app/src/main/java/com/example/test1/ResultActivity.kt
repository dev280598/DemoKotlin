package com.example.test1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.adapter.NotiCheckAdapter
import com.example.test1.databinding.ActivityResultBinding
import com.example.test1.model.Hit
import com.example.test1.viewholder.MainViewModel


class ResultActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private var postAdapter: NotiCheckAdapter? = null
    var list : ArrayList<Hit> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val resultBinding :ActivityResultBinding =DataBindingUtil.setContentView(this,R.layout.activity_result)
        val recyclerViewResult =resultBinding.viewResult

        recyclerViewResult.layoutManager = LinearLayoutManager(this)
        recyclerViewResult.setHasFixedSize(true)

        val intent = intent
        val bundle = intent.getBundleExtra("BUNDLE")
        val list = bundle.getSerializable("Hit") as ArrayList<Hit>
        Log.d("JJJ","List:" +list)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        postAdapter = NotiCheckAdapter(list)

       // allPost
        recyclerViewResult.adapter = postAdapter
        Log.d("OOO","Size:" + " " + postAdapter?.itemCount)


    }
    private val allPost: Unit
        private get() {
            mainViewModel!!.allPost.observe(this, Observer<List<Hit?>?> {
                postAdapter!!.notifyDataSetChanged() })
        }
}




