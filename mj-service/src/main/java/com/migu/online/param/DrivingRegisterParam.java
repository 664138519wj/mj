package com.migu.online.param;

/**
 * 驾校报名param
 * @author fanyunlong
 *
 */
public class DrivingRegisterParam {

	private String userName;
	
	private Long courseId;
		
	private int sex;
	
	private int firstApply;
	
	private int payType;
	
	private String telNo;
	
	private String idUp;
	
	private String idDown;
	
    private String idUpDir;
	
	private String idDownDir;
	
	private String price;
	
	public DrivingRegisterParam (String userName, String telNo, Long courseId, int sex, int firstApply, int payType, String idUp, String idDown) {
		this.userName = userName;
		this.telNo = telNo;
		this.courseId = courseId;
		this.sex = sex;
		this.firstApply = firstApply;
		this.payType = payType;
		this.idUp = idUp;
		this.idDown = idDown;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getFirstApply() {
		return firstApply;
	}

	public void setFirstApply(int firstApply) {
		this.firstApply = firstApply;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getIdUp() {
		return idUp;
	}

	public void setIdUp(String idUp) {
		this.idUp = idUp;
	}

	public String getIdDown() {
		return idDown;
	}

	public void setIdDown(String idDown) {
		this.idDown = idDown;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getIdUpDir() {
		return idUpDir;
	}

	public void setIdUpDir(String idUpDir) {
		this.idUpDir = idUpDir;
	}

	public String getIdDownDir() {
		return idDownDir;
	}

	public void setIdDownDir(String idDownDir) {
		this.idDownDir = idDownDir;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}	
	

}
