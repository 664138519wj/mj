package com.migu.online.common;

/**
 * 性别
 * @author wangjin
 *
 */
public enum SexEnum {

	male(1, "男"),
	female(0, "女");
    
    public final int code;
    public final String desc;

    SexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public static SexEnum getEnumByCode(int code) {
		for (SexEnum sexEnum : SexEnum.values()) {
			if (code == sexEnum.getCode()) {
				return sexEnum;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
