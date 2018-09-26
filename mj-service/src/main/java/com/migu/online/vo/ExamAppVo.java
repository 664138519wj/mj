package com.migu.online.vo;

import java.io.Serializable;

import com.migu.online.model.ExamPlace;

public class ExamAppVo implements Serializable{

	private static final long serialVersionUID = -4060631006735814366L;

	private ExamPlace examPlace;
	
	private String startTimeStr;
	
	private Integer limitCount;
	
	private Integer leftCount;
	
	private Long appointmentId;
	
	private Integer userAppointStatus; // 预约状态 0:未预约 1：预约中 2：预约成功 

	public ExamPlace getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(ExamPlace examPlace) {
		this.examPlace = examPlace;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
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

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getUserAppointStatus() {
		return userAppointStatus;
	}

	public void setUserAppointStatus(Integer userAppointStatus) {
		this.userAppointStatus = userAppointStatus;
	}
	
}


