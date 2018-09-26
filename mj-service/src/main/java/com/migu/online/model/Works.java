package com.migu.online.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/*作品*/
@Alias("works")
@Data
public class Works {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String enName;
	private String cnName;
	private String imgUrl;
	private Date createTime;
	private Date updateTime;
	private Long createUser;
	
	
}
