package com.migu.online.controller.pay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.configuration.NeedLogin;
import com.migu.online.controller.BaseController;
import com.migu.online.model.CourseOffline;
import com.migu.online.model.CourseOnline;
import com.migu.online.model.DrivingSchoolLesson;
import com.migu.online.model.PracticeCard;
import com.migu.online.model.PrivateTutor;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.User;
import com.migu.online.model.UserAppointment;
import com.migu.online.model.UserCourse;
import com.migu.online.model.UserCourseVip;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.PracticeCardService;
import com.migu.online.service.PrivateTutorService;
import com.migu.online.service.UserAppointmentService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserCourseVipService;
import com.migu.online.service.UserNoticeService;
import com.migu.online.service.pay.AliPayService;
import com.migu.online.service.pay.WXPayService;
import com.migu.online.utils.OutOrderNoGenerate;
import com.migu.online.utils.qrcode.QRCodeUtil;

/**
 * Describe : web支付接口
 *
 * @author
 */
@Controller
@RequestMapping("/api/pay/web")
public class WebPayAction extends BaseController{

	private static final long serialVersionUID = -517003588129837631L;

	private Logger log = LoggerFactory.getLogger(WebPayAction.class);

	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private CourseOnlineService courseOnlineService;
	@Autowired
	private CourseOfflineService courseOfflineService;
	@Autowired
	private DrivingSchoolService drivingSchoolService;
	@Autowired
	private PracticeCardService practiceCardService;
	@Autowired
	private WXPayService wXPayService;
	@Autowired
	private AliPayService aliPayService;
	@Autowired
	private UserAppointmentService userAppointmentService;
	@Autowired
	private PrivateTutorService privateTutorService;
	@Autowired
	private UserCourseVipService userCourseVipService;
	@Autowired
	private UserNoticeService userNoticeService;
	@Value("${online.redirect.url}")
	private String ONLINE_REDIRECT_URL;
	@Value("${offline.redirect.url}")
	private String OFFLINE_REDIRECT_URL;
	@Value("${sign.redirect.url}")
	private String SIGN_REDIRECT_URL;
	@Value("${card.redirect.url}")
	private String CARD_REDIRECT_URL;
	@Value("${appointment.redirect.url}")
	private String APPOINTMENT_REDIRECT_URL;
	@Value("${tutor.redirect.url}")
	private String PRIVATE_TUTOR_REDIRECT_URL;
	@Value("${vip.redirect.url}")
	private String VIP_COURSE_REDIRECT_URL;

