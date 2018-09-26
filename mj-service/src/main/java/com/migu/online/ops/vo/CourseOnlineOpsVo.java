package com.migu.online.ops.vo;

import com.migu.online.model.CourseOnline;

//线下课程首页显示vo
public class CourseOnlineOpsVo extends CourseOnline{

	private String teacherName;
	
    private String kemu;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getKemu() {
		return kemu;
	}

	public void setKemu(String kemu) {
		this.kemu = kemu;
	}
	
}
