package com.example.test1.viewholder

import android.view.View
import android.widget.Button
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR
import com.example.test1.R
import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack
import kotlinx.android.synthetic.main.item_invite.view.*


class ViewHolderItemNotiInvite(
    val binding: ViewDataBinding,
    private val adapterOnclick: onclickCallBack
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(data: Hit, position: Int) {
        binding.setVariable(BR.post,data)
        binding.executePendingBindings()
        data.source.checkAccept.let {
            if (it) {
                binding.root.bt_accept.visibility = View.INVISIBLE
                binding.root.bt_cancel.visibility = View.INVISIBLE
                binding.root.tv_sttAccept.visibility=View.VISIBLE
            } else {
                binding.root.bt_accept.visibility = View.VISIBLE
                binding.root.bt_cancel.visibility = View.VISIBLE
                binding.root.tv_sttAccept.visibility=View.INVISIBLE
            }
        }
        binding.root.findViewById<Button>(R.id.bt_accept).setOnClickListener {
            adapterOnclick.onClick(it, position)
        }
        binding.root.findViewById<Button>(R.id.bt_cancel).setOnClickListener {
            adapterOnclick.onClick(it, position)

        }
    }
    companion object {
        fun create(
            binding: ViewDataBinding,
            adapterOnclick: onclickCallBack
        ): ViewHolderItemNotiInvite {
            return ViewHolderItemNotiInvite(binding, adapterOnclick)
        }
    }
}