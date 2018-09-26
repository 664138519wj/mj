package com.migu.online.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

//驾校班级
@Alias("school_classModel")
@Data
public class SchoolClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// 所属驾校
	private Integer schoolId;
	
	/* A1	大型客车
	A2	牵引车
	A3	城市公共汽车
	B1	中型客车
	B2	大型货车
	C1	小型汽车
	C2	小型自动档汽车
	C3	低速载货汽车
	C4	三轮汽车
	C5	残疾人专用小型自动挡载客汽车
	D	三轮摩托车
	E	二轮摩托车
	F	轻便摩托车
	M	轮式自行机械车
	N	无轨电车
	P	有轨电车
	 */
	private String carModelCode;
	private String carModel;
	 
	//开班时间
	private Date startTime;
	 
	//价格
	private Double price;
	  
	/*所属驾校*/
	@Transient
	private Common school;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getCarModelCode() {
		return carModelCode;
	}

	public void setCarModelCode(String carModelCode) {
		this.carModelCode = carModelCode;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Common getSchool() {
		return school;
	}

	public void setSchool(Common school) {
		this.school = school;
	}
	
	
	
}
