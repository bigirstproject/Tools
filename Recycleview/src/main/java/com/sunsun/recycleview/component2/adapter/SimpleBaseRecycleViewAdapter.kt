package com.sunsun.recycleview.component2.adapter

import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunsun.recycleview.R
import com.sunsun.recycleview.component2.bean.TypeBaseBean
import java.lang.reflect.InvocationTargetException
import java.util.*

open class SimpleBaseRecycleViewAdapter():
    BaseRecycleViewAdapter<TypeBaseBean, MultiTypeViewHolder<TypeBaseBean>>() {

    val TYPE_DEFAULT = 10001
    val TYPE_HEAD = 10002
    val TYPE_BOTTOM = 10003

    private var mLayoutIDMap: SparseArray<Int>? = null

    private var mViewHolderMap: SparseArray<Class<*>>? = null

    private var mHeadBean: TypeBaseBean? = null

    private var mBottomBean: TypeBaseBean? = null

    init {
        mLayoutIDMap = SparseArray()
        mViewHolderMap = SparseArray()
    }

    fun addTypeItem(type: Int, layoutid: Int, viewholder: Class<*>?) {
        if (mLayoutIDMap == null || mViewHolderMap == null) {
            throw NullPointerException("mLayoutIDMap must not null or mViewHolderMap must not null")
        }
        mLayoutIDMap!!.put(type, layoutid)
        mViewHolderMap!!.put(type, viewholder)
        require(mLayoutIDMap!!.size() == mViewHolderMap!!.size()) { "mLayoutIDMap'size != mViewHolderMap'size" }
    }

    fun addTypeItem(layoutid: Int, viewholder: Class<*>?) {
        addTypeItem(TYPE_DEFAULT, layoutid, viewholder)
    }

    fun addHeadTypeItem(layoutid: Int, viewholder: Class<*>?) {
        addTypeItem(TYPE_HEAD, layoutid, viewholder)
    }

    fun addBottomTypeItem(layoutid: Int, viewholder: Class<*>?) {
        addTypeItem(TYPE_BOTTOM, layoutid, viewholder)
    }

    fun getHeadBean(): TypeBaseBean? {
        return mHeadBean
    }

    fun setHeadBean(bean: TypeBaseBean) {
        bean?.itemType = TYPE_HEAD
        mHeadBean = bean
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return if (mDatas == null) {
            true
        } else mDatas!!.isEmpty()
    }

    fun getBottomBean(): TypeBaseBean? {
        return mBottomBean
    }

    fun setBottomBean(bean: TypeBaseBean) {
        bean?.itemType = TYPE_BOTTOM
        mBottomBean = bean
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultiTypeViewHolder<TypeBaseBean> {
        if (mViewHolderMap != null && mLayoutIDMap != null && mLayoutIDMap!![viewType] != null) {
            val layoutId = mLayoutIDMap!![viewType]
            val view =
                LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            if (view != null) {
                try {
                    val holder: MultiTypeViewHolder<TypeBaseBean>? =
                        getTypeViewHolder(viewType, view)
                    if (holder != null) {
                        return holder
                    }
                } catch (e: Exception) {
                    Log.e("adapter", "Id:$layoutId")
                    e.printStackTrace()
                }
            }
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_template_loading, parent, false)
        return SimpleMultiTypeViewHolder(view)
    }

    /**
     * 通过反射实例化对应的ViewHolder
     *
     * @param type
     * @param view
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Throws(
        NoSuchMethodException::class,
        IllegalAccessException::class,
        InvocationTargetException::class,
        InstantiationException::class
    )
    protected fun getTypeViewHolder(
        type: Int,
        view: View?
    ): MultiTypeViewHolder<TypeBaseBean>? {
        if (mViewHolderMap != null) {
            val holderClass = mViewHolderMap!![type]
            if (holderClass != null) {
                val holderConstructor =
                    holderClass.getConstructor(
                        View::class.java
                    )
                var objects = holderConstructor.newInstance(view)
                if (objects != null) {
                    return objects as MultiTypeViewHolder<TypeBaseBean>
                }
            }
        }
        return null
    }

    override fun onBindViewHolder(
        holder: MultiTypeViewHolder<TypeBaseBean>,
        position: Int
    ) {
        if (mHeadBean != null && position == 0) {
            holder.showData(mHeadBean)
            return
        }
        if (mDatas != null) {
            var newPosition = position
            //重点留意对应的position
            if (mHeadBean != null) {
                newPosition = position - 1
            }
            if (newPosition >= 0 && newPosition < mDatas!!.size) {
                val bean = mDatas!![newPosition]
                holder.showData(bean)
                return
            }
        }
        if (mBottomBean != null) {
            holder.showData(mBottomBean)
        }
    }

    override fun onBindViewHolder(
        holder: MultiTypeViewHolder<TypeBaseBean>,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        if (mHeadBean != null && position == 0) {
            holder.refreshData(mHeadBean)
            return
        }
        if (mDatas != null) {
            var newPosition = position
            //重点留意对应的position
            if (mHeadBean != null) {
                newPosition = position - 1
            }
            if (newPosition >= 0 && newPosition < mDatas!!.size) {
                val bean = mDatas!![newPosition]
                holder.refreshData(bean)
                return
            }
        }
        if (mBottomBean != null) {
            holder.refreshData(mBottomBean)
        }
    }

    fun getTypeBaseBean(
        data: List<TypeBaseBean?>?,
        type: Int
    ): List<TypeBaseBean?>? {
        var newData: MutableList<TypeBaseBean?>? = null
        if (data != null && !data.isEmpty()) {
            newData = ArrayList()
            for (item in data) {
                item?.itemType = type
                newData.add(item)
            }
        }
        return newData
    }

    fun getExternTypeBaseBean(
        data: List<TypeBaseBean?>?,
        extype: String?
    ): List<TypeBaseBean?>? {
        var newData: MutableList<TypeBaseBean?>? = null
        if (data != null && !data.isEmpty()) {
            newData = ArrayList()
            for (item in data) {
                item?.externType = extype
                newData.add(item)
            }
        }
        return newData
    }


    fun getTypeBaseBean(data: List<TypeBaseBean?>?): List<TypeBaseBean?>? {
        return getTypeBaseBean(data, TYPE_DEFAULT)
    }

    fun isPositionIncludeInData(position: Int): Boolean {
        return mDatas != null && position >= 0 && position < mDatas!!.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && mHeadBean != null) {
            return TYPE_HEAD
        }
        if (mDatas != null) {
            var itemPosition = position
            //重点留意对应的position
            if (mHeadBean != null) {
                itemPosition = position - 1
            }
            if (itemPosition >= 0 && itemPosition < mDatas!!.size) {
                return mDatas!![itemPosition].itemType
            }
        }
        return TYPE_BOTTOM
    }

    fun getDataPosition(adapterposition: Int): Int {
        var adapterposition = adapterposition
        if (getHeadBean() != null) {
            adapterposition--
        }
        return adapterposition
    }

    override fun getItemCount(): Int {
        var count = 0
        if (mDatas != null) {
            count = mDatas!!.size
        }
        if (mHeadBean != null) {
            count++
        }
        if (mBottomBean != null) {
            count++
        }
        return count
    }

    fun destroy() {
        if (mLayoutIDMap != null) {
            mLayoutIDMap!!.clear()
            mLayoutIDMap = null
        }
        if (mViewHolderMap != null) {
            mViewHolderMap!!.clear()
            mViewHolderMap = null
        }
        mHeadBean = null
        mBottomBean = null
    }

}