package com.migu.online.vo;

//线上课程 首页vo
public class CourseOnlineVo {

	private Long id;
	// 所属教师
	private Long teacherId;
	// 类别 1,2,3,4 分别代表 科目一、二、三、四
	private Integer type;
	private String title;
	/* 视频地址 */
	private String videoPath;
	private String smallVideoPath; //  付费视频，提供5min的小视频
	private String imagePath;
	// 是否热点和付费 0-都不是,1-热点,2-付费,3-热点且付费
	private Integer isHotAndCharge;
	private String time; // 视频时间长度 00：00：00
	/* 播放次数 */
	private Long playNumber;	
	private String updateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getIsHotAndCharge() {
		return isHotAndCharge;
	}
	public void setIsHotAndCharge(Integer isHotAndCharge) {
		this.isHotAndCharge = isHotAndCharge;
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

	public String getSmallVideoPath() {
		return smallVideoPath;
	}

	public void setSmallVideoPath(String smallVideoPath) {
		this.smallVideoPath = smallVideoPath;
	}	
	
}
