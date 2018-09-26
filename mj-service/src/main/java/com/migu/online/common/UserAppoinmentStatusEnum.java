package com.migu.online.common;

/**
 * 预约课程状态
 * @author fanyunlong
 *
 */
public enum UserAppoinmentStatusEnum {
	// 预约状态 0:预约成功 1：线下支付完成 2：线上支付完成 3：完成考试 4：取消 5:申请退款 6:退款成功
	APPOINT_SUCCESS(0, "预约成功"),
    PAY_SUCCESS_OFFLINE(1, "在线视频课程"),
    PAY_SUCCESS_ONLINE(2, "线下网点课程"),
    FINISH_EXAM(3, "驾校课程"),
    CANCEL(4, "在线视频课程"),
    APPLY_REFUND(5, "线下网点课程"),
    REFUND_SUCCESS(6, "驾校课程");
    
    public final int code;
    public final String desc;

    UserAppoinmentStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
