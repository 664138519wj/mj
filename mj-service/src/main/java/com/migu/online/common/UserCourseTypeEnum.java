package com.migu.online.common;

/**
 * 在线课程列表类型
 * @author fanyunlong
 *
 */
public enum UserCourseTypeEnum {

    online(1, "在线视频课程"),
    offline(2, "线下网点课程"),
    lesson(3, "驾校课程"),
    practice(4, "练习卡"),
    appointment(5, "预约"),
    tutor(6, "私教"),
    vip(7, "vip课程");
    
    public final int code;
    public final String desc;

    UserCourseTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public static UserCourseTypeEnum getEnumByCode(int code) {
		for (UserCourseTypeEnum sexEnum : UserCourseTypeEnum.values()) {
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
