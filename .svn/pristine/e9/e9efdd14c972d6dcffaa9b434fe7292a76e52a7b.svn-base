package com.migu.online.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Alias("commonModel")
@Data
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*sysuserid*/
    private Integer userId;
    
    /*平台-1*/
    /*驾校-2*/
    /*教师-3*/
    private String type;
    
    /*教师-学校id*/
    /*驾校-区域id*/
    private Integer belongId;
    
    /*教师-名称*/
    /*驾校-名称*/
    private String name;
    
    /*教师-教龄*/
    private Integer teachingAge;
    
    /*教师-头像*/
    /*驾校-图片*/
    private String imagePath;
    
    /*教师-标签-用逗号拼接标签id的字符串*/
    /*驾校-用逗号拼接驾照类型id的字符串*/
    private String tag;
    
    /*教师-简介*/
    /*驾校-简介*/
    private String introduce;
    
    /*教师-授课地点*/
    /*驾校-地址*/
    private String address;
    
    /*教师-电话*/
    /*驾校-电话*/
    private String tel;
    
    /*教师-是否显示在首页 1-是 */
    private Integer showHomepage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeachingAge() {
		return teachingAge;
	}

	public void setTeachingAge(Integer teachingAge) {
		this.teachingAge = teachingAge;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getShowHomepage() {
		return showHomepage;
	}

	public void setShowHomepage(Integer showHomepage) {
		this.showHomepage = showHomepage;
	}
    
    
    
}
