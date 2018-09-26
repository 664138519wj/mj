package com.migu.online.ops.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.migu.online.model.Student;

/**
 * 学员驾校报名VO
 * @author wangjin
 *
 */
public class UserSchoolCourseVo{
	private Long id;
	private Long userId;
	private Long userCourseId;
	private String userName;
	private Integer sex;
	private Integer firstApply;
	private Integer payType;
	private String telNo;
	private String idUp;
	private String idDown;
	private Integer isDelete;
	private Date createTime;
	private Date updateTime;
	private Integer refundStatus;
	private Long schoolId;
	private String schoolName;
	private String licenceName;
	private String licence;
	private BigDecimal price;
	private Date startTime;
	private int status; // 0:报名中 1:报名成功 2:报名失败（费用原路返回）
	private int payStatus; // 0:未付款 2:付款完成
	private int studentId;
	
	//课程名称
	private String className;
	//班级编号
	private String classNo;
	private String startTimeStr;
	//是否预录入
	private int isPreEntry;
	//预录入学员信息
	private Student preEntryStudent;
	
	
	
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLicenceName() {
		return licenceName;
	}
	public void setLicenceName(String licenceName) {
		this.licenceName = licenceName;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getFirstApply() {
		return firstApply;
	}
	public void setFirstApply(Integer firstApply) {
		this.firstApply = firstApply;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
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
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public Student getPreEntryStudent() {
		return preEntryStudent;
	}
	public void setPreEntryStudent(Student preEntryStudent) {
		this.preEntryStudent = preEntryStudent;
	}
	public int getIsPreEntry() {
		return isPreEntry;
	}
	public void setIsPreEntry(int isPreEntry) {
		this.isPreEntry = isPreEntry;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
}
