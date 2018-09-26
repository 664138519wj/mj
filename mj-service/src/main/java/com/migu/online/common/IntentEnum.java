package com.migu.online.common;

/**
 * 证件类别
 * @author wangjin
 *
 */
public enum IntentEnum {

	consultations("1", "设计咨询"),
	jobs("2", "招聘"),
	cooperations("3", "品牌合作"),
	publications("4", "媒体发表");
    
    public final String code;
    public final String desc;

    IntentEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public static IntentEnum getEnumByCode(String code) {
		for (IntentEnum sexEnum : IntentEnum.values()) {
			if (code.equals(sexEnum.getCode())) {
				return sexEnum;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
