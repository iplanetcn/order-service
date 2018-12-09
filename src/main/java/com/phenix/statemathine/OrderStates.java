package com.phenix.statemathine;

/**
 * 枚举：订单状态
 */
public enum OrderStates {
    UNPAID,                 // 待支付
    WAITING_FOR_RECEIVE,    // 待收货
    DONE                    // 结束
}
