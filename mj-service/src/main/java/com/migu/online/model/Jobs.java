package com.migu.online.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/*招聘*/
@Alias("jobs")
@Data
public class Jobs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String post;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Long createUser;
	
	
}
