package com.migu.online.controller.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.common.UserCourseTypeEnum;
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
import com.migu.online.model.VipCourse;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.PracticeCardService;
import com.migu.online.service.PrivateTutorService;
import com.migu.online.service.UserAppointmentService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserCourseVipService;
import com.migu.online.service.UserNoticeService;
import com.migu.online.service.VipCourseService;
import com.migu.online.service.pay.AliPayService;
import com.migu.online.service.pay.WXPayService;
import com.migu.online.utils.DateUtil;
import com.migu.online.utils.OutOrderNoGenerate;

/**
 * Describe : 支付接口
 *
 * @author
 */
@Controller
@RequestMapping("/api/pay")
public class PayAction extends BaseController{

	private static final long serialVersionUID = -517003588129837631L;

	private Logger log = LoggerFactory.getLogger(PayAction.class);

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
	private VipCourseService vipCourseService;
	@Autowired
	private UserCourseVipService userCourseVipService;
	@Autowired
	private UserNoticeService userNoticeService;
	@Autowired
	private PrivateTutorService privateTutorService;

	/**
	 * 付费统一接口
	 * 对接app
	 *  生成订单
	 * @param request
	 * @return
	 */
	@RequestMapping("/order")
	@ResponseBody
	public ResposeModel order(@RequestParam("courseId") Long courseId, 
			@RequestParam("courseType") Integer courseType, @RequestParam("payType") Integer payType, @RequestParam("ip") String ip) {
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}	
		Long userId = user.getId();
		// 在线课程购买
    	UserCourse uCourse = userCourseService.selectPayCourseByUser(courseId, userId, courseType);
    	if (null != uCourse) {
    		// 已购买，返回
    		res.setStatus("0");
    		res.setCode("900001");
			res.setMsg("用户已购买成功，无法重复购买");
			return res;
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
				return res;
	    	}
	    	publishCourseUserId = online.getTeacherId();
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("O" + userId);
	    	price = online.getPrice();
	    	body = "驾考中心-在线课程支付";
	    } else if (UserCourseTypeEnum.offline.code == courseType) {
	    	// 相关课程查询
	    	CourseOffline offline = courseOfflineService.selectById(courseId);
	    	if (null == offline) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				return res;
	    	}
	    	publishCourseUserId = offline.getPlatId();
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("F" + userId);
	    	price = offline.getPrice();
	    	body = "驾考中心-线下课程支付";
	    } else if (UserCourseTypeEnum.lesson.code == courseType) {
	    	// 相关课程查询
	    	return this.dealLessonPay(courseId, userId, payType, ip, res);
	    } else if (UserCourseTypeEnum.practice.code == courseType) {
	    	// 相关课程查询
	    	PracticeCard card = practiceCardService.getOne(courseId);
	    	if (null == card) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				return res;
	    	}
	    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
	    	outTradeNo = generate.makeOrderNum("P" + userId);
	    	price = card.getPrice();
	    	body = "驾考中心-线下课程支付";
	    } else if (UserCourseTypeEnum.appointment.code == courseType) {
	    	// 相关课程查询
	    	UserAppointment data = userAppointmentService.queryById(courseId);
	    	if (null == data) {
	    		// 课程不存在
	    		res.setStatus("0");
	    		res.setCode("900002");
				res.setMsg("相关课程不存在，或已下架");
				return res;
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
				return res;
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
				return res;
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
			return res;
	    }
	    // 订单防刷拦截
	    // 订单生成
		uCourse = new UserCourse();
    	uCourse.setCourseId(courseId);
    	uCourse.setCourseType(courseType);
    	uCourse.setOutTradeNo(outTradeNo);
    	uCourse.setPrice(price);
    	if (payType == 1) {
        	uCourse.setTradeType("wx_app");
    	} else if (payType == 2) {
        	uCourse.setTradeType("ali_app");
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
	    	result = wXPayService.doAppUnifiedOrder(outTradeNo, priceStr, ip, courseId + "", body);	    	
	    	result.put("out_trade_no", outTradeNo);
	    	res.setData(result);
	    } else if (payType == 2 && flag == 1) {
	    	// ali支付
	    	String priceStr = price.toString();
	    	res.setData(aliPayService.appPay(outTradeNo, priceStr, body));	  
	    	res.setMsg(outTradeNo);
	    }
	    return res;	   
	}
	
	/**
	 * 驾校报考支付特殊处理
	 * @return
	 */
	private ResposeModel dealLessonPay(Long courseId, Long userId, int payType, String ip, ResposeModel res) {
		// 特殊处理
    	UserCourse uCourse = userCourseService.selectOneByUser(courseId, userId);
    	if (null == uCourse || uCourse.getCourseType() != UserCourseTypeEnum.lesson.code) {
    		// 用户未报名驾校，
    		res.setStatus("0");
    		res.setCode("900003");
			res.setMsg("用户未报名驾校，无法付款");
			return res;
    	} 
    	DrivingSchoolLesson driveLesson = drivingSchoolService.selectLessonById(uCourse.getCourseId());
    	if (null == driveLesson) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			return res;
    	}
    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
    	String outTradeNo = generate.makeOrderNum("L" + userId);
    	BigDecimal price = driveLesson.getPrice();
    	String body = "驾考中心-线下课程支付";
    	uCourse.setOutTradeNo(outTradeNo);
    	uCourse.setPrice(price);
    	if (payType == 1) {
        	uCourse.setTradeType("wx_app");
    	} else if (payType == 2) {
        	uCourse.setTradeType("ali_app");
    	}
    	int flag = userCourseService.createOrUpdate(uCourse);
    	Map<String, String> result = new HashMap<>();
	    // 订单生成成功，调用支付接口
	    if (payType == 1 && flag == 1) {
	    	// 微信支付
	    	// TODO 增加订单扫码锁 避免用户重复支付
	    	String priceStr = price.multiply(new BigDecimal(100)).toString();
	    	if (priceStr.contains(".")) {
	    		priceStr = priceStr.substring(0, priceStr.indexOf("."));
	    	}
	    	result = wXPayService.doAppUnifiedOrder(outTradeNo, priceStr, "", courseId + "", body);	    	
	    	result.put("out_trade_no", outTradeNo);
	    	res.setData(result);
	    } else if (payType == 2 && flag == 1) {
	    	// ali支付
	    	String priceStr = price.toString();
	    	res.setData(aliPayService.appPay(outTradeNo, priceStr, body));	  
	    	res.setMsg(outTradeNo);
	    }
    	return res;
	}
	
	

	/**
	 * 扫码支付请求
	 * 
	 * 判断是微信还是支付宝 二码合一
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	@ResponseBody
	public String wxPayUrl(HttpServletRequest request, @RequestParam("outOrderNo") String outOrderNo) {
		log.info("支付开始");
		// 通过浏览器判断微信还是支付宝
		String userAgent = request.getHeader("user-agent");
		if (userAgent.contains("MicroMessenger")) {
			// 微信支付
			UserCourse course = userCourseService.selectCourseByOutOrderNo(outOrderNo);
			if (null == course) {
				return "error";
			}
			// TODO 增加订单扫码锁 避免用户重复支付
//			wXPayService.doUnifiedOrder(outOrderNo, course.getPrice().multiply(new BigDecimal(100)).toString(),
//					CourseOnlineService.getIpAddress(request), course.getId() + "");
			return "success";
		} else {
			// 支付宝
		}

		return "error";
	}

	/**
	 * 支付宝异步通知 Url
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/ali/notify")
	@ResponseBody
	public String alipayNotifyUrl(HttpServletRequest request) {
		log.info("alipay notify begin");
		//获取支付宝POST过来反馈信息
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
			params.put(name, valueStr);
		}
		
		boolean signVerified = aliPayService.isSignVerified(params); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		System.out.println("signVerified = " + signVerified);
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = params.get("out_trade_no");
			//支付宝交易号
			String trade_no = params.get("trade_no");		
			//交易状态
			String trade_status = params.get("trade_status");
			//交易状态
			String total_amount = params.get("total_amount");
    		log.info("ali pay result map: out_trade_no = " + out_trade_no + ":trade_status" + trade_status + ":total_amount=" + total_amount);
			UserCourse course = userCourseService.selectCourseByOutOrderNo(out_trade_no);
            if (null == course) {
            	return "fail";
            }
			if (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISHED")){
				if (null != course && course.getPrice().compareTo(new BigDecimal(total_amount)) == 0) {
					// 支付成功
					course.setPayStatus(2);
					course.setPayCode(trade_no);
//					course.setPayTime(DateUtil.getDate(params.get("timestamp"), DateUtil.DATATIMEF_STR_SEC));
					course.setPayTime(new Date());
					course.setUpdateTime(new Date());
					course.setTradeType("ali_web");
					userCourseService.createOrUpdate(course);	
					// 预约课程，状态更改
					if (course.getCourseType() == UserCourseTypeEnum.appointment.code) {
						// 预约支付成功，更新预约表状态
						UserAppointment ua = userAppointmentService.selectById(course.getCourseId());
						ua.setPayStatus(1);
						ua.setUpdateTime(new Date());
						userAppointmentService.createOrUpdate(ua);							
					} else if (course.getCourseType() == UserCourseTypeEnum.vip.code) {
						// 预约支付成功，更新预约表状态
						UserCourseVip ua = userCourseVipService.selectById(course.getCourseId());
						ua.setPayStatus(2);
						ua.setPayTime(new Date());
						ua.setUpdateTime(new Date());
						userCourseVipService.createOrUpdate(ua);							
					}
					// dealMessage
					userNoticeService.dealMessage(course);
				}	
			}			
			return "success";
			
		}
		//——请在这里编写您的程序（以上代码仅作参考）——
		return "fail";
	}

	/**
	 * 微信异步通知 Url
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/wx/notify")
	@ResponseBody
	public String wxPayNotifyUrl(HttpServletRequest request) {
		System.out.println("微信回调请求已触发");
		log.info("微信回调请求已触发");
		Map<String, String> returnMap = new HashMap<>();
		BufferedReader br;
		try {
			br = request.getReader();
			String str, xmlStr = "";
			while ((str = br.readLine()) != null) {
				xmlStr += str;
			}
			System.out.println(xmlStr);
			if (StringUtils.isNotEmpty(xmlStr)) {
				Map<String, String> resutlMap = wXPayService.getNotifyMap(xmlStr);
				if (null != resutlMap) {
					// 回调成功
					String trade_type = resutlMap.get("trade_type");
					//int total_fee = Integer.parseInt(resutlMap.get("total_fee")); // 分
					String time_end = resutlMap.get("time_end");// 支付完成时间
					String transaction_id = resutlMap.get("transaction_id");// 微信支付订单号
					String out_trade_no = resutlMap.get("out_trade_no");// 商家订单号
					// 更新订单
					UserCourse course = userCourseService.selectCourseByOutOrderNo(out_trade_no);
					if (null != course) {
						// 跟新支付状态
						course.setPayStatus(2);
						course.setPayCode(transaction_id);
						course.setPayTime(DateUtil.getDate(time_end, DateUtil.DATATIMEF_STR_SEC));
						course.setUpdateTime(new Date());
						course.setTradeType("wx_web");
						userCourseService.createOrUpdate(course);
						if (course.getCourseType() == UserCourseTypeEnum.appointment.code) {
							// 预约支付成功，更新预约表状态
							UserAppointment ua = userAppointmentService.selectById(course.getCourseId());
							ua.setPayStatus(1);
							ua.setUpdateTime(new Date());
							userAppointmentService.createOrUpdate(ua);							
						} else if (course.getCourseType() == UserCourseTypeEnum.vip.code) {
							// 预约支付成功，更新预约表状态
							UserCourseVip ua = userCourseVipService.selectById(course.getCourseId());
							ua.setPayStatus(2);
							ua.setPayTime(new Date());
							ua.setUpdateTime(new Date());
							userCourseVipService.createOrUpdate(ua);							
						}
						// dealMessage
						userNoticeService.dealMessage(course);
						returnMap.put("return_code", "SUCCESS");
						returnMap.put("return_msg", "OK");
					} else {
						log.error("微信支付更新状态失败，订单号不存在" + out_trade_no);
						returnMap.put("return_code", "FAIL");
						returnMap.put("return_msg", "FAIL");					
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wXPayService.mapToXml(returnMap);
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
					String tradeState = result.get("trade_state");
					res.setData(tradeState);
					if (StringUtils.isNotEmpty(tradeState) && tradeState.equals("SUCCESS")) {
						// 支付成功 跟新支付状态
						course.setPayStatus(2);
						course.setPayCode(result.get("transaction_id"));
						course.setPayTime(DateUtil.getDate(result.get("time_end"), DateUtil.DATATIMEF_STR_SEC));
						course.setUpdateTime(new Date());
						course.setTradeType(result.get("trade_type"));
						userCourseService.createOrUpdate(course);
						if (course.getCourseType() == UserCourseTypeEnum.appointment.code) {
							// 预约支付成功，更新预约表状态
							UserAppointment ua = userAppointmentService.selectById(course.getCourseId());
							ua.setPayStatus(1);
							ua.setUpdateTime(new Date());
							userAppointmentService.createOrUpdate(ua);							
						} else if (course.getCourseType() == UserCourseTypeEnum.vip.code) {
							// 预约支付成功，更新预约表状态
							UserCourseVip ua = userCourseVipService.selectById(course.getCourseId());
							ua.setPayStatus(2);
							ua.setPayTime(new Date());
							ua.setUpdateTime(new Date());
							userCourseVipService.createOrUpdate(ua);							
						}
						// dealMessage
						userNoticeService.dealMessage(course);
					}
				}				
			} else if (payType == 2) {
				res.setData(aliPayService.doOrderQuery(outTradeNo, ""));
			}
		}	    
		return res;
	}
	
	public static void main(String args[]) {
		BigDecimal a = new BigDecimal("1");
		System.out.println(a.compareTo(new BigDecimal("0.10")));
	}
	
	/**
	 * vip课程付款
	 * @param kemus 所选科目
	 * @param payType 
	 * @param ip
	 * @return
	 */
	@RequestMapping("/vipCoureOrder")
	@ResponseBody
	public ResposeModel vipCoureOrder(@RequestParam("kemus") String kemus, @RequestParam("beginTimeStr") String beginTimeStr, 
			@RequestParam("payType") Integer payType, 
			@RequestParam("ip") String ip) {	
		ResposeModel res = new ResposeModel();
    	// 相关课程查询
    	if (StringUtils.isEmpty(kemus) || StringUtils.isEmpty(beginTimeStr)) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("参数异常");
			return res;
    	} 	
	    Date beginTime = DateUtil.getDate(beginTimeStr, DateUtil.DATATIMEF_STR);		
    	// 相关课程查询
    	if (StringUtils.isEmpty(kemus)) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			return res;
    	}
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}	
		Long userId = user.getId();
		// 在线课程购买
    	UserCourse uCourse = userCourseService.selectPayCourseByUser(0L, userId, UserCourseTypeEnum.vip.code);
    	if (null != uCourse) {
    		// 已购买，返回
    		res.setStatus("0");
    		res.setCode("900001");
			res.setMsg("用户已购买成功，无法重复购买");
			return res;
    	} 
    	int flag = 0;
    	BigDecimal price = new BigDecimal(0);
    	String outTradeNo = "";
    	String body = "";
	    
    	BigDecimal totalPrice = new BigDecimal(0);
    	List<VipCourse> vipList =  vipCourseService.selectAll();
    	for (VipCourse data: vipList) {
    	  if (kemus.contains(data.getId() + "")) {
    		  // 包含该科目 计算价格
    		  totalPrice = totalPrice.add(data.getPrice());
    	  }	
    	}
    	if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			return res;
    	}
    	OutOrderNoGenerate generate = new OutOrderNoGenerate();
    	outTradeNo = generate.makeOrderNum("V" + userId);
    	price = totalPrice;
    	body = "驾考中心-在线课程支付";
    
	    // 订单防刷拦截
	    // 订单生成
		uCourse = new UserCourse();
    	uCourse.setCourseId(0L);
    	uCourse.setCourseType(UserCourseTypeEnum.vip.code);
    	uCourse.setOutTradeNo(outTradeNo);
    	uCourse.setPrice(price);
    	if (payType == 1) {
        	uCourse.setTradeType("wx_app");
    	} else if (payType == 2) {
        	uCourse.setTradeType("ali_app");
    	}
    	uCourse.setUserId(userId);
    	uCourse.setUserName(user.getMobile());
    	uCourse.setCreateTime(new Date());
    	uCourse.setUpdateTime(new Date());
    	uCourse.setPayStatus(0);
    	uCourse.setRefundStatus(0);
    	uCourse.setIsDelete(0);
    	flag = userCourseService.createOrUpdate(uCourse);
    	// 创建user vip course
    	UserCourseVip vipCourse = new UserCourseVip();
    	vipCourse.setKemus(kemus);
    	vipCourse.setIsDelete(0);
    	vipCourse.setPayStatus(0);
    	vipCourse.setMobile(user.getMobile());
    	vipCourse.setUserId(userId);
    	vipCourse.setUserCourseId(uCourse.getId());
    	vipCourse.setPrice(totalPrice);
    	vipCourse.setBeginTime(beginTime);
    	vipCourse.setCreateTime(new Date());
    	vipCourse.setUpdateTime(new Date());
    	Map<String, String> result = new HashMap<>();
	    // 订单生成成功，调用支付接口
	    if (payType == 1 && flag == 1) {
	    	// 微信支付
	    	// TODO 增加订单扫码锁 避免用户重复支付
	    	String priceStr = price.multiply(new BigDecimal(100)).toString();
	    	if (priceStr.contains(".")) {
	    		priceStr = priceStr.substring(0, priceStr.indexOf("."));
	    	}
	    	result = wXPayService.doAppUnifiedOrder(outTradeNo, priceStr, ip, 0 + "", body);	    	
	    	result.put("out_trade_no", outTradeNo);
	    	res.setData(result);
	    } else if (payType == 2 && flag == 1) {
	    	// ali支付
	    	String priceStr = price.toString();
	    	res.setData(aliPayService.appPay(outTradeNo, priceStr, body));	  
	    	res.setMsg(outTradeNo);
	    }
	    return res;	   
	}
	

}
