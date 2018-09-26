package com.migu.online.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

import com.migu.online.common.IntentEnum;

import lombok.Data;

/*作品*/
@Alias("contact")
@Data
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String companyName;
	private String email;
	private String phone;
	private String intent;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Long createUser;
	
	@Transient
	private String intentStr;
	
	public void setIntentStr(String intent) {
		String intentStr = "";
		if (intent != null) {
			String[] ary = intent.split(",");
			for (String str:ary){
				switch (str) {
				case "1":
					intentStr += IntentEnum.consultations.desc+",";
					break;
				case "2":
					intentStr += IntentEnum.jobs.desc+",";
					break;
				case "3":
					intentStr += IntentEnum.cooperations.desc+",";
					break;
				case "4":
					intentStr += IntentEnum.publications.desc+",";
					break;
				default:
					break;
				}
			}
		}
		intentStr = intentStr.length() >1?intentStr.substring(0, intentStr.length()-1):intentStr;
		this.intentStr = intentStr;

	}
}
