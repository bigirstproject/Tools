package com.sunsun.recycleview.demo.ui

import com.sunsun.recycleview.R
import com.sunsun.recycleview.component2.adapter.SimpleBaseRecycleViewAdapter
import com.sunsun.recycleview.demo.viewholder.ReplaymentViewHolder

class ReplaymentAdapter : SimpleBaseRecycleViewAdapter() {

    init {
        addTypeItem(TYPE_REPLAYMENT_ITEM, R.layout.layout_relayment_item, ReplaymentViewHolder::class.java)
    }
}

const val TYPE_REPLAYMENT_ITEM = 10002