package com.migu.online.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//用户报名(购买)线下课程
@Alias("customer_buy_course_offlineModel")
@Data
public class CustomerBuyCourseOffline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private Integer courseOfflineId;
    private Date buyTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCourseOfflineId() {
		return courseOfflineId;
	}
	public void setCourseOfflineId(Integer courseOfflineId) {
		this.courseOfflineId = courseOfflineId;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	 
	 
}
