package com.sunsun.recycleview.component2.adapter

interface IAdapterListProvider<T> {

    fun getAdapterList(): List<T?>?
}