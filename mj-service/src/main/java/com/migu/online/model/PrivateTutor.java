package com.migu.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//地区
@Alias("privateTutor")
@Data
public class PrivateTutor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long teacherId;// '教师id',
	private int type;// '1：科目一 2：科目二 3：科目三 4：科目四',
	private String title;// '标题',
	private String content;// '内容描述',
	private String imagePath;// '图片地址',
	private BigDecimal price;// '价格',
	private Date beginTime;// '开课时间',
	private String address;// '开课地址',
	private int recommand;// '1:首页推荐 0：不推荐',
	private int isListRec;// '1:列表页推荐 0：不推荐',
	private int isDelete;// '是否删除',
	private Date createTime;// '创建时间',
	private Date updateTime;// '更新时间',

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

	public int getRecommand() {
		return recommand;
	}

	public void setRecommand(int recommand) {
		this.recommand = recommand;
	}

	public int getIsListRec() {
		return isListRec;
	}

	public void setIsListRec(int isListRec) {
		this.isListRec = isListRec;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
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
