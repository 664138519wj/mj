package com.migu.online.ops.vo;

import com.migu.online.model.UserCourse;

/**
 * 用户购买课程vo
 * @author fanyunlong
 *
 */
public class UserCourseOpsVo extends UserCourse {
	
	private String courseName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
