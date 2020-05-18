package com.example.test1.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR

class ViewHolderItemListtening(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind (data: Any){
        binding.setVariable(BR.noti_invite,data)
        binding.executePendingBindings()
    }
}