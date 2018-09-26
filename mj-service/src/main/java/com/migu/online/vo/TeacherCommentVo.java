package com.migu.online.vo;

import com.migu.online.model.TeacherComment;

//线上课程 首页vo
public class TeacherCommentVo extends TeacherComment{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8558066444514454693L;

	private String userName;
	
	private String userImage;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
}
