package com.migu.online.vo;

import java.util.Date;
import java.util.List;

//线下课程首页显示vo
public class CourseOfflineDetailVo {

	private Long id;
	private String title;
	private Double price;
	private String content;
	private String imagePath;
	private String address;
	/* 开始时间 */
    private String startTime;
    /* 教师列表 */
    List<TeacherDetailVo> teacherList;
    /*课长*/
    private String hours;
    /*是否支付*/
    private Integer isPay;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public List<TeacherDetailVo> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<TeacherDetailVo> teacherList) {
		this.teacherList = teacherList;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
		
}
