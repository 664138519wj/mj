package com.migu.online.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//线上课程
@Alias("course_online")
@Data
public class CourseOnline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 所属教师
	private Long teacherId;
	private String teacherName;
	// 类别 1,2,3,4 分别代表 科目一、二、三、四
	private Integer type;
	private String title;
	private String content;
	/* 视频地址 */
	private String videoPath;
	private String smallVideoPath; //  付费视频，提供5min的小视频
	private String imagePath;
	// 是否热点和付费 0-都不是,1-热点,2-付费,3-热点且付费
	private Integer isHotAndCharge;
	private Integer hour;
	private Integer minute;
	private Integer second;
	private BigDecimal price;
	/* 播放次数 */
	private Long playNumber;
	/* 是否显示在首页 1-是 */
	private Integer recommand;
	/* 列表页推荐 1-是 */
	private Integer isListRec;
	private Integer isDelete;
	private Integer sort;
	private Date createTime;
	private Date updateTime;
	private Date releaseTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsListRec() {
		return isListRec;
	}

	public void setIsListRec(Integer isListRec) {
		this.isListRec = isListRec;
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

	public Integer getIsHotAndCharge() {
		return isHotAndCharge;
	}

	public void setIsHotAndCharge(Integer isHotAndCharge) {
		this.isHotAndCharge = isHotAndCharge;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	public Long getPlayNumber() {
		return playNumber;
	}

	public void setPlayNumber(Long playNumber) {
		this.playNumber = playNumber;
	}

	public Integer getRecommand() {
		return recommand;
	}

	public void setRecommand(Integer recommand) {
		this.recommand = recommand;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSmallVideoPath() {
		return smallVideoPath;
	}

	public void setSmallVideoPath(String smallVideoPath) {
		this.smallVideoPath = smallVideoPath;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
