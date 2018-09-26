package com.migu.online.common;

/**
 * 在线课程列表类型
 * @author fanyunlong
 *
 */
public enum OnlineTypeEnum {

    kemu1(1, "科目一"),
    kemu2(2, "科目二"),
    kemu3(3, "科目三"),
    kemu4(4, "科目四");
    
    public final int code;
    public final String desc;

    OnlineTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
	public static OnlineTypeEnum getEnumByCode(int code) {
		for (OnlineTypeEnum sexEnum : OnlineTypeEnum.values()) {
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
