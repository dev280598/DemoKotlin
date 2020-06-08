package com.example.test1.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.BR
import com.example.test1.databinding.PostListItemBinding
import com.example.test1.model.Hit
import com.example.test1.services.Presenter
import com.example.test1.services.onclickCallBack

class NotifyListingItemViewHolder(var binding: PostListItemBinding, private val adapterOnclick: onclickCallBack) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(data: Hit?,position: Int, callBack: Presenter) {
        binding.callback = callBack
        binding.index    =  position
        binding.setVariable(BR.post, data)
        binding.executePendingBindings()

        data?.checked?.let {
            if (it){
                adapterOnclick.isChecked(data)
            }
        }
        binding.checkboxId.setOnClickListener { view ->
            adapterOnclick.onClick(view,position)

            val isChecked : Boolean = binding.checkboxId.isChecked

            isChecked.let {
                if (it){
                    data?.source?.checkAccept =true
                    adapterOnclick.isChecked(data)
                }else{
                    data?.source?.checkAccept =false
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