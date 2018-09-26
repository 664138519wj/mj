package com.migu.online.utils;

public final class NumberUtil {

	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public String autoGenericCode(String code, int num) {
        String result = "";    
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);
        return result;
    }
    
    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param str
     * @param strLength
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
         while (strLen < strLength) {
               sb = new StringBuffer();
               sb.append("0").append(str);// 左补0
               str = sb.toString();
               strLen = str.length();
         }
        return str;
    }
}
