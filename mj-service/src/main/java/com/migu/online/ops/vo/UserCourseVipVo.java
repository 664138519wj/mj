package com.migu.online.ops.vo;

import java.math.BigDecimal;

import com.migu.online.model.UserCourseVip;

//线下课程首页显示vo
public class UserCourseVipVo extends UserCourseVip{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6109456013040836019L;

	private String kemuTitle;
	
	private String  kemuContent;

	private BigDecimal kemuPrice;

	public String getKemuTitle() {
		return kemuTitle;
	}

	public void setKemuTitle(String kemuTitle) {
		this.kemuTitle = kemuTitle;
	}

	public String getKemuContent() {
		return kemuContent;
	}

	public void setKemuContent(String kemuContent) {
		this.kemuContent = kemuContent;
	}

	public BigDecimal getKemuPrice() {
		return kemuPrice;
	}

	public void setKemuPrice(BigDecimal kemuPrice) {
		this.kemuPrice = kemuPrice;
	}
	
	

	
}
