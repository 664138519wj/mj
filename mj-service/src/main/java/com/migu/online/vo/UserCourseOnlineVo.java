package com.migu.online.vo;

import java.math.BigDecimal;

//用户线上课程
public class UserCourseOnlineVo extends CourseOnlineVo{

	/**
	 * 是否已支付
	 */
	private Integer isPay; // 0:未下单 1：已支付 2：下单未完成支付
	
	/**
	 * 价格
	 */
	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	
}
