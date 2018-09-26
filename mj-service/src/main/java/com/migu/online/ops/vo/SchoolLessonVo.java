package com.migu.online.ops.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.migu.online.model.UserCourse;

/*驾校课程*/

public class SchoolLessonVo {

	private Long id;
	private Integer schoolId;
	private String name;
	private String licence;
	private BigDecimal price;
	private Integer isDelete;
	private Date startTime;
	private Date updateTime;
	private int limitNum;
	private Date createTime;
	
	//驾校名称
	private String schoolName;
	//课程名称
	private String className;
	//班级编号
	private String classNo;
	
	//报名人数列表
	
	Integer userCourseNum;
	List<UserCourse> userCourses;
	
	String userCourseNumLabel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

	public Integer getUserCourseNum() {
		return userCourseNum;
	}

	public void setUserCourseNum(Integer userCourseNum) {
		this.userCourseNum = userCourseNum;
	}

	public String getUserCourseNumLabel() {
		return userCourseNumLabel;
	}

	public void setUserCourseNumLabel(String userCourseNumLabel) {
		this.userCourseNumLabel = userCourseNumLabel;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	
	

}
