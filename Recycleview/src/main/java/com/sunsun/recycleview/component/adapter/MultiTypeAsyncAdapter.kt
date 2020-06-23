package com.sunsun.recycleview.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunsun.recycleview.component.bean.IItem

class MultiTypeAsyncAdapter <T: IItem>(callback: DiffUtil.ItemCallback<T>) :
    BaseAdapter<T, MultiTypeAsyncAdapter.ItemViewHolder>(callback) {

    var binding: ViewDataBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val holder = ItemViewHolder.create(parent, viewType)
        binding = holder.binding
        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list = mDiffer!!.currentList
        val iItem = list[position]
        holder.bindTo(iItem)
    }

    override fun getItemCount(): Int {
        return mDiffer?.currentList?.size!!
    }

    override fun getItemViewType(position: Int): Int {
        return mDiffer?.currentList!![position].getLayoutType()
    }

    class ItemViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: IItem) {
            binding.setVariable(item.getVariableId(), item)
            binding.executePendingBindings()
        }
        companion object {
            fun create(parent: ViewGroup, viewType: Int): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    viewType, parent, false
                )
                return ItemViewHolder(binding)
            }
        }
    }

}