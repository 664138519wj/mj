package com.migu.online.ops.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.migu.online.model.UserCourse;

//线下课程首页显示vo
public class StuPrivateTutorOpsVo extends UserCourse{

	private Long teacherId;// '教师id',
	private int type;// '1：科目一 2：科目二 3：科目三 4：科目四',
	private String title;// '标题',
	private String content;// '内容描述',
	private String imagePath;// '图片地址',
	private BigDecimal price;// '价格',
	private Date beginTime;// '开课时间',
	private String address;// '开课地址',
	private String teacherName;	
    private String kemu;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getKemu() {
		return kemu;
	}

	public void setKemu(String kemu) {
		this.kemu = kemu;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
