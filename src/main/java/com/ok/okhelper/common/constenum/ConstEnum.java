package com.ok.okhelper.common.constenum;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 9:26 2018/4/16
*/

public enum ConstEnum {
	STATUSENUM_AVAILABLE("可用",1),
	STATUSENUM_UNAVAILABLE("不可用",0),

	ROLE_STOREMANAGER("店长",2),

	SALESTATUS_NOPAYMENT("未付款",1),
	SALESTATUS_DEBT("未付全款",2),
	SALESTATUS_PAID("已付全款",3),
	SALESTATUS_SUCCESS("交易成功",4),
	SALESTATUS_CLOSE("交易关闭",5),

	LOGISTICSSTATUS_NOSEND("未发货",1),
    LOGISTICSSTATUS_SEND("已发货", 2),
	LOGISTICSSTATUS_RECEIVED("已经送达",3),

	PAYTYPE_CASH("现金交易",1),
	PAYTYPE_ALIPAY("支付宝",2),
	PAYTYPE_WEICHAT("微信",3),
	PAYTYPE_POS("刷卡交易",4),

	TRADETYPE_FIRST("订单首次支付",1),

	TRADETYPE_REPAYMENT("订单还款支付",2);


	// 成员变量
	private String description;
	private int code;

	ConstEnum(String description, int code) {
		this.description = description;
		this.code = code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}
}