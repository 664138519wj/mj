package com.migu.online.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//vip课程管理 用户购买
@Alias("user_course_vip")
@Data
public class UserCourseVip implements Serializable {

	private static final long serialVersionUID = 181341196746442633L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;// '用户id',
	private Long userCourseId;// '用户报名课程id',
	private String mobile;// '用户手机号',
	private Integer payStatus;// '0:未支付 1：支付等待反馈 2:支付成功',
	private Integer assignStatus;// '0:未分配 1：已分配 2：部分分配',
	private BigDecimal price;// '价格',
	private Date payTime; // 支付时间
	private Integer refundStatus;// '0:无退款 1：申请退款 2：退款成功',
	private String kemus;// '1：科目一 2：科目二 3：科目三 4：科目四 5:4类组合' 逗号分隔,
	private Long kemuTeacherId1;// '科目1分配老师id',
	private String kemuTeacherName1;// '科目1分配老师name',
	private String kemuTeacherMobile1;// '科目1分配老师手机号',
	private Long kemuTeacherId2;// '科目2分配老师id',
	private String kemuTeacherName2;// '科目2分配老师name',
	private String kemuTeacherMobile2;// '科目2分配老师手机号',
	private Long kemuTeacherId3;// '科目3分配老师id',
	private String kemuTeacherName3;// '科目3分配老师name',
	private String kemuTeacherMobile3;// '科目3分配老师手机号',
	private Long kemuTeacherId4;// '科目4分配老师id',
	private String kemuTeacherName4;// '科目4分配老师name',
	private String kemuTeacherMobile4;// '科目4分配老师手机号',
	private Date beginTime;// '开课时间',
	private Date createTime;// '创建时间',
	private Date updateTime;// '更新时间',
	private Integer isDelete;// '是否删除',

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getKemus() {
		return kemus;
	}

	public void setKemus(String kemus) {
		this.kemus = kemus;
	}

	public Long getKemuTeacherId1() {
		return kemuTeacherId1;
	}

	public void setKemuTeacherId1(Long kemuTeacherId1) {
		this.kemuTeacherId1 = kemuTeacherId1;
	}

	public String getKemuTeacherName1() {
		return kemuTeacherName1;
	}

	public void setKemuTeacherName1(String kemuTeacherName1) {
		this.kemuTeacherName1 = kemuTeacherName1;
	}

	public String getKemuTeacherMobile1() {
		return kemuTeacherMobile1;
	}

	public void setKemuTeacherMobile1(String kemuTeacherMobile1) {
		this.kemuTeacherMobile1 = kemuTeacherMobile1;
	}

	public Long getKemuTeacherId2() {
		return kemuTeacherId2;
	}

	public void setKemuTeacherId2(Long kemuTeacherId2) {
		this.kemuTeacherId2 = kemuTeacherId2;
	}

	public String getKemuTeacherName2() {
		return kemuTeacherName2;
	}

	public void setKemuTeacherName2(String kemuTeacherName2) {
		this.kemuTeacherName2 = kemuTeacherName2;
	}

	public String getKemuTeacherMobile2() {
		return kemuTeacherMobile2;
	}

	public void setKemuTeacherMobile2(String kemuTeacherMobile2) {
		this.kemuTeacherMobile2 = kemuTeacherMobile2;
	}

	public Long getKemuTeacherId3() {
		return kemuTeacherId3;
	}

	public void setKemuTeacherId3(Long kemuTeacherId3) {
		this.kemuTeacherId3 = kemuTeacherId3;
	}

	public String getKemuTeacherName3() {
		return kemuTeacherName3;
	}

	public void setKemuTeacherName3(String kemuTeacherName3) {
		this.kemuTeacherName3 = kemuTeacherName3;
	}

	public String getKemuTeacherMobile3() {
		return kemuTeacherMobile3;
	}

	public void setKemuTeacherMobile3(String kemuTeacherMobile3) {
		this.kemuTeacherMobile3 = kemuTeacherMobile3;
	}

	public Long getKemuTeacherId4() {
		return kemuTeacherId4;
	}

	public void setKemuTeacherId4(Long kemuTeacherId4) {
		this.kemuTeacherId4 = kemuTeacherId4;
	}

	public String getKemuTeacherName4() {
		return kemuTeacherName4;
	}

	public void setKemuTeacherName4(String kemuTeacherName4) {
		this.kemuTeacherName4 = kemuTeacherName4;
	}

	public String getKemuTeacherMobile4() {
		return kemuTeacherMobile4;
	}

	public void setKemuTeacherMobile4(String kemuTeacherMobile4) {
		this.kemuTeacherMobile4 = kemuTeacherMobile4;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getAssignStatus() {
		return assignStatus;
	}

	public void setAssignStatus(Integer assignStatus) {
		this.assignStatus = assignStatus;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getKemuStr(String kemus) {
        String[] args = kemus.split(",");
        String kemuStr = "";
        for (int i = 0 ;i < args.length; i ++) {
        	String kemu = "";
        	if (args[i].equals("1")) {
        		kemu = "科目一";
        	} else if (args[i].equals("2")) {
        		kemu = "科目二";
        	} else if (args[i].equals("3")) {
        		kemu = "科目三";
        	} else if (args[i].equals("4")) {
        		kemu = "科目四";
        	} else if (args[i].equals("5")) {
        		kemu = "组合套餐";
        	}
        	kemuStr += kemu + " ";
        }
        return kemuStr;
	}
	
}
