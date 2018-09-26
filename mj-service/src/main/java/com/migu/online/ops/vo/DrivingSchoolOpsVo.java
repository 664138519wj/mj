package com.migu.online.ops.vo;

import java.util.List;

import com.migu.online.model.DrivingSchool;

/**
 * 首页推荐老师vo
 * @author fanyunlong
 *
 */
public class DrivingSchoolOpsVo extends DrivingSchool {
	
	private String areaName;
	
	private List<String> shufflingImageList;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<String> getShufflingImageList() {
		return shufflingImageList;
	}

	public void setShufflingImageList(List<String> shufflingImageList) {
		this.shufflingImageList = shufflingImageList;
	}
	
}
