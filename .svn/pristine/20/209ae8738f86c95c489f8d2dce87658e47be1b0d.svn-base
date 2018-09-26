package com.migu.online.vo;

//交通安全法律法规
public class TrafficLawVo {
 
    private Long id;
    private String title;
    private String content;
    private String imagePath;
    private String topicStr;
    
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
	public String getTopicStr() {
		return topicStr;
	}
	public void setTopicStr(String topicStr) {
		this.topicStr = topicStr;
	}
	
	/**
	 * 章节转化
	 * @param topic
	 * @return
	 */
	public String conventTopi(int topic) {
		if (topic <= 0) {
			return "";
		}
		String topicStr = toChinese(topic + "");
		
		return "第" + topicStr + "章";			
	}

	/**
	 * 数字中文转化
	 * 
	 * @param string
	 * @return
	 */
	private String toChinese(String string) {
		String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

		String result = "";

		int n = string.length();
		for (int i = 0; i < n; i++) {

			int num = string.charAt(i) - '0';

			if (i != n - 1 && num != 0) {
				result += s1[num] + s2[n - 2 - i];
			} else {
				result += s1[num];
			}
		}

		return result;

	}
	
      
}
