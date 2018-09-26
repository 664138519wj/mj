package com.migu.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//线下课程、培训计划
@Alias("courseOffline")
@Data
public class CourseOffline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*平台网点id*/
    private Long platId ;
    private String title;
    private BigDecimal price;
    private String content;
    private String imagePath; 
    /*课长*/
    private String hours;
	/* 开始时间 */
    private Date startTime;
    /*授课地址*/
    private String address;
    /*是否显示在首页 1-是 */
    private Integer recommand;
    /*教师-用逗号拼接教师id的字符串*/
    private String teachers;
    private Integer isDelete;
    private Integer sort;
    private Date createTime;
    private Date updateTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlatId() {
		return platId;
	}
	public void setPlatId(Long platId) {
		this.platId = platId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
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
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRecommand() {
		return recommand;
	}
	public void setRecommand(Integer recommand) {
		this.recommand = recommand;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
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
    
}
