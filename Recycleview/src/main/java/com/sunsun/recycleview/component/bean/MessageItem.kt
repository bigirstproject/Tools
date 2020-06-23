package com.sunsun.recycleview.component.bean

import android.util.Log
import com.sunsun.recycleview.R
import com.sunsun.recycleview.BR

class MessageItem : IItem, ITemplate  {

    var content: String = "借1000元每天最低只需0.56元，最多可分12期"


    override fun getLayoutType(): Int {
        return R.layout.template_message_item
    }

    override fun getVariableId(): Int {
        return BR.item
    }

    override fun getTemplateID(): Int {
        return 190003
    }

    override fun bindData(data: String): IItem? {
        Log.d("MessageItem", data)
        return this
    }

}