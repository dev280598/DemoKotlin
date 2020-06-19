package com.example.test1.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.test1.R
import com.example.test1.adapter.NotiAdapter
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.model.Hit
import com.example.test1.services.Presenter
import com.example.test1.services.onclickCallBack
import com.example.test1.viewholder.MainViewModel
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("UNCHECKED_CAST", "CAST_NEVER_SUCCEEDS")
class MainActivity : AppCompatActivity(), onclickCallBack {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var adapterNoti: NotiAdapter? = null
    private val list = MutableLiveData<MutableList<Hit>>()
    private var listChecked = ArrayList<Hit?>()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callBack = object :Presenter{
            override fun callBack(pos: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Android Alert")
                builder.setMessage("You want to delete this noti")
                builder.setPositiveButton("Yes") { dialog, which ->
                    adapterNoti?.currentList?.get(pos)?.checked=true
                    adapterNoti?.notifyItemChanged(pos)
                    isChecked(adapterNoti?.currentList?.get(pos))
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                }
                builder.show()
            }
        }
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewmodel = viewModel
            callback = callBack

        }
        setSupportActionBar(toolbar)

        list.value = ArrayList()
        
        adapterNoti = NotiAdapter(this,callBack)
        val recyclerView = viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = SlideInLeftAnimator()
        recyclerView.adapter = adapterNoti
        allPost
        Log.d("AAA","${viewModel.counts.value}")

        swipe_refresh_layout.setOnRefreshListener{
          viewModel.getNetWorkState().refresh.invoke()
            listChecked.clear()
            tv.visibility = View.GONE
            viewModel.onResetCount()
            swipe_refresh_layout?.isRefreshing = false
        }
    }

    private fun observeData() {
        viewModel.getArticleLiveData()?.observeForever { it ->
            adapterNoti?.submitList(it)
        }
        viewModel.getNetWorkState().networkState.observeForever {
            adapterNoti?.setNetworkState(it)
        }
    }
    private val allPost: Unit
        get() {
            viewModel.allPost.observe(this, Observer<List<Hit?>?> {
                observeData()
            })
        }
    override fun onClick(view: View, pos: Int) {
        when (view.id) {
            R.id.retry_button -> {
                viewModel.getNetWorkState().retry.invoke()
            }
            R.id.bt_accept -> {
                adapterNoti?.currentList?.get(pos)?.source?.checkAccept = true
                adapterNoti?.setItemChaged(pos)
            }
            R.id.bt_cancel -> {
                adapterNoti?.currentList?.get(pos)?.source?.checkAccept = true
                adapterNoti?.setItemChaged(pos)
            }
            R.id.checkbox_id -> {
                adapterNoti?.currentList?.get(pos)?.source?.checkAccept = true
            }
        }
    }
    override fun isChecked(hit: Hit?) {
        list.value?.add(hit!!)
        list.value = list.value
        listChecked.add(hit)
        viewModel.onCount()
        tv.visibility = View.VISIBLE
    }

    override fun unChecked(hit: Hit?, pos: Int) {
        list.value?.remove(hit!!)
        listChecked.remove(hit)
        viewModel.onunCount()
        if (listChecked.size == 0){
            tv.visibility = View.GONE
            viewModel.onResetCount()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_one) {
            if (listChecked.size==0){
                val intent = Intent(this,ResultActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("Hit", list.value as ArrayList<out Parcelable> )
                intent.putExtra("BUNDLE", bundle)
                startActivity(intent)
            }else{
                Toast.makeText(this,"You are performing another task",Toast.LENGTH_SHORT).show()
            }


            return true
        }
        if (id ==R.id.action_two){
            if (listChecked.size == 0) {
                Toast.makeText(this,"Check in pls",Toast.LENGTH_SHORT).show()
            }else {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Android Alert")
                builder.setMessage("You want to delete" + " ("+viewModel.counts.value+") " +  "notifications " )
                builder.setPositiveButton("Yes") { dialog, which ->
                    list.value?.forEach {
                        if (list.value != null) {
                            it.checked = true
                            adapterNoti?.notifyDataSetChanged()
                        }
                        viewModel.dao.insert(list.value!!)
                    }
                    listChecked.clear()
                    tv.visibility = View.GONE
                    viewModel.onResetCount()
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                }
                builder.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}







