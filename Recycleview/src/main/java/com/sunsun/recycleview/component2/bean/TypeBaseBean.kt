package com.sunsun.recycleview.component2.bean

import java.io.Serializable


class TypeBaseBean : Serializable {

    companion object {

        private const val serialVersionUID = -2194088845482279330L
    }

    /**
     * 是否展开，默认不展开
     */
     var isUnfold = false

    /**
     * 拓展数据类型，方便开发者自定义数据类型进行拓展
     */
     var externType: String? = null

    var isSelected = false

    /**
     * 可以自带一个拓展数据
     */
     var mObject: Any? = null

    /**
     * 是否占位bean 不显示，只占位
     */
     var isTempBean = false

     var itemType = 0


}