package com.sunsun.recycleview.component.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<B, V : RecyclerView.ViewHolder>(callback: DiffUtil.ItemCallback<B>) :
    RecyclerView.Adapter<V>() {

    protected var mDiffer: AsyncListDiffer<B>? = null

    init {
        mDiffer = AsyncListDiffer(this, callback)
    }

    fun setData(mData: B?) {
        if (mData == null || mDiffer == null) {
            return
        }
        val mList = ArrayList(mDiffer!!.currentList)
        mList.add(mData)
        mDiffer!!.submitList(mList)
    }

    fun setData(mData: List<B>?) {
        if (mData == null || mDiffer == null) {
            return
        }
        val mList = ArrayList(mData)
        mDiffer!!.submitList(mList)
    }


    fun removeData(index: Int) {
        if (mDiffer == null) {
            return
        }
        val mList = ArrayList(mDiffer!!.currentList)
        mList.removeAt(index)
        mDiffer!!.submitList(mList)
    }

    fun clear() {
        if (mDiffer == null) {
            return
        }
        mDiffer!!.submitList(null)
    }


}
