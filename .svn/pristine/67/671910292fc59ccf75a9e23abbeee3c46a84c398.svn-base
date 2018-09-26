package com.migu.online.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//用户
@Alias("user_exam_record")
@Data
public class UserExamRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1473909832687637459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String userName;
	private String examMap;
	private int score;
	private int count;
	private int status;
	private int kemu;
	private int licType;
	private int type; // 0:模拟答题 1：自由考试
	private int language; // 0:中文 1：蒙语 2：哈语 3：维语
	private Date createTime;
	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	private Date updateTime;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExamMap() {
		return examMap;
	}

	public void setExamMap(String examMap) {
		this.examMap = examMap;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getKemu() {
		return kemu;
	}

	public void setKemu(int kemu) {
		this.kemu = kemu;
	}

	public int getLicType() {
		return licType;
	}

	public void setLicType(int licType) {
		this.licType = licType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
