package com.migu.online.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/*媒体*/
@Alias("media")
@Data
public class Media {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String imgUrls;
	private Date createTime;
	private Date updateTime;
	private Long createUser;
	
	
}
