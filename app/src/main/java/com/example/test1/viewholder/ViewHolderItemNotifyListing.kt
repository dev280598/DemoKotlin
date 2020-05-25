package com.example.test1.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR
import com.example.test1.R
import com.example.test1.model.Hit
import com.example.test1.services.onclickCallBack
import kotlinx.android.synthetic.main.item_invite.view.*

class NotifyListingItemViewHolder(val binding: ViewDataBinding, private val adapterOnclick: onclickCallBack) : RecyclerView.ViewHolder(binding.root) {


    fun bindTo(data: Hit) {
        binding.setVariable(BR.post, data)
        binding.executePendingBindings()
        data.source.checkAccept.let {
            if (it) {
                R.layout.item_empty
            } else {

            }
        }
        binding.root.findViewById<CheckBox>(R.id.checkbox_id).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                adapterOnclick?.evTest(buttonView, data)
            }
        }
    }
companion object {
    fun create(binding: ViewDataBinding, adapterOnclick: onclickCallBack): NotifyListingItemViewHolder {
        return NotifyListingItemViewHolder(binding, adapterOnclick)
    }
}
}