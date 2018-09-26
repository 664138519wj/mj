package com.migu.online.service.pay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2018030902342965";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC5mTNPK21seH7OqDnNvxsxo90kCGQqbK/lIMLlN1GkfFkm06W4N250nFKUsshRjD9QLBbfQWAcLvu2TdYdj85Z1Sfd6/ZijU9kMjUORWNhdmlExo1Mwl33tijAJjF2vuUEpa1ugnqrceAqAXAdwZ7V56dNuYDQxuFwBeAzefEXpoQegH4VJ3AmjjmGuJxBvv3XrE8QUVrquZR7BQoP2osW7Fh4UMyKaUIJy0zMWifiGSoEysfjvtCBVTPAuZ7PfpZSk4jItIz/K1EWBZ0I5nwTWKe7SDuwV0cO2gYJLy6iiKRkU24E5kk3DHHOZBUMXGJoVVg9QsgAY/pidnscX2kxAgMBAAECggEBAKVgGRQGQi5Q/8zsoPTvwC9vL3EX+QjEZ8bmdkMAE8NY+adbF2MaIVXOpNbSpyPir9ekMY0lF5qFUsySdqNIrDnncOqRgq019PP2uMfKlE8Xz5z0vpsS5d//dDgi0Bha6H5Hkk3R/bQed/t15IzfJcS78ZuUG2/shpvm4Gt90JGczYxNAcYIKaJVcwBBhTHoF9ZW5drYyOKtjNMpgM6hcfTvNKHVRZtX6E2g2hO95ZrEnl4UFClTLtLjsEGPaon19UAIyY7M7pHJEgPRx+zvEOANaNvAmBQg1shclI4NjtJRwNGKXICiFJtROi+QJv7jNzVQyzL4D0VwEMGWCuhm5/ECgYEA4jHB0SSugsKwoTo98Kk7xhwiXk27tjpVAc1gRagugHx4lJHTA3/FomcIiOAuDgjhsjCsM6TE0BfBVpwgEuAtYtPTKhFxHCnK7JrUPnAX3Kj4ZFvG23g/5Nes9dcQ4lIXCsuKk0tR+fwwo5sPm8cnw8C5lBvOL8CBxUtMbvRKNxcCgYEA0g4EEl5byOxHIopt/F3VVBye4H9BH6ZjES0HSA9+clmi1067MmjMQTfcEfzduqnGBrsM8jZaHN0FHIuxLxicagWJmYySPTJ9yGTw9oMaR73+GILnL/nWI5dX3P+N7MgaZmV6vhgMi2xIctHgD+vsQmGAcZMolhYMBRI5efznDvcCgYEA4FZnGy3dYtUDZ7SjEBhYr2Dh+VPxU4fBHbt6uztVe5t34CpM9ZB5DFxSC6cEJmlKp3c8oy/M/3JAD81ixjXO7dCXA4cu/ra4izf4P0DfebugN7EKpks6W5ZI9b5rAXxXXB6JfxLZ6CzmdK1qqp64Yp63sBxvv86XJn1K/lSNctsCgYEAsZsiPDnGV7PYTc9IQetA9jag62cNtGomN56gGoDnRF6f/nbWdz/+/GHz1M2+2Ra1lnlP7/ie5urGaKWAsjaYAlkeSFOATvhDPE6FI5okfWaa0zTTr1AKjNtpcjYuVXe4Qr4pM1P4P2bWNydX0X6ErHfud5ma5pRLo3oOWSPKZm8CgYBK13FktIO2LulOv5Ez/+vkk9NDJ3U+PUU6Tf+cG4ZYXxP872ONoBEEeNtkae4waT1fKFNgsS7XR4G6ilyhrgq3KvgEIIlcrkevyeDHyhkbsXYPmATqi2IETqf/6CDTdi936i5dvotzslDqHS3UsoH5nsKxHX1uWV2nJNzyoTA+pg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
//    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuZkzTyttbHh+zqg5zb8bMaPdJAhkKmyv5SDC5TdRpHxZJtOluDdudJxSlLLIUYw/UCwW30FgHC77tk3WHY/OWdUn3ev2Yo1PZDI1DkVjYXZpRMaNTMJd97YowCYxdr7lBKWtboJ6q3HgKgFwHcGe1eenTbmA0MbhcAXgM3nxF6aEHoB+FSdwJo45hricQb7916xPEFFa6rmUewUKD9qLFuxYeFDMimlCCctMzFon4hkqBMrH477QgVUzwLmez36WUpOIyLSM/ytRFgWdCOZ8E1inu0g7sFdHDtoGCS8uooikZFNuBOZJNwxxzmQVDFxiaFVYPULIAGP6YnZ7HF9pMQIDAQAB";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq/gQ/Ipe8hfONn/ZDSvVuFo/MwAK1kfksEpU+vkoypf7iw/my2k6l/Z9bVk6XSg9QR9nMOBCYm5ZBrj+ePDHYyYbviimbrepe98WnLcl1MuKEivmtmtdgvIUesvkSpVk9fOlPb3ilEFVsPlDcN5rH7etn2ErdoV6vSchoAT98APNAa1q/FUfHUiJkCH5tn8rubyUFHduhqpCnD5/uTSyzdrY6MmdV/7kdfUivCWaA+l8OqFAcWrKcOzQvYy3X5A2O4GQTGfp9zXLb2zd5AryZixqEjBTy54c2Stf3ciNtzAKRBvukJgyIEf4lyKUQfKzWXDEWCxTTHnt+lIQO/MstQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://www.myxueche.com/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.myxueche.com/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "/opt";



    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

