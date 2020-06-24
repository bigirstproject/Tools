package com.sunsun.recycleview.component2.adapter

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecycleViewAdapter<T, VH : RecyclerView.ViewHolder>() :
    RecyclerView.Adapter<VH>() {


    protected var mDatas: MutableList<T>? = null

    open fun setDatasWithoutNotify(datas: List<T>?) {
        if (mDatas == null) {
            mDatas = ArrayList()
        }
        mDatas?.clear()
        if (datas != null) mDatas?.addAll(datas)
    }

    open fun addDatas(datas: List<T>?) {
        if (mDatas == null) {
            mDatas = ArrayList()
        }
        mDatas?.addAll(datas!!)
        notifyDataSetChanged()
    }

    open fun addDatas(location: Int, datas: List<T>) {
        if (mDatas == null) {
            mDatas = ArrayList()
        }
        mDatas?.addAll(location, datas)
        notifyItemRangeInserted(location, datas.size)
    }

    open fun addData(data: T) {
        if (mDatas == null) {
            mDatas = ArrayList()
        }
        mDatas?.add(data)
        notifyItemInserted(mDatas!!.size - 1)
    }

    open fun addData(location: Int, data: T) {
        if (mDatas == null) {
            mDatas = ArrayList()
        }
        mDatas?.add(location, data)
        notifyItemInserted(location)
    }

    open fun getDatas(): List<T>? {
        return mDatas
    }

    open fun setDatas(datas: List<T>?) {
        if (mDatas == null) {
            mDatas = ArrayList()
        }
        mDatas?.clear()
        if (datas != null) {
            mDatas?.addAll(datas)
        }
        notifyDataSetChanged()
    }

    open fun removeData(position: Int): Boolean {
        if (mDatas != null && position >= 0 && position < mDatas!!.size) {
            mDatas?.removeAt(position)
            notifyItemRemoved(position)
            return true
        }
        return false
    }

    open fun clearDatas() {
        if (mDatas != null) {
            mDatas?.clear()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (mDatas != null) {
            mDatas!!.size
        } else 0
    }

}