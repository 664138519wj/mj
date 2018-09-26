package com.migu.online.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//驾考报名用户信息
@Alias("user_course_info")
@Data
public class UserCourseInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/* 平台网点id */
	private Long userId;
	private Long userCourseId; // 用户报名课程id 关联usercourse表id
	private String userName;
	private int sex;
	private int firstApply;
	private int payType;
	private String telNo;
	private String idUp;
	private String idDown;
	private Integer isDelete;
	private Date createTime;
	private Date updateTime;
	private int status; // 0:报名中 1:报名成功 2:报名失败（费用原路返回）
	private String reason; // 0:报名中 1:报名成功 2:报名失败（费用原路返回）  -1:未报名
	private String schoolInfo;
	private Long schoolLessonId;  
	private Integer schoolId; 
	
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

	public Long getUserCourseId() {
		return userCourseId;
	}

	public void setUserCourseId(Long userCourseId) {
		this.userCourseId = userCourseId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getFirstApply() {
		return firstApply;
	}

	public void setFirstApply(int firstApply) {
		this.firstApply = firstApply;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getIdUp() {
		return idUp;
	}

	public void setIdUp(String idUp) {
		this.idUp = idUp;
	}

	public String getIdDown() {
		return idDown;
	}

	public void setIdDown(String idDown) {
		this.idDown = idDown;
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

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(String schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public Long getSchoolLessonId() {
		return schoolLessonId;
	}

	public void setSchoolLessonId(Long schoolLessonId) {
		this.schoolLessonId = schoolLessonId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
}
