package com.migu.online.vo;

/**
 * 首页推荐老师vo
 * @author fanyunlong
 *
 */
public class TeacherShowDetailVo extends TeacherVo {
	/**
	 * 标签列表
	 */
	private String tags;
	
	private String introduce;
	
	private String teachingAddress;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getTeachingAddress() {
		return teachingAddress;
	}

	public void setTeachingAddress(String teachingAddress) {
		this.teachingAddress = teachingAddress;
	}
}
