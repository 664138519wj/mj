package com.migu.online.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//预约课程管理
@Alias("appointment_manage")
@Data
public class AppointmentManage implements Serializable {

	private static final long serialVersionUID = 181341196746442633L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String licenceType;
	private Integer kemu;
	private BigDecimal price;
	private Integer examPlaceId;
	private Integer limitCount;
	private Integer leftCount;
	private Integer isDelete;
	private Date startTime;
	private Date createTime;
	private Date updateTime;
	@Transient
	private Integer userAppointStatus; // 预约状态 0:未预约 1：已预约 线上未付款 2：已预约 线下未付款 3：已付款 4:申请退款中

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Integer examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	public Integer getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
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

	public Integer getUserAppointStatus() {
		return userAppointStatus;
	}

	public void setUserAppointStatus(Integer userAppointStatus) {
		this.userAppointStatus = userAppointStatus;
	}
		
}
