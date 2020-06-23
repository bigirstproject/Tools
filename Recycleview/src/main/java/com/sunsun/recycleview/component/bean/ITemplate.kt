package com.sunsun.recycleview.component.bean

interface ITemplate {
    fun getTemplateID(): Int
    fun bindData(data: String): IItem?
}