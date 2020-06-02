package com.example.test1.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test1.R
import com.example.test1.constant.Constants.Companion.LIST_KEY
import com.example.test1.databinding.ItemEmptyBinding
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Hit
import com.example.test1.constant.NetworkState
import com.example.test1.services.Presenter
import com.example.test1.services.onclickCallBack
import com.example.test1.viewholder.NetworkStateItemViewHolder
import com.example.test1.viewholder.NotifyListingItemViewHolder
import com.example.test1.viewholder.ViewHolderItemNotiInvite
import kotlin.coroutines.coroutineContext

class NotiAdapter(val adapterOnclick: onclickCallBack, val callback : Presenter):  PagedListAdapter<Hit, ViewHolder>(NotiDiff) {

    private var networkState: NetworkState? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val postListItemBinding: PostListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                R.layout.post_list_item, viewGroup, false)
        val itemEmptyBinding: ItemEmptyBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                R.layout.item_empty, viewGroup, false)
        val bindingItemNotifyInvite: ViewDataBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                        R.layout.item_invite, viewGroup, false)
        return when (i) {
            R.layout.post_list_item -> NotifyListingItemViewHolder.create(postListItemBinding, adapterOnclick)
            R.layout.item_empty ->PostViewHolderEmpty(itemEmptyBinding)
            R.layout.network_state_layout -> NetworkStateItemViewHolder.create(viewGroup, adapterOnclick)
            R.layout.item_invite -> ViewHolderItemNotiInvite.create(
                bindingItemNotifyInvite,
                adapterOnclick
            )
            else -> throw IllegalArgumentException("unknown view type $i")
        }
    }
    override fun getItemViewType(position: Int): Int {

        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_layout
        } else {
            if (getItem(position)?.source?.iv104.equals("noti_friend_invite_like")) {
                R.layout.item_invite
            } else if (!LIST_KEY.containsKey(getItem(position)?.source?.iv104) || getItem(position)?.checked==true) {
                R.layout.item_empty
            } else {
                R.layout.post_list_item
            }
        }
    }
    fun setItemChaged(position: Int){
        notifyItemChanged(position)
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            R.layout.item_invite -> (holder as ViewHolderItemNotiInvite).bindTo(getItem(position)!!, position)
            R.layout.post_list_item -> (holder as NotifyListingItemViewHolder).bindTo(getItem(position)!!,position,callback)
            R.layout.network_state_layout -> (holder as NetworkStateItemViewHolder).bindTo(
                    networkState, position)
            R.layout.item_empty-> initLayoutTwo(holder as PostViewHolderEmpty, position)
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
    private fun initLayoutTwo(postViewHolderEmpty: PostViewHolderEmpty, i: Int) {
        val currentStudent = getItem(i)
        postViewHolderEmpty.postListItemEmptyBinding.post = currentStudent
    }
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







