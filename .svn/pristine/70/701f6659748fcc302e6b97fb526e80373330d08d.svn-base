package com.migu.online.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

//vip课程管理
@Alias("vip_course")
@Data
public class VipCourse implements Serializable {

	private static final long serialVersionUID = 181341196746442633L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer kemu;
	private BigDecimal price;
	private String title;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getKemu() {
		return kemu;
	}
	public void setKemu(Integer kemu) {
		this.kemu = kemu;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	
		
}
