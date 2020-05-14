package com.example.test1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.R
import com.example.test1.adapter.PostAdapter.PostViewHolder
import com.example.test1.databinding.ItemEmptyBinding
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Data
import com.example.test1.model.Hit
import com.example.test1.viewholder.PostViewHolderEmpty
import java.util.*

class PostAdapter  : RecyclerView.Adapter<PostViewHolder>() {
    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1

    private var post: ArrayList<Data>? = null


    override fun getItemViewType(position: Int): Int {
        return if (post?.get(position)?.type.equals("halo_noti_like_post") || post?.get(position)?.type.equals("halo_noti_like_comment"))
            NORMAL_ITEM
        else
            EMPTY_ITEM
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
        val postListItemBinding: PostListItemBinding
        val itemEmptyBinding: ItemEmptyBinding
        return (if (i == NORMAL_ITEM) {
            postListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                    R.layout.post_list_item, viewGroup, false)
            PostViewHolder(postListItemBinding)
        } else {
            itemEmptyBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                    R.layout.item_empty, viewGroup, false)
            PostViewHolderEmpty(itemEmptyBinding)
        }) as PostViewHolder

    }

    override fun onBindViewHolder(postViewHolder: PostViewHolder, i: Int) {
        val currentStudent = post!![i]
        postViewHolder.postListItemBinding.post = currentStudent
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

    inner class PostViewHolder(val postListItemBinding: PostListItemBinding) : ViewHolder(postListItemBinding.root)
}