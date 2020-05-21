package com.example.test1.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.R
import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.databinding.ItemEmptyBinding
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.notify.Hit
import com.example.test1.utils.NetworkState
import java.util.*

class NotiAdapter:  PagedListAdapter<Hit, ViewHolder>(NotiDiff) {
    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1
    private var networkState: NetworkState? = null
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

        return if (LIST_KEY.containsKey(getItem(position)?.source?.iv104)
        ){
            NORMAL_ITEM
        } else {
            EMPTY_ITEM
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       when (holder.itemViewType) {
          NORMAL_ITEM -> initLayoutOne(holder as PostViewHolder, position)
           EMPTY_ITEM -> initLayoutTwo(holder as PostViewHolderEmpty, position)
           else -> {
            }
       }
    }
  private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
    private fun initLayoutOne(postViewHolder: PostViewHolder, i: Int) {
        val currentStudent = getItem(i)
        postViewHolder.postListItemBinding.post = currentStudent
    }
    private fun initLayoutTwo(postViewHolderEmpty: PostViewHolderEmpty, i: Int) {
        val currentStudent = getItem(i)
        postViewHolderEmpty.postListItemEmptyBinding.post = currentStudent
    }
    inner class PostViewHolder(val postListItemBinding: PostListItemBinding) : ViewHolder(postListItemBinding.root)
    inner class PostViewHolderEmpty(val postListItemEmptyBinding: ItemEmptyBinding) : ViewHolder(postListItemEmptyBinding.root)

}

object NotiDiff : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem._id == newItem._id
    }
    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem
    }


}







