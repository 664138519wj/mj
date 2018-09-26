package com.migu.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//驾考报名用户信息
@Alias("user_appointment")
@Data
public class UserAppointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String userName;
	private Long appointId;
	private String licenceType;
	private Integer kemu;
	private BigDecimal price;
	private Integer payType; // 0：线上付款 1：线下付款
	private Integer examPlaceId;
	private Integer score;
	private String examPlaceName;
	private String examPlaceAddress;
	private String realName;
	private String idNo;
	private Integer status; // 预约状态 0:预约中 1：预约成功 2：预约失败
	private Integer payStatus; // 0：未付款 1：已付款 2:退款成功
	private Integer isDelete;
	private Date startTime;
	private Date createTime;
	private Date updateTime;

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

	public Integer getKemu() {
		return kemu;
	}

	public void setKemu(Integer kemu) {
		this.kemu = kemu;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Integer examPlaceId) {
		this.examPlaceId = examPlaceId;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Long getAppointId() {
		return appointId;
	}

	public void setAppointId(Long appointId) {
		this.appointId = appointId;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
		
}
