package com.migu.online.vo;

import java.io.Serializable;

/**
 * 考试题库
 * 
 * @author fanyunlong
 *
 */
public class UserExamRecordVo implements Serializable {

	private static final long serialVersionUID = 7151977338441172009L;

	private Long id;
	private int errorRate;
	private int count;
	private int kemu;
	private String licTypeStr;
	private Long examId;
	private int type; // 0:模拟考试 1：自由练习
	private String createTimeStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getErrorRate() {
		return errorRate;
	}

	public void setErrorRate(int errorRate) {
		this.errorRate = errorRate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getKemu() {
		return kemu;
	}

	public void setKemu(int kemu) {
		this.kemu = kemu;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getLicTypeStr() {
		return licTypeStr;
	}

	public void setLicTypeStr(String licTypeStr) {
		this.licTypeStr = licTypeStr;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
		
}
