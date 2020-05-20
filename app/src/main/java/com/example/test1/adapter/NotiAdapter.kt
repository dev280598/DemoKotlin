package com.example.test1.adapter

import NewsListAdapter.Companion.DataDiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.R
import com.example.test1.constant.Constants.Companion.LIST_KEY

import com.example.test1.databinding.ItemEmptyBinding
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Data
import com.example.test1.testDemo.State
import java.util.*

class NotiAdapter():
        PagedListAdapter<com.example.test1.testDemo.Data, ViewHolder>(DataDiffCallback) {
    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1
    private var post: ArrayList<Data>? = null
    private var state = State.LOADING

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
       return if (i == NORMAL_ITEM) {
            val postListItemBinding: PostListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                    R.layout.post_list_item, viewGroup, false)
            PostViewHolder(postListItemBinding)
        } else {
            val itemEmptyBinding: ItemEmptyBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                    R.layout.item_empty, viewGroup, false)
            PostViewHolderEmpty(itemEmptyBinding)
        }

    }
    override fun getItemViewType(position: Int): Int {
        return if (LIST_KEY.containsKey(post?.get(position)?.noti)
        ){
            NORMAL_ITEM
        } else {
            EMPTY_ITEM
        }
    }
    override fun getItemCount(): Int {

        return if (post != null) {
            post!!.size
        } else {
            0
        }
    }
    fun setPostList(post: ArrayList<Data>?) {
        this.post = post
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       when (holder.itemViewType) {
          NORMAL_ITEM -> initLayoutOne(holder as PostViewHolder, position)
           EMPTY_ITEM -> initLayoutTwo(holder as PostViewHolderEmpty, position)
           else -> {
            }
       }

    }
    private fun initLayoutOne(postViewHolder: PostViewHolder, i: Int) {
        val currentStudent = post!![i]
        postViewHolder.postListItemBinding.post = currentStudent
    }
    private fun initLayoutTwo(postViewHolderEmpty: PostViewHolderEmpty, i: Int) {
        val currentStudent = post!![i]
        postViewHolderEmpty.postListItemEmptyBinding.post = currentStudent
    }
    inner class PostViewHolder(val postListItemBinding: PostListItemBinding) : ViewHolder(postListItemBinding.root)
    inner class PostViewHolderEmpty(val postListItemEmptyBinding: ItemEmptyBinding) : ViewHolder(postListItemEmptyBinding.root)
}