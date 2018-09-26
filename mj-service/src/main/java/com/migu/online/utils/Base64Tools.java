package com.migu.online.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class Base64Tools {
	static Base64 base64 = new Base64();

	/**
	 * 解密
	 * 
	 * @param pwd
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String decodeStr(String pwd) {
		if (StringUtils.isEmpty(pwd)) {
			return pwd;
		}
		byte[] debytes = base64.decodeBase64(new String(pwd).getBytes());
		return new String(debytes);
	}

	/**
	 * 加密
	 * 
	 * @param pwd
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String encodeStr(String pwd) {
		byte[] enbytes = base64.encodeBase64Chunked(pwd.getBytes());
		return new String(enbytes);
	}
	
	public static void main(String args[]) {
		Base64Tools tools = new Base64Tools();
		System.out.println(tools.decodeStr("7ou97om+7oq27omqIO6Mme6KgO6Lu+6Kke6LrO6Kke6Ml+6JuyDuiZPmlrDmiYsg7omUIO6Lo+6Jt+6Lne6Kpw=="));
	}
}
