package com.migu.online.controller.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.configuration.NeedLogin;
import com.migu.online.controller.BaseController;
import com.migu.online.model.DrivingSchoolLesson;
import com.migu.online.model.PracticeCard;
import com.migu.online.model.User;
import com.migu.online.model.UserCourse;
import com.migu.online.service.AppointmentManageService;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.ExamPlaceService;
import com.migu.online.service.PracticeCardService;
import com.migu.online.service.UserAppointmentService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserCourseVipService;
import com.migu.online.service.UserNoticeService;
import com.migu.online.service.UserService;
import com.migu.online.utils.DateUtil;
import com.migu.online.vo.DrivingSchoolVo;
import com.migu.online.vo.UserVo;

/**
 * web 首页
 * @author fanyunlong
 *
 */
@Controller
@RequestMapping("/home/person_center")
public class PersonCenterController extends BaseController {

	private static final long serialVersionUID = 2931428145753112856L;

	private Logger log = LoggerFactory.getLogger(PersonCenterController.class);
	
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private UserAppointmentService userAppointmentService;
	@Autowired
	private DrivingSchoolService drivingSchoolService;
	@Autowired
	private PracticeCardService practiceCardService;
	@Autowired
	private AppointmentManageService appointmentManageService;
	@Autowired
	private UserCourseVipService userCourseVipService;
	@Autowired
	private ExamPlaceService examPlaceService;
	@Autowired
	private UserNoticeService userNoticeService;
	@Autowired
	private UserService userService;

