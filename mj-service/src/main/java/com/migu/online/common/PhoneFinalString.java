package com.migu.online.common;

/**
 * Description: Created by fanyunlong 
 * power by migu
 */
public class PhoneFinalString {
	/** 座机电话格式验证 **/
	public static final String PHONE_CALL_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$";
	/**
	 * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173 新增199，1410，17400~17405
	 **/
	public static final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019]|9[9]|4[1]|7[4])\\d{8}$)|(^1700\\d{7}$)";
	/**
	 * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709,
	 * 新增166，146
	 **/
	public static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56]|6[6]|4[6])\\d{8}$)|(^170[7-9]\\d{7}$)";
	/**
	 * 中国移动号码格式验证
	 * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184
	 * ,187,188,147,178,1705 新增198，148，1440
	 **/
	public static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7-8]|5[0-27-9]|7[8]|8[2-478]|9[8]|)\\d{8}$)|(^1705\\d{7}$)|(^1440\\d{7}$)";
	/**
	 * 仅手机号格式校验
	 */
	public static final String PHONE_PATTERN = new StringBuilder(300).append(CHINA_MOBILE_PATTERN).append("|")
			.append(CHINA_TELECOM_PATTERN).append("|").append(CHINA_UNICOM_PATTERN).toString();
	/**
	 * 手机和座机号格式校验
	 */
	public static final String PHONE_TEL_PATTERN = new StringBuilder(350).append(PHONE_PATTERN).append("|").append("(")
			.append(PHONE_CALL_PATTERN).append(")").toString();
	/**
	 * 匹配多个号码以,、或空格隔开的格式，如 17750581369
	 * 13306061248、(596)3370653,17750581369,13306061248 (0596)3370653
	 */
	public static final String MULTI_PHONE_TEL_PATTERN = "^(?:(?:(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)[,\\s、])+)?(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)$";

}
