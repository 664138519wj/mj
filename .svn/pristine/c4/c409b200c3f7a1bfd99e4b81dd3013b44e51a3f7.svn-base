package com.migu.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//用户购买课程
@Alias("user_course")
@Data
public class UserCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/* 平台网点id */
	private Long userId;
	private String userName;
	private Long courseId;
	private Integer courseType; //'课程类型 1：线上课程 2：线下课程 3：驾校课程
	private BigDecimal price;
	private Integer payStatus; // 0:未支付 1：支付等待反馈 2:支付成功 -1:失败 3:已退款
	private Date payTime; // 支付时间
	private String payCode; // 支付流水号
	private Integer isDelete;
	private Date createTime;
	private Date updateTime; 
	private Integer refundStatus; // 0:无退款 1：申请退款 2：退款成功
	private String refundBankNo; // 银行卡号
	private String refundBankUserName; // 持卡人姓名
	private String refundBankAddress; // 开户行
	private String outTradeNo; // 订单号唯一  生成规则 课程类型+userId+当前时间秒
	private String refundPayCode; // 退款支付流水
	private String tradeType; // 支付方式  微信：JSAPI、NATIVE、APP 支付宝：
	private Long publishCourseUserId; // 课程发布人id 可以为教师id或者驾校id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getRefundPayCode() {
		return refundPayCode;
	}

	public void setRefundPayCode(String refundPayCode) {
		this.refundPayCode = refundPayCode;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getRefundBankNo() {
		return refundBankNo;
	}

	public void setRefundBankNo(String refundBankNo) {
		this.refundBankNo = refundBankNo;
	}

	public String getRefundBankUserName() {
		return refundBankUserName;
	}

	public void setRefundBankUserName(String refundBankUserName) {
		this.refundBankUserName = refundBankUserName;
	}

	public String getRefundBankAddress() {
		return refundBankAddress;
	}

	public void setRefundBankAddress(String refundBankAddress) {
		this.refundBankAddress = refundBankAddress;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getPublishCourseUserId() {
		return publishCourseUserId;
	}

	public void setPublishCourseUserId(Long publishCourseUserId) {
		this.publishCourseUserId = publishCourseUserId;
	}
	
}
