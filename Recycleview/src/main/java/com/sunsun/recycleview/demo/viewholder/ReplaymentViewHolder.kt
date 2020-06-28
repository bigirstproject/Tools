package com.sunsun.recycleview.demo.viewholder

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.sunsun.recycleview.R
import com.sunsun.recycleview.component2.adapter.MultiTypeViewHolder
import com.sunsun.recycleview.demo.bean.*
import java.text.SimpleDateFormat
import java.util.*

class ReplaymentViewHolder(val view: View) : MultiTypeViewHolder<LoanItemBean>(view) {

    private val TAG: String = "ReplaymentViewHolder"

    override fun showData(data: LoanItemBean?) {
        var periods = itemView.findViewById<TextView>(R.id.relayment_periods)
        var currentSum = itemView.findViewById<TextView>(R.id.repayment_current_sum)
        var totalSum = itemView.findViewById<TextView>(R.id.repayment_total_sum)
        var firstPart = itemView.findViewById<LinearLayout>(R.id.first_part)
        var secondPart = itemView.findViewById<LinearLayout>(R.id.second_part)
        var repaymentCurrentSumName =
            itemView.findViewById<TextView>(R.id.repayment_current_sum_name)
        var repaymentTotalSumName = itemView.findViewById<TextView>(R.id.repayment_total_sum_name)
        var dateText = itemView.findViewById<TextView>(R.id.relayment_date)
        var button = itemView.findViewById<Button>(R.id.repayment_button)
        var status = itemView.findViewById<TextView>(R.id.repayment_status)
        var dis = itemView.findViewById<TextView>(R.id.repayment_dis)
    }

//    /**
//     * 还款
//     */
//    private fun dueDateTip(
//        dueTime: Int,
//        exceedDays: Int,
//        dateText: TextView,
//        button: Button,
//        dis: TextView
//    ) {
//
//        if (exceedDays > 0) {
//            dateText.text = "逾期" + exceedDays + "天"
//            dateText.setBackgroundResource(R.drawable.bg_rectangle_ffece8)
//            dateText.setTextColor(Color.parseColor("#FFFF3200"))
//            button.setBackgroundResource(R.drawable.selector_replayment_btn_red)
//            dis.visibility = View.VISIBLE
//            return
//        }
//        if (exceedDays == 0) {
//            dateText.text = "今天到期"
//            dateText.setBackgroundResource(R.drawable.bg_rectangle_ffece8)
//            dateText.setTextColor(Color.parseColor("#FFFF3200"))
//            button.setBackgroundResource(R.drawable.selector_replayment_btn_blue)
//            dis.visibility = View.GONE
//            return
//        }
//        if (exceedDays < 0) {
//            if (exceedDays == -1) {
//                dateText.text = "明天到期"
//                dateText.setBackgroundResource(R.drawable.bg_rectangle_ffece8)
//                dateText.setTextColor(Color.parseColor("#FFFF3200"))
//                button.setBackgroundResource(R.drawable.selector_replayment_btn_blue)
//                dis.visibility = View.GONE
//                return
//            }
//            if (exceedDays == -2) {
//                dateText.text = "后天到期"
//                dateText.setBackgroundResource(R.drawable.bg_rectangle_ffece8)
//                dateText.setTextColor(Color.parseColor("#FFFF3200"))
//                button.setBackgroundResource(R.drawable.selector_replayment_btn_blue)
//                dis.visibility = View.GONE
//                return
//            }
//
//        }
//        dis.visibility = View.GONE
//        button.setBackgroundResource(R.drawable.selector_replayment_btn_blue)
//        dateText.setTextColor(Color.parseColor("#FF000000"))
//        dateText.setBackgroundResource(R.color.white)
//        val sDateFormat = SimpleDateFormat("yyyy/MM/dd")
//        val dueTimeDes = sDateFormat.format(Date(dueTime * 1000L))
//        dateText.text = "还款日：" + dueTimeDes
//    }


}
