package com.migu.online.ops.vo;

import java.util.List;

import com.migu.online.model.PlatformNetwork;

/**
 * 首页推荐老师vo
 * @author fanyunlong
 *
 */
public class PlatformNetworkOpsVo extends PlatformNetwork {
	
	private String schoolName;
	
	private List<String> shufflingImageList;
	
	private String areaName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<String> getShufflingImageList() {
		return shufflingImageList;
	}

	public void setShufflingImageList(List<String> shufflingImageList) {
		this.shufflingImageList = shufflingImageList;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
