package com.sunsun.recycleview.component.bean

interface IItem {

    //布局文件
    fun getLayoutType(): Int

    //对应layout中variable
    fun getVariableId(): Int

}