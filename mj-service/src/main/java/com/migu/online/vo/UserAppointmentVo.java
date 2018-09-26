package com.migu.online.vo;

import java.math.BigDecimal;

public class UserAppointmentVo {

	private Long id;
	private Long userId;
	private String licenceType;
	private String kemuStr;
	private BigDecimal price;
	private String examPlaceAddress;
	private String examPlaceName;
	private Integer status;
	private Integer payStatus;
	private String beginTime;
	private Integer payType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public String getKemuStr() {
		return kemuStr;
	}

	public void setKemuStr(String kemuStr) {
		this.kemuStr = kemuStr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getExamPlaceAddress() {
		return examPlaceAddress;
	}

	public void setExamPlaceAddress(String examPlaceAddress) {
		this.examPlaceAddress = examPlaceAddress;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
		
}
