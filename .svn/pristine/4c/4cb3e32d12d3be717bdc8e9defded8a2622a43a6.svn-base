package com.migu.online.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//用户购买付费视频
@Alias("customer_buy_course_onlineModel")
@Data
public class CustomerBuyCourseOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private Integer courseOnlineId;
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
	public Integer getCourseOnlineId() {
		return courseOnlineId;
	}
	public void setCourseOnlineId(Integer courseOnlineId) {
		this.courseOnlineId = courseOnlineId;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	 	 
}
