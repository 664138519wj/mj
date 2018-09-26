package com.migu.online.vo;

import java.math.BigDecimal;

// 用户课程 在线课程
public class UserOnlineCourseVo {
	private Long id ;
	private Long userId;
	private Long courseId;
	private Integer type; // 课程类型
	private String title; // 课程名字
	private String content; // 描述
	/* 视频地址 */
	private String videoPath;
	private String imagePath;
	private BigDecimal price;
	private String time; // 视频时间长度 00：00：00
	/* 播放次数 */
	private Long playNumber;	
	// 上传时间
	private String updateTime;
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
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getPlayNumber() {
		return playNumber;
	}
	public void setPlayNumber(Long playNumber) {
		this.playNumber = playNumber;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
		
}
