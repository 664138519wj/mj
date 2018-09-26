package com.migu.online.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import lombok.Data;

//用户
@Alias("user")
@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5516786843814211249L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String mobile;
	private String pwd;
	private String avatar;
	private String sessionId;
	private Integer isDelete;
	private String appNo;
	private Date lastLoginTime;
	private Date createTime;
	private Date updateTime;
	private String realName;
	private String idNo;
	private Date cardExpiredTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	
	public Date getCardExpiredTime() {
		return cardExpiredTime;
	}

	public void setCardExpiredTime(Date cardExpiredTime) {
		this.cardExpiredTime = cardExpiredTime;
	}

	/**
	 * 获取用户名字，如果name为空，返回mobile
	 * @return
	 */
	public String getUserName() {
		if (StringUtils.isEmpty(this.name)) {
			return this.mobile;
		}
		return this.name;
	}

}