	/**
	 * 付费统一接口
	 * 对接web
	 *  生成订单
	 * @param request
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/order")
	public String order(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("courseId") Long courseId, 
			@RequestParam("courseType") Integer courseType, @RequestParam("payType") Integer payType) {
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		Long userId = user.getId();
		
		// 在线课程购买
    	UserCourse uCourse = userCourseService.selectPayCourseByUser(courseId, userId, courseType);
    	if (null != uCourse) {
    		// 已购买，返回
    		res.setStatus("0");
    		res.setCode("900001");
			res.setMsg("用户已购买成功，无法重复购买");
			modelMap.put("error", res);
			return "payment";
    	} 
    	int flag = 0;
    	BigDecimal price = new BigDecimal(0);
    	String outTradeNo = "";
    	String body = "";
    	Long publishCourseUserId = 0L;
	    if (UserCourseTypeEnum.online.code == courseType) {
	    	// 相关课程查询
	    	CourseOnline online = courseOnlineService.selectById(courseId);
	    	if (null == online) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	publishCourseUserId = online.getTeacherId();
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("O" + userId);
	    	price = online.getPrice();
	    	body = "驾考中心-在线课程支付-" + online.getTitle();
	    } else if (UserCourseTypeEnum.offline.code == courseType) {
	    	// 相关课程查询
	    	CourseOffline offline = courseOfflineService.selectById(courseId);
	    	if (null == offline) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	publishCourseUserId = offline.getPlatId();
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("F" + userId);
	    	price = offline.getPrice();
	    	body = "驾考中心-线下课程支付-" + offline.getTitle();
	    } else if (UserCourseTypeEnum.lesson.code == courseType) {
	    	// 相关课程查询
	    	DrivingSchoolLesson driveLesson = drivingSchoolService.selectLessonById(courseId);
	    	if (null == driveLesson) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("L" + userId);
	    	price = driveLesson.getPrice();
	    	body = "驾考中心-线下课程支付-" + driveLesson.getName();
	    } else if (UserCourseTypeEnum.practice.code == courseType) {
	    	// 相关课程查询
	    	PracticeCard card = practiceCardService.getOne(courseId);
	    	if (null == card) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("P" + userId);
	    	price = card.getPrice();
	    	body = "驾考中心-线下课程支付-" + card.getDes();
	    } else if (UserCourseTypeEnum.appointment.code == courseType) {
	    	// 相关课程查询
	    	UserAppointment data = userAppointmentService.queryById(courseId);
	    	if (null == data) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("A" + userId);
	    	price = data.getPrice();
	    	body = "驾考中心-预约课程支付";
	    } else if (UserCourseTypeEnum.tutor.code == courseType) {
	    	// 相关课程查询
	    	PrivateTutor data = privateTutorService.selectById(courseId);
	    	if (null == data) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	publishCourseUserId = data.getTeacherId();
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("T" + userId);
	    	price = data.getPrice();
	    	body = "驾考中心-私教课程支付";
	    } else if (UserCourseTypeEnum.vip.code == courseType) {
	    	// 相关课程查询
	    	UserCourseVip data = userCourseVipService.selectById(courseId);
	    	if (null == data) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				modelMap.put("error", res);
				return "payment";
	    	}
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("V" + userId);
	    	price = data.getPrice();
	    	body = "驾考中心-VIP课程支付";
	    } else {
	    	// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			modelMap.put("error", res);
			return "payment";
	    }
	    // 订单生成
		uCourse = new UserCourse();
    	uCourse.setCourseId(courseId);
    	uCourse.setCourseType(courseType);
    	uCourse.setOutTradeNo(outTradeNo);
    	uCourse.setPrice(price);
    	String ip = CourseOnlineService.getIpAddress(request);
    	if (payType == 1) {
        	uCourse.setTradeType("wx_web");
    	} else if (payType == 2) {
        	uCourse.setTradeType("ali_web");
    	}
    	uCourse.setUserId(userId);
    	uCourse.setUserName(user.getMobile());
    	uCourse.setCreateTime(new Date());
    	uCourse.setUpdateTime(new Date());
    	uCourse.setPayStatus(0);
    	uCourse.setRefundStatus(0);
    	uCourse.setIsDelete(0);
    	uCourse.setPublishCourseUserId(publishCourseUserId);
    	flag = userCourseService.createOrUpdate(uCourse);
    	Map<String, String> result = new HashMap<>();
	    // 订单生成成功，调用支付接口
	    if (payType == 1 && flag == 1) {
	    	// 微信支付
	    	// TODO 增加订单扫码锁 避免用户重复支付
	    	String priceStr = price.multiply(new BigDecimal(100)).toString();
	    	if (priceStr.contains(".")) {
	    		priceStr = priceStr.substring(0, priceStr.indexOf("."));
	    	}
	    	try {
	    		result = wXPayService.doWebUnifiedOrder(outTradeNo, priceStr, ip, courseId + "", body);
		    	// 生成二维码
		    	String codeUrl = result.get("code_url");
		    	if (StringUtils.isNotEmpty(codeUrl)) {
		    		
					QRCodeUtil.encode(codeUrl, "/opt/upload/logo_s.png", "/opt/upload/image/qr/" + outTradeNo + "w.png", true);
		    	}
		    	result.put("out_trade_no", outTradeNo);
		    	log.info("微信支付订单生成成功" + result.toString());
	    	}catch (Exception e) {
				// TODO: handle exception
	    		log.error(e.toString());
			}	    	
	    } else if (payType == 2 && flag == 1) {
	    	// 支付宝支付
	    	String priceStr = price.toString();
	    	try {
		    	response.setContentType("text/html;charset=utf-8");
		    	//直接将完整的表单html输出到页面
		    	response.getWriter().write(aliPayService.webPay(outTradeNo, priceStr, body, body));
		    	response.getWriter().flush();
	    	}catch (Exception e) {
				// TODO: handle exception
	    		log.error(e.toString());
			}
	    }
	    modelMap.put("title", body);
	    modelMap.put("price", price);
	    modelMap.put("courseType", courseType);
	    modelMap.put("payType", payType);
	    modelMap.put("outTradeNo", outTradeNo);
		return "payment";
	}
	
	/**
	 * 驾校课程报名付费接口
	 * 对接web
	 *  生成订单
	 * @param request
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/dsOrder")
	public String order(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("courseId") Long courseId, @RequestParam("payType") Integer payType) {
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		Long userId = user.getId();
		// 在线课程购买
    	UserCourse uCourse = userCourseService.selectOneByUser(courseId, userId);
    	if (null == uCourse || uCourse.getCourseType() != UserCourseTypeEnum.lesson.code) {
    		// 用户未报名驾校，
    		res.setStatus("0");
    		res.setCode("900003");
			res.setMsg("用户未报名驾校，无法付款");
			modelMap.put("error", res);
			return "payment";
    	}  else if (uCourse.getPayStatus() == 2) {
    		// 用户未报名驾校，
    		res.setStatus("0");
    		res.setCode("900001");
			res.setMsg("用户已购买成功，无法重复购买");
			modelMap.put("error", res);
			return "payment";
    	}
    	int flag = 0;
    	BigDecimal price = new BigDecimal(0);
    	String outTradeNo = "";
    	String body = "";	    
    	// 相关课程查询
    	DrivingSchoolLesson driveLesson = drivingSchoolService.selectLessonById(uCourse.getCourseId());
    	if (null == driveLesson) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			modelMap.put("error", res);
			return "payment";
    	}
    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
    	outTradeNo = generate.makeOrderNum("L" + userId);
    	price = uCourse.getPrice();
    	body = "驾考中心-线下课程支付-" + driveLesson.getName();  
	    // 更新订单
    	uCourse.setOutTradeNo(outTradeNo);
    	String ip = CourseOnlineService.getIpAddress(request);
    	uCourse.setTradeType(ip);
    	uCourse.setUpdateTime(new Date());
    	uCourse.setPayStatus(0);
    	uCourse.setRefundStatus(0);
    	uCourse.setIsDelete(0);
    	flag = userCourseService.createOrUpdate(uCourse);
    	Map<String, String> result = new HashMap<>();
	    // 订单生成成功，调用支付接口
	    if (payType == 1 && flag == 1) {
	    	// 微信支付
	    	// TODO 增加订单扫码锁 避免用户重复支付
	    	String priceStr = price.multiply(new BigDecimal(100)).toString();
	    	if (priceStr.contains(".")) {
	    		priceStr = priceStr.substring(0, priceStr.indexOf("."));
	    	}
	    	try {
	    		result = wXPayService.doWebUnifiedOrder(outTradeNo, priceStr, ip, courseId + "", body);
		    	// 生成二维码
		    	String codeUrl = result.get("code_url");
		    	if (StringUtils.isNotEmpty(codeUrl)) {
		    		
					QRCodeUtil.encode(codeUrl, "/opt/upload/logo_s.png", "/opt/upload/image/qr/" + outTradeNo + "w.png", true);
		    	}
		    	result.put("out_trade_no", outTradeNo);
		    	log.info("微信支付订单生成成功" + result.toString());
	    	}catch (Exception e) {
				// TODO: handle exception
	    		log.error(e.toString());
			}	    	
	    } else if (payType == 2 && flag == 1) {
	    	// 支付宝支付
	    	String priceStr = price.toString();
	    	try {
		    	response.setContentType("text/html;charset=utf-8");
		    	//直接将完整的表单html输出到页面
		    	response.getWriter().write(aliPayService.webPay(outTradeNo, priceStr, body, body));
		    	response.getWriter().flush();
	    	}catch (Exception e) {
				// TODO: handle exception
	    		log.error(e.toString());
			}
	    }
	    modelMap.put("title", body);
	    modelMap.put("price", price);
	    modelMap.put("courseType", UserCourseTypeEnum.lesson.code);
	    modelMap.put("payType", payType);
	    modelMap.put("outTradeNo", outTradeNo);
		return "payment";
	}
	

	/**
	 * 是否知否成功查询
	 * 对接app
	 * @param request
	 * @return
	 */
	@RequestMapping("/isPaySuccess")
	@ResponseBody
	public ResposeModel isPaySuccess(@RequestParam("outTradeNo") String outTradeNo, @RequestParam("payType") Integer payType) {
		ResposeModel res = new ResposeModel();
		UserCourse course = userCourseService.selectCourseByOutOrderNo(outTradeNo);
		if (null != course && course.getPayStatus() == 2) {
			res.setData("SUCCESS");
			res.setMsg("支付成功");
			return res;
		} else if (course == null) {
			res.setStatus("0");
			res.setMsg("改订单不存在");
			return res;
		} else {
			// 查询支付接口来判断是否成功
			if (payType == 1) {		
				Map<String, String> result = wXPayService.doOrderQuery(outTradeNo);
				if (null != result) {
					res.setData(result.get("trade_state"));
				}				
			}
		}	    
		return res;
	}
	
