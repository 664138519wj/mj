package com.migu.online.service.pay;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

@Service
public class AliPayService {

	private Logger log = LoggerFactory.getLogger(AliPayService.class);
	private static AlipayClient alipayClient;
	@Value("${ali.notify.url}")
	private String ali_notify_url;
	@Value("${ali.return.url}")
	private String ali_return_url;

	public AliPayService() {
		alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
	}

	/**
	 * 网页支付接口
	 */
	public String webPay(String out_trade_no, String total_amount, String subject, String body) {
		log.info("网页版阿里支付接口调用start");
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(ali_return_url);
		alipayRequest.setNotifyUrl(ali_notify_url);

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\"," + "\"notify_url\":\""
				+ ali_notify_url + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求
		String result = "";
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 订单查询
	 */
	public String doOrderQuery(String out_trade_no, String trade_no) {
		// 设置请求参数
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		alipayRequest
				.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");
		// 请求
		String result = "";
		try {
			AlipayResponse response = alipayClient.execute(alipayRequest);
			// 输出
			result = response.getMsg();
			System.out.println(response.getBody());
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 支付宝回调
	 * @param params
	 * @return
	 */
	public boolean isSignVerified(Map<String,String> params) {
		boolean signVerified;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); 
			return signVerified;
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	public static void main(String args[]) {
		AliPayService service = new AliPayService();
		service.webPay("1111", "0.01", "test", "test");
		
	}
	
	/**
	 * app 端支付
	 * @return
	 */
    public String appPay(String outTradeNo, String totalAmount, String body) {
    	//实例化客户端
    	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.app_id, 
    			AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    	//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
    	AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
    	//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
    	AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
    	model.setBody(body);
    	model.setSubject(body);
    	model.setOutTradeNo(outTradeNo);
    	model.setTimeoutExpress("30m");
    	model.setTotalAmount(totalAmount);
    	model.setProductCode("QUICK_MSECURITY_PAY");
    	request.setBizModel(model);
    	request.setNotifyUrl(ali_notify_url);
    	try {
    	        //这里和普通的接口调用不同，使用的是sdkExecute
    	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
    	        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
    	        return response.getBody();
    	    } catch (AlipayApiException e) {
    	        e.printStackTrace();
    	}
    	return null;
    }
    
	/**
	 * 退款
	 * @return
	 */
    public String refund(String out_trade_no, String refund_amount, String refund_reason) {
    	//获得初始化的AlipayClient
    	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, 
    			"json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);   	
    	//设置请求参数
    	AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();   	
    	alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
    			+ "\"trade_no\":\""+ "" +"\"," 
    			+ "\"refund_amount\":\""+ refund_amount +"\"," 
    			+ "\"refund_reason\":\""+ refund_reason +"\"," 
    			+ "\"out_request_no\":\""+ "" +"\"}");   	
    	//请求
    	String result = "";
		try {
			AlipayTradeRefundResponse response = alipayClient.execute(alipayRequest);
			result = response.getMsg();
	    	System.out.println(response.getBody());
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			e.printStackTrace();
		}  	
    	//输出
    	return result;
    }

}
