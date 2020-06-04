package com.example.test1.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.strictmode.Violation
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
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

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity(),
        onclickCallBack{
    private var mainViewModel: MainViewModel? = null
    private var notiAdapter: NotiAdapter? = null
    private val list = MutableLiveData<MutableList<Hit>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val tv = findViewById<View>(R.id.tv) as TextView
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        list.value = ArrayList()

        val swipeRefreshLayout = findViewById<View>(R.id.swipe_refresh_layout) as SwipeRefreshLayout



         val callBack = object :Presenter{
            override fun callBack(pos: Int) {
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("Androidly Alert")
                    builder.setMessage("You want to delete this noti")
                    builder.setPositiveButton("Yes") { dialog, which ->
                        notiAdapter?.currentList?.get(pos)?.checked=true
                        notiAdapter?.notifyItemChanged(pos)
                        isChecked(notiAdapter?.currentList?.get(pos))
                    }
                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    }
                    builder.show()
                }
        }
        activityMainBinding.callback = callBack

        notiAdapter = NotiAdapter(this,callBack)
        val recyclerView = activityMainBinding.viewPost
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = SlideInLeftAnimator()
        recyclerView.adapter = notiAdapter
        allPost
        swipeRefreshLayout.setOnRefreshListener{
            allPost
            swipeRefreshLayout.isRefreshing = false
            notiAdapter?.notifyDataSetChanged()
            Log.d("AAAA","sizes : ${notiAdapter?.currentList?.size}")

        }
    }
    fun observeData() {
        mainViewModel?.getArticleLiveData()?.observeForever {
            notiAdapter?.submitList(it)

        }
        mainViewModel?.getNetWorkState()?.networkState?.observeForever {
            notiAdapter?.setNetworkState(it)
        }
    }
    private val allPost: Unit
        get() {
            mainViewModel?.allPost?.observe(this, Observer<List<Hit?>?> {
                observeData()

            })
        }
    override fun onClick(view: View, pos: Int) {
        when (view.id) {
            R.id.retry_button -> {
                mainViewModel?.getNetWorkState()?.retry?.invoke()
            }
            R.id.bt_accept -> {
                notiAdapter?.currentList?.get(pos)?.source?.checkAccept = true
                notiAdapter?.setItemChaged(pos)
            }
            R.id.bt_cancel -> {
                notiAdapter?.currentList?.get(pos)?.source?.checkAccept = true
                notiAdapter?.setItemChaged(pos)
            }
            R.id.checkbox_id -> {
                notiAdapter?.currentList?.get(pos)?.source?.checkAccept = true
            }
        }
    }
    override fun isChecked(hit: Hit?) {
        list.value?.add(hit!!)
        list.value = list.value
    }

    override fun unChecked(hit: Hit?, pos: Int) {
        list.value?.remove(hit!!)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_one) {
            val intent = Intent(this, ResultActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelableArrayList("Hit", list.value as ArrayList<out Parcelable> )
            intent.putExtra("BUNDLE", bundle)
            startActivity(intent)
            return true
        }
        if (id ==R.id.action_two){
            list.value?.forEach {
                if (list.value!=null){
                    it.checked = true
                    notiAdapter?.notifyDataSetChanged()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}







