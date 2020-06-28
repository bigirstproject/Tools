package com.sunsun.recycleview.demo.bean

import com.sunsun.network.bean.BaseResp
import com.sunsun.recycleview.component2.bean.TypeBaseBean
import com.sunsun.recycleview.demo.ui.TYPE_REPLAYMENT_ITEM
import java.io.Serializable

data class RepaymentResp(var itemlist: ArrayList<BillListResp>?) : BaseResp()


data class BillListResp(
    val loanList: List<LoanItemBean>
) : BaseResp()

data class RepaymentTipBean(
    val id: Int,
    val content: String
) : Serializable

data class LoanItemBean(
    val loanGid: String//借款gid，如果oldOrderTag=1，表示老及贷的orderId
) : Serializable, TypeBaseBean(TYPE_REPLAYMENT_ITEM)


//还款的各种状态
const val INPAYMENT = 0 //打款中
const val LOAN_SUCESS = 1 //借款成功
const val DEFERRED_REPLAYMENT_PROCESSING = 2 //延期还款处理中
const val DEFERRED_REPLAYMENT_FAILURE = 3 //延期失败
const val DEFERRED_BORROWIN_SUCCESS = 4 //延期借款成功
const val BORROWIN_FAILURE = 5 //借款失败
const val WAITING_TO_BEPAID = 6 //借款成功,等待打款
const val ONREVIEWING = 7 //审核中
const val INAPPROVAL = 8 //审批中(风控交易验证需审批)


const val REPLAYSTATUS_PROCESSING = "0" //还款中
const val REPLAYSTATUS_SUCESS = "1" //还款成功
const val REPLAYSTATUS_FAIL = "2" //还款失败
const val REPLAYSTATUS_VERIFY_CODE = "3" //验证码失败
