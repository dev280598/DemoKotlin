package com.example.test1.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR
import com.example.test1.adapter.NotiAdapter
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Hit
import com.example.test1.services.Presenter
import com.example.test1.services.onclickCallBack
import kotlinx.android.synthetic.main.item_invite.view.*
import kotlinx.android.synthetic.main.item_noti.*
import kotlinx.android.synthetic.main.post_list_item.view.*
import okhttp3.internal.notify

class NotifyListingItemViewHolder(var binding: PostListItemBinding, private val adapterOnclick: onclickCallBack) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(data: Hit?,position: Int, callBack: Presenter) {
        binding.callback = callBack
        binding.setVariable(BR.post, data)
        binding.executePendingBindings()

//
/*

        binding.btnDelete.setOnClickListener {
            Log.e("==","btnDelete")
            callBack.callBack()
        }
*/


       /* binding.root.btn_delete.setOnClickListener{
            callBack.callBack()
            adapterOnclick.onClick(it,position)
            if (data?.checked==true){
                adapterOnclick.isChecked(data)
            }
        }*/

        binding.root.checkbox_id.setOnClickListener {
            adapterOnclick.onClick(it,position)
            val isChecked: Boolean = binding.root.checkbox_id.isChecked
            data?.source!!.checkAccept.let {
                if (isChecked) {
                    adapterOnclick.isChecked(data)
                } else {
                    adapterOnclick.unChecked(data,position)
                }
            }
        }

    }
companion object {
    fun create(binding: PostListItemBinding, adapterOnclick: onclickCallBack): NotifyListingItemViewHolder {
        return NotifyListingItemViewHolder(binding, adapterOnclick)
    }
}
}