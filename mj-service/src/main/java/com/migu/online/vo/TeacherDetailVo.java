package com.migu.online.vo;

import com.migu.online.model.Teacher;

/**
 * 首页推荐老师vo
 * @author fanyunlong
 *
 */
public class TeacherDetailVo extends Teacher {
	/**
	 * 标签列表
	 */
	private String tags;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
