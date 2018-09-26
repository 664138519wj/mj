package com.migu.online.vo;

//驾考政策
public class DrivingPolicyWebVo {

    private Long id;
    private String title;
    private String content;
    private String imagePath;
    private String updateTime;
    private String upTitle;
    private Long upId;
    private String downTitle;
    private Long downId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpTitle() {
		return upTitle;
	}

	public void setUpTitle(String upTitle) {
		this.upTitle = upTitle;
	}

	public Long getUpId() {
		return upId;
	}

	public void setUpId(Long upId) {
		this.upId = upId;
	}

	public String getDownTitle() {
		return downTitle;
	}

	public void setDownTitle(String downTitle) {
		this.downTitle = downTitle;
	}

	public Long getDownId() {
		return downId;
	}

	public void setDownId(Long downId) {
		this.downId = downId;
	}	
		
}
