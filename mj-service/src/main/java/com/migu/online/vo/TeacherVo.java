package com.migu.online.vo;

/**
 * 首页推荐老师vo
 * 
 * @author fanyunlong
 *
 */
public class TeacherVo {

	private Long id;
	private String name;
	private Integer teachingAge;
	private String avator;
	private String schoolName; // 驾校名字

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeachingAge() {
		return teachingAge;
	}

	public void setTeachingAge(Integer teachingAge) {
		this.teachingAge = teachingAge;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
