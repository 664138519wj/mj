package com.migu.online.ops.vo;

import com.migu.online.model.AppointmentManage;

public class AppointmentOpsVo extends AppointmentManage{
	
	
	private static final long serialVersionUID = -8785132056834182893L;

	private String examPlaceName;
	
	private String examPlaceAddress;

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public String getExamPlaceAddress() {
		return examPlaceAddress;
	}

	public void setExamPlaceAddress(String examPlaceAddress) {
		this.examPlaceAddress = examPlaceAddress;
	}
	
}