	/**
	 * 是否知否成功查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/ali/returnUrl")
	public String aliReturnUrl(HttpServletRequest request, ModelMap modelMap) {
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
//			try {
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			params.put(name, valueStr);
		}
		boolean signVerified = aliPayService.isSignVerified(params);
		String redirectUrl = "http://www.myxueche.com";
		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = params.get("out_trade_no");		
			//支付宝交易号
			String trade_no = params.get("trade_no");			
			//付款金额
			String total_amount = params.get("total_amount");
			
			System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);		
			UserCourse course = userCourseService.selectCourseByOutOrderNo(out_trade_no);
			if (null != course && course.getPayStatus() == 2) {
				// 支付成功
				modelMap.put("pay_flag", "success");
				switch (course.getCourseType()) {
				case 1:
					redirectUrl = ONLINE_REDIRECT_URL + course.getCourseId();
					break;
				case 2:
					redirectUrl = OFFLINE_REDIRECT_URL + course.getCourseId();
					break;
				case 3:
					redirectUrl = SIGN_REDIRECT_URL;

					break;
				case 4:
					redirectUrl = CARD_REDIRECT_URL;
					break;
				case 5:
					redirectUrl = APPOINTMENT_REDIRECT_URL;
					break;
				case 6:
					redirectUrl = PRIVATE_TUTOR_REDIRECT_URL;
					break;
				case 7:
					redirectUrl = VIP_COURSE_REDIRECT_URL;
					break;
				}
//				// dealMessage
//				userNoticeService.dealMessage(course);
				return "redirect:" + redirectUrl;
			} else {
				modelMap.put("pay_flag", "false");
			}
		} else {
			log.error("验签失败");
			modelMap.put("pay_flag", "false");
		}
		modelMap.put("redirectUrl", redirectUrl);
		return "pay_result";
	}
	
	/**
	 * 驾校课程报名付费接口
	 * 对接web
	 *  生成订单
	 * @param request
	 * @return
	 */
	@NeedLogin
	@RequestMapping("/payChoose")
	public String payChoose(ModelMap modelMap, HttpServletRequest request, 
			@RequestParam("courseId") Long courseId, @RequestParam("courseType") Integer courseType,
			@RequestParam("price") String price) {
		String body = UserCourseTypeEnum.getEnumByCode(courseType).desc;
	    modelMap.put("body", body);
	    modelMap.put("courseType", courseType);
	    modelMap.put("courseId", courseId);
	    modelMap.put("price", price);
		return "pay_choose";
	}
		
}
