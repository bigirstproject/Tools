package com.sunsun.recycleview.component.bean

import android.util.Log
import com.sunsun.recycleview.R
import com.sunsun.recycleview.BR

class BannerItem : IItem, ITemplate  {

    var interval: Int = 3

    override fun getLayoutType(): Int {
        return R.layout.template_banner_view_item
    }

    override fun getVariableId(): Int {
        return BR.item
    }

    override fun getTemplateID(): Int {
        return 190003
    }
    override fun bindData(data: String): IItem? {
        Log.d("BannerItem", data)
        return this
    }

}