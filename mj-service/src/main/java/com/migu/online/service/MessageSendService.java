package com.migu.online.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.migu.online.common.Constants;
import com.migu.online.common.PhoneFinalString;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.SendMsgRecord;

@Service
public class MessageSendService {
	
	private Logger log = LoggerFactory.getLogger(MessageSendService.class);

	@Autowired
	private RedisService redisService;
	@Autowired
	private SendMsgRecordService sendMsgRecordService;

	// 初始化ascClient需要的几个参数
	final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
	final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）

	@Value("${accessKeyId}")
	private String accessKeyId="LTAIWvS9DxXNZYaW";
	@Value("${accessKeySecret}")
	private String accessKeySecret="zcrKMYSO8sPp5RfUZRQKRXEK48q2JM";
	
	// 短信模板
	public static String MSG_TEMPLATE_VIP_STUDENT="SMS_135028985"; // VIP 课程学生短信 您购买的vip课程${course}，已成功分配教练${tName}，电话${tel}，上课时间${beginTime}
	public static String MSG_TEMPLATE_VIP_TEACHER="SMS_135043908"; // VIP 课程老师短信 后台分配了${kemu}的vip学员${name}，联系电话${tel}，上课时间${beginTime} 
	
	/**
	 * 验证码发送
	 * @param phone
	 * @return
	 */
	public ResposeModel sendCode(String phone) {
		ResposeModel res = new ResposeModel();
		try {
			// 缓存判断发送次数，一个号码一天只能发送5次
			String key = Constants.SMS_PHONE_TIMES + phone;
			Integer sendCount = (Integer)redisService.get(key);
			if (null != sendCount && sendCount > Constants.SEND_LIMIT_TIME) {
				res.setStatus("0");
				res.setMsg("已超过每日发送次数");
				return res;
			} else {
				int count = sendCount == null ? 0 : sendCount;
				redisService.set(key, count + 1, 60 * 60 * 24);
			}
		    // 生成验证码
			String code = (Integer.toString((int)((Math.random() * 9 + 1) * 1000)));
			// 缓存中存放验证码
			redisService.setString(Constants.SMS_PHONE_CODE + phone, code, 60 * 2);
			sendSms(phone, code);
			res.setStatus("1");
			res.setMsg("发送成功");
			return res;			
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("短信发送出错");
		}
		return res;
	}

	/**
	 * 阿里云短信接口 @throws
	 */
	public boolean sendSms(String phoneNum, String validateCode) {
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phoneNum);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("我的学车网");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_122560153");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"code\":\"" + validateCode + "\"}");
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				// 请求成功
				return true;
			}
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 根据模板发送短信
	 */
	public boolean sendSmsByTemplate(Map<String, String> param, String template) {
		// TODO 缺少重试机制和并发处理
		try {
			Thread.sleep(100);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		String phoneNum = param.get("phoneNum");
		if (StringUtils.isEmpty(phoneNum)) {
			return false;
		}
		String templaetParam = "";
	    if (template.equals(MSG_TEMPLATE_VIP_STUDENT)) {
	    	// 您购买的vip课程${course}，已成功分配教练${tName}，电话${tel}，上课时间${beginTime}
	    	templaetParam = "{\"course\":\"" + param.get("course") + "\",\"tName\":\"" + param.get("tName")
	    	+ "\",\"tel\":\"" + param.get("tel") + "\",\"beginTime\":\"" + param.get("beginTime") + "\"}";
	    } else if (template.equals(MSG_TEMPLATE_VIP_TEACHER)) {
	    	// 后台分配了${kemu}的vip学员${name}，联系电话${tel}，上课时间${beginTime} 
	    	templaetParam = "{\"kemu\":\"" + param.get("kemu") + "\",\"name\":\"" + param.get("name")
	    	+ "\",\"tel\":\"" + param.get("tel") + "\",\"beginTime\":\"" + param.get("beginTime") + "\"}";
	    } else {
	    	return false;
	    }
		request.setPhoneNumbers(phoneNum);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("我的学车网");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(template);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam(templaetParam);
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				// 请求成功
				return true;
			}
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 发送短信并跟新记录
	 */
	public void sendSmsByTempAndUpdRecord(SendMsgRecord record) {
		if (null == record) {
			return;
		}	
		System.out.println("message send begin:" + JSON.toJSONString(record));
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		String phoneNum = record.getMobile();
		if (StringUtils.isEmpty(phoneNum)) {
			return;
		}
		request.setPhoneNumbers(phoneNum);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("我的学车网");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(record.getTemplate());
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam(record.getContent());
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				// 请求成功 update record				
				record.setIsDelete(1);
				sendMsgRecordService.update(record);
				// 删除记录
				log.info("send msg success mobile=" + record.getMobile());	
			}  else {
				record.setStatus(record.getStatus() + 1);
				sendMsgRecordService.update(record);
			}
		} catch (Exception e) {
			record.setStatus(record.getStatus() + 1);
			sendMsgRecordService.update(record);
			log.error("send msg error mobile=" + record.getMobile());	
		} 
	}

	/**
	 * 匹配函数
	 * 
	 * @param regex
	 * @param input
	 * @return
	 */
	private static boolean match(String regex, String input) {
		return Pattern.matches(regex, input);
	}

	public boolean isPhone(String input) {
		return match(PhoneFinalString.PHONE_PATTERN, input);
	}

	public static void main(String args[]) {
		MessageSendService service = new MessageSendService();
//		service.sendSms("15900479490", "11111");
//		Map<String, String> param = new HashMap<>();
//		param.put("phoneNum", "15900479490");
//		param.put("course", "科目一");
//		param.put("tName", "张三");
//		param.put("tel", "13391000000");
//		param.put("beginTime", "20180528");
//		service.sendSmsByTemplate(param, MessageSendService.MSG_TEMPLATE_VIP_STUDENT);	
		Map<String, String> param2 = new HashMap<>();
		param2.put("phoneNum", "15900479490");
		param2.put("kemu", "科目一");
		param2.put("name", "张三");
		param2.put("tel", "13391000000");
		param2.put("beginTime", "20180528");
		service.sendSmsByTemplate(param2, MessageSendService.MSG_TEMPLATE_VIP_TEACHER);

	}

}
