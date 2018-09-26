package com.migu.online.common;

/**
 * 来源
 * @author wangjin
 *
 */
public enum SourceEnum {

	local("1", "本地"),
	field("2", "外地");
    
    public final String code;
    public final String desc;

    SourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public static SourceEnum getEnumByCode(String code) {
		for (SourceEnum sexEnum : SourceEnum.values()) {
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
