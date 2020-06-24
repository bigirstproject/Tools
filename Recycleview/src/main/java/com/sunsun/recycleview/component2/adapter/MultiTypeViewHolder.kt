package com.sunsun.recycleview.component2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunsun.recycleview.component2.bean.TypeBaseBean

abstract class MultiTypeViewHolder<T : TypeBaseBean>(view: View) :
    RecyclerView.ViewHolder(view) {

    /**
     * 添加获取Adapter的数据列表List的方法
     */
     var adapterListProvider:IAdapterListProvider<T>? = null

    /**
     * 适配ListView记录position
     */
    var position:Int? = 0

    open fun setPosition(position: Int) {
        this.position = position
    }

    open fun setAdapterProvider(provider: IAdapterListProvider<T>? ) {
        adapterListProvider = provider
    }

    //整個item刷新使用，結合notifyItemRangeChanged(int positionStart, int itemCount)
    abstract fun showData(data: T?)

    //局部刷新使用，結合notifyItemRangeChanged(int positionStart, int itemCount, Object payload)，payload
    open fun refreshData(data: T?) {

    }


}