	/**
	 * 个人中心
	 * @return
	 */
	@NeedLogin
	@GetMapping("")
	public String index(ModelMap modelMap) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		return "person_center";
	}
	
	/**
	 * 未购买视频列表
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_video")
	public String personVideo(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("onlineCourseList", userCourseService.selectUnpayOnlineByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_video";
	}
	
	/**
	 * 我的购买视频列表
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_video_1")
	public String personVideo1(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("onlineCourseList", userCourseService.selectPayOnlineByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_video_1";
	}
	
	/**
	 * 未购买网点课程
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_course")
	public String personCourse(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("offlineCourseList", userCourseService.selectUnpayOfflineByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_course";
	}
	
	/**
	 * 我的购买网点列表
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_course_1")
	public String personCourse1(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("offlineCourseList", userCourseService.selectPayOfflineByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_course_1";
	}
	
	/**
	 * 我已预约考试成绩
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_check_score")
	public String personCheckScore(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("appointmentList", userAppointmentService.selectScoreByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_check_score";
	}
//	
//	/**
//	 * 预约考试查询
//	 * @return
//	 */
//	@GetMapping("person_order_test")
//	public String personOrderTest(ModelMap modelMap, 
//			@RequestParam(value = "licence", required = false) String licence, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
//			@RequestParam(value = "pageSize", required = false) Integer pageSize,
//			@RequestParam(value = "kemu", required = false) Integer kemu, @RequestParam(value = "kemuStr", required = false) String kemuStr,
//			@RequestParam(value = "licenceName", required = false) String licenceName) {
//		modelMap.put("isLogin", true);
//		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
//		try {
//			QuerySchoolParam param = new QuerySchoolParam();
//			param.setKemu(kemu);			
//			param.setKemuStr(kemuStr);
//			param.setLicence(licence);
//			param.setLicenceName(licenceName);
//			modelMap.put("licenceList", drivingSchoolService.selectLicenceList());
//			modelMap.put("param", param);
//			modelMap.put("appointList", appointmentManageService.selectByCondition(kemu, licence, super.getCurrentUserId()));			
//		} catch (Exception e) {
//			log.error(e.toString());
//			e.printStackTrace();
//		}
//		return "person_center/person_order_test";
//	}
	
	/**
	 * 预约考试查询
	 * @return
	 */
	@GetMapping("person_order_test")
	public String personOrderTest(ModelMap modelMap, 
			@RequestParam(value = "licence", required = false) String licence, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "kemu", required = false) Integer kemu,
			@RequestParam(value = "areaId", required = false) Long areaId,
			@RequestParam(value = "examPlaceId", required = false) Long examPlaceId,
			@RequestParam(value = "address", required = false) String address) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		try {
			modelMap.put("areaList", drivingSchoolService.selectAreaList());	
			modelMap.put("examPlaceList", examPlaceService.selectAll());

			modelMap.put("licenceList", drivingSchoolService.selectLicenceList());
			Map<String, Object> filters = new HashMap<>();
			filters.put("kemu", kemu);
			filters.put("licence", licence);
			filters.put("areaId", areaId);
			filters.put("examPlaceId", examPlaceId);
			filters.put("address", address);
			modelMap.put("appointList", appointmentManageService.selectByCondition2(filters, super.getCurrentUserId()));
			
			modelMap.put("kemu", kemu);
			modelMap.put("licence", licence);
			modelMap.put("areaId", areaId);
			modelMap.put("examPlaceId", examPlaceId);
			modelMap.put("address", address);	
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_order_test";
	}
	
	/**
	 * 进入预约
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_order_require")
	public String personOrderRequire(ModelMap modelMap, @RequestParam("appointId") Long appointId) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		try {
			modelMap.put("data", appointmentManageService.selectOpsById(appointId));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_order_require";
	}
	
	/**
	 * 我已预约考试
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_order_record")
	public String personOrderRecord(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("appointmentList", userAppointmentService.selectByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_order_record";
	}
	
//	/**
//	 * 报名驾校
//	 * @return
//	 */	
//	@RequestMapping("person_sign_up")
//	public String personSignUp(ModelMap modelMap, @RequestParam(value = "areaId", required = false) Integer areaId,
//			@RequestParam(value = "priceFrom", required = false) Integer priceFrom, @RequestParam(value = "priceTo", required = false) Integer priceTo,
//			@RequestParam(value = "licence", required = false) String licence, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
//			@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "areaName", required = false) String areaName,
//			@RequestParam(value = "priceStr", required = false) String priceStr, @RequestParam(value = "licenceName", required = false) String licenceName) {
//		modelMap.put("isLogin", true);
//		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
//		if (null == pageIndex) {
//			pageIndex = super.pageIndex;
//		}
//		if (null == pageSize) {
//			pageSize = super.personPageSize;
//		}
//		try {
//			modelMap.put("areaList", drivingSchoolService.selectAreaList());		
//			modelMap.put("licenceList", drivingSchoolService.selectLicenceList());
//			Page<DrivingSchoolVo> page = drivingSchoolService.selectConditionByPage(areaId, priceFrom, priceTo, licence, pageIndex,
//					pageSize);
//			modelMap.put("schoolList", page);
//			modelMap.put("total", page.getTotal());
//			modelMap.put("pageIndex", pageIndex);
//			int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
//			modelMap.put("totalPage", totalPage);
//			modelMap.put("param", new QuerySchoolParam(areaId, priceFrom, priceTo, licence
//					, pageIndex, pageSize, areaName, priceStr, licenceName));
//		} catch (Exception e) {
//			log.info("query:" + e.toString());
//		}
//
//		return "person_center/person_sign_up";
//	}
	/**
	 * 报名驾校
	 * @return
	 */	
	@NeedLogin
	@RequestMapping("person_sign_up")
	public String personSignUp(ModelMap modelMap, @RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "priceRange", required = false) String priceRange,
			@RequestParam(value = "schoolName", required = false) String schoolName, 
			@RequestParam(value = "schoolAddress", required = false) String schoolAddress,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));		
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = 12;
		}
		try {
			modelMap.put("areaList", drivingSchoolService.selectAreaList());	
			Integer priceFrom = null, priceTo = null;
			if (StringUtils.isNotEmpty(priceRange) && priceRange.contains("-")) {
				priceFrom = Integer.parseInt(priceRange.split("-")[0]);
				priceTo = Integer.parseInt(priceRange.split("-")[1]);
			}
			Page<DrivingSchoolVo> page = drivingSchoolService.selectConditionByPage2(areaId, priceFrom, priceTo, schoolName, schoolAddress, pageIndex,
					pageSize);
			modelMap.put("schoolList", page);
			modelMap.put("pageIndex", pageIndex);
			modelMap.put("pageSize", pageSize);
			modelMap.put("total", page.getTotal());
			int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
			modelMap.put("totalPage", totalPage);
			modelMap.put("priceRange", priceRange);
			modelMap.put("schoolName", schoolName);
			modelMap.put("areaId", areaId);
			modelMap.put("schoolAddress", schoolAddress);
		} catch (Exception e) {
			log.info("query:" + e.toString());
		}

		return "person_center/person_sign_up";
	}
	
	/**
	 * 我已报名驾校
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_sign_up_1")
	public String personSignUp1(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("schoolList", userCourseService.selectUserDrivingCourse(userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_sign_up_1";
	}
	
	/**
	 * 报名驾校在线支付
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_sign_up_pay")
	public String personSignUpPay(ModelMap modelMap, @RequestParam("courseId") Long courseId) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		try {
			modelMap.put("data", userCourseService.selectOneByUser(courseId, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_sign_up_pay";
	}
	
	/**
	 * 驾校详情
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_sign_up_2")
	public String personSignUp2(ModelMap modelMap, @RequestParam("id") Integer id) {
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));	
		modelMap.put("isLogin", true);
		Long userId = super.getCurrentUserId();
		try {
			modelMap.put("schoolDetail", drivingSchoolService.selectOne(id, userId));	
			// 是否已经报名成功
			// 用户报名驾校 
			List<UserCourse> list = userCourseService.selectSignSchoolCourse(userId);
			// 用户只能报名一个驾校
			if (null != list && list.size() > 0) {
				modelMap.put("signed", "1");
			} else {
				modelMap.put("signed", "0");
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_sign_up_2";
	}
	
	/**
	 * 报名驾校课程
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_sign_up_3")
	public String personSignUp3(ModelMap modelMap, @RequestParam("courseId") Long courseId) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		modelMap.put("courseId", courseId);	
		try {
			// 查询课程情况
			DrivingSchoolLesson record = drivingSchoolService.selectLessonById(courseId);
			if (null != record && record.getSchoolId() != null) {
				modelMap.put("school", drivingSchoolService.selectById(record.getSchoolId()));
			} else {
				return "error";
			}
			modelMap.put("schoolLesson", record);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_sign_up_3";
	}
	
	/**
	 * 退款
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_refund")
	public String personRefund(ModelMap modelMap, @RequestParam("courseId") Long courseId) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		modelMap.put("courseId", courseId);			
		try {
			UserCourse data = userCourseService.selectOneByUser(courseId, super.getCurrentUserId());
			if (null == data || data.getPayStatus() != 2 || data.getRefundStatus() > 0) {
				return "person_center/person_refund";
			}
			modelMap.put("userCourse", data);			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_refund";
	}
	
	/**
	 * 退款提交
	 * @return
	 */
	@NeedLogin
	@PostMapping("person_refund_submit")
	public String personRefundSubmit(ModelMap modelMap, @RequestParam("refundBankUserName") String name
			, @RequestParam("refundBankNo") String bankNo, @RequestParam("courseId") Long courseId) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		try {
			UserCourse data = userCourseService.selectOneByUser(courseId, super.getCurrentUserId());
			if (null == data || data.getPayStatus() != 2 || data.getRefundStatus() > 0) {
				return "person_center/person_refund";
			}
		    data.setRefundStatus(1);
		    data.setRefundBankNo(bankNo);
		    data.setRefundBankUserName(name);
		    if(userCourseService.createOrUpdate(data) >= 1) {
		    	// 成功
		    	return "redirect:/home/person_center/person_sign_up_1";
		    }
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_refund";
	}
	
	/**
	 * 购买选项卡
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_card")
	public String practiceCard(ModelMap modelMap) {
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		try {
            // 查询用户选项卡购买情况
            //付费判断
			UserVo vo = new UserVo();
    		UserCourse pay = userCourseService.selectPayCourseByUser(1l, super.getCurrentUserId(), UserCourseTypeEnum.practice.code);
    		PracticeCard card = practiceCardService.getOne(1L);
    		if (null == pay) {
    			vo.setIsBuyCard(0);
    		} else {
    			vo.setIsBuyCard(1);
        		boolean isExpired = false;
        		Long expiredDays = card.getExpiredDays().longValue();
    			User nowUser = userService.selectById(super.getCurrentUserId());  
        		if (nowUser.getCardExpiredTime() != null && DateUtil.compareTime(nowUser.getCardExpiredTime(), new Date())) {
        			// 过期
        			isExpired = true;
        			expiredDays = -1L;
        		} else if (null != nowUser.getCardExpiredTime()) {
        			expiredDays = DateUtil.getQuot(nowUser.getCardExpiredTime(), new Date());
        		}
        		modelMap.put("isExpired", isExpired);
        		modelMap.put("expiredDays", expiredDays);
        		vo.setCardExpiredTime(nowUser.getCardExpiredTime());
    		}
    		
    		// vip课程是否购买
    		modelMap.put("vip", userCourseVipService.isVipUser(super.getCurrentUserId()));
    		vo.setCardPrice(card.getPrice());
			modelMap.put("data", vo);
			modelMap.put("card", card);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_card";
	}
	
	/**
	 * 已购买私教
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_private_tutor")
	public String personPrivateTutor(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("privateTutorList", userCourseService.selectPayTutorByPage(pageIndex, pageSize, userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_private_tutor";
	}
	
	/**
	 * 已购买VIP
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_vip_list")
	public String personVip(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.personPageSize;
		}
		try {
			modelMap.put("dataList", userCourseVipService.selectCourseDetailByUserId(userId));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_vip_list";
	}
	
	/**
	 * 已购买VIP
	 * @return
	 */
	@NeedLogin
	@GetMapping("person_notice_list")
	public String personNoticeList(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", true);
		modelMap.put("user", super.getCacheUser(super.getCurrentUserId()));			
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = 1000;
		}
		try {
			modelMap.put("dataList", userNoticeService.selectPageByUserId(userId, pageIndex, pageSize));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "person_center/person_notice_list";
	}
	
}
