package com.migu.online.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/*平台网点图片*/
@Alias("platform_service_network_imageModel")
@Data
public class PlatformServiceNetworkImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fromId;
    private String imagePath;
    @Transient
    private PlatformNetwork from;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public PlatformNetwork getFrom() {
		return from;
	}
	public void setFrom(PlatformNetwork from) {
		this.from = from;
	}
    
}
