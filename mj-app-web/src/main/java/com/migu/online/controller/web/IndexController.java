package com.migu.online.controller.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.configuration.NeedLogin;
import com.migu.online.controller.BaseController;
import com.migu.online.model.PlatformNotice;
import com.migu.online.model.PrivateTutor;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.Teacher;
import com.migu.online.model.User;
import com.migu.online.model.UserCourse;
import com.migu.online.model.UserCourseVip;
import com.migu.online.model.VipCourse;
import com.migu.online.ops.vo.PrivateTutorOpsVo;
import com.migu.online.param.VipCourseParam;
import com.migu.online.service.AppointmentManageService;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.DrivingPolicyService;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.ExamPlaceService;
import com.migu.online.service.PlatformNetworkService;
import com.migu.online.service.PlatformNoticeService;
import com.migu.online.service.PrivateTutorService;
import com.migu.online.service.ShufflingService;
import com.migu.online.service.TeacherCommentService;
import com.migu.online.service.TeacherService;
import com.migu.online.service.TrafficLawService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserCourseVipService;
import com.migu.online.service.VipCourseService;
import com.migu.online.utils.DateUtil;
import com.migu.online.vo.CourseOfflineVo;
import com.migu.online.vo.CourseOnlineVo;
import com.migu.online.vo.DrivingPolicyVo;
import com.migu.online.vo.DrivingSchoolVo;
import com.migu.online.vo.PlatformNetworkVo;
import com.migu.online.vo.TeacherCommentVo;
import com.migu.online.vo.TeacherDetailVo;
import com.migu.online.vo.TeacherShowDetailVo;
import com.migu.online.vo.TrafficLawVo;
import com.migu.online.vo.UserCourseOnlineVo;

/**
 * web 首页
 * @author fanyunlong
 *
 */
@Controller
@RequestMapping("/home")
public class IndexController extends BaseController {

	private static final long serialVersionUID = 2931428145753112856L;

	private Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseOfflineService courseOfflineService;
	@Autowired
	private ShufflingService shufflingService;	
	@Autowired
	private CourseOnlineService courseOnlineService;
	@Autowired
	private PlatformNetworkService platformNetworkService;
	@Autowired
	private DrivingPolicyService drivingPolicyService;
	@Autowired
	private PlatformNoticeService platformNoticeService;
	@Autowired
	private TrafficLawService trafficLawService;
	@Autowired
	private DrivingSchoolService drivingSchoolService;
	@Autowired
	private AppointmentManageService appointmentManageService;
	@Autowired
	private PrivateTutorService privateTutorService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private VipCourseService vipCourseService;
	@Autowired
	private UserCourseVipService userCourseVipService;
	@Autowired
	private ExamPlaceService examPlaceService;
	@Autowired
	private TeacherCommentService teacherCommentService;


	/**
	 * 首页加载
	 * @return
	 */
	@GetMapping("index")
	public String index(ModelMap modelMap) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		try {
			modelMap.put("offlineCourseList", courseOfflineService.selectHomePageAll());
			modelMap.put("shufflingList", shufflingService.selectAll());
			modelMap.put("teacherList", teacherService.selectHomePageAll());
			modelMap.put("onlineCourseList", courseOnlineService.selectHomePageAll());
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "index";
	}
	
//	/**
//	 * 网点分布
//	 * @return
//	 */
//	@GetMapping("platform_info")
//	public String platformInfo(ModelMap modelMap,
//			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
//			@RequestParam(value="pageSize", required=false) Integer pageSize) {
//		User user = super.getCurrentUser();
//		modelMap.put("isLogin", user == null ? false:true);
//		if (null == pageIndex) {
//			pageIndex = super.pageIndex;
//		}
//		if (null == pageSize) {
//			pageSize = super.pageSize;
//		}
//		try {
//			Page<PlatformNetworkVo> page = platformNetworkService.selectWebByPage(pageIndex, pageSize);
//			if (null != page) {
//				modelMap.put("platList", page.getResult());
//				modelMap.put("pageIndex", pageIndex);
//				modelMap.put("pageSize", pageSize);
//				modelMap.put("total", page.getTotal());
//				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
//				modelMap.put("totalPage", totalPage);
//			}
//		} catch (Exception e) {
//			log.error(e.toString());
//			e.printStackTrace();
//		}
//		return "platform_info";
//	}
	
	/**
	 * 网点分布
	 * @return
	 */
	@GetMapping("platform_info")
	public String platformInfo(ModelMap modelMap,
			@RequestParam(value="areaId", required=false) Long areaId,
			@RequestParam(value="platName", required=false) String platName,
			@RequestParam(value="teacherId", required=false) Long teacherId,
			@RequestParam(value="kemu", required=false) Integer kemu,
			@RequestParam(value="address", required=false) String address,
			@RequestParam(value="startTime", required=false) String startTime,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			modelMap.put("areaList", drivingSchoolService.selectAreaList());
			modelMap.put("teachers", teacherService.selectAll());
			Map<String, Object> filters = new HashMap<>();
			filters.put("areaId", areaId);
			filters.put("platName", platName);
			filters.put("teacherId", teacherId);
			filters.put("kemu", kemu);
			filters.put("address", address);
			filters.put("startTime", startTime);
			Page<PlatformNetworkVo> page = platformNetworkService.selectWebConditionByPage(filters, pageIndex, pageSize);
			if (null != page) {
				modelMap.put("platList", page.getResult());
				modelMap.put("pageIndex", pageIndex);
				modelMap.put("pageSize", pageSize);
				modelMap.put("total", page.getTotal());
				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
				modelMap.put("totalPage", totalPage);
			}
			modelMap.put("areaId", areaId);
			modelMap.put("platName", platName);
			modelMap.put("teacherId", teacherId);
			modelMap.put("kemu", kemu);
			modelMap.put("address", address);
			modelMap.put("startTime", startTime);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_info";
	}
	
	/**
	 * 网点分布
	 * @return
	 */
	@GetMapping("platform_info_detail")
	public String platformInfoDetail(ModelMap modelMap, @RequestParam("id") Long id) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		try {
			modelMap.put("platDetail", platformNetworkService.selectOne(id));		
			modelMap.put("offlineList", courseOfflineService.selectPlatCourseByPage(super.pageIndex, super.pageSize, id));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_info_detail";
	}
	
	/**
	 * 网点课程
	 * @return
	 */
	@GetMapping("platform_course")
	public String platformCourse(ModelMap modelMap, 
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			Page<CourseOfflineVo> page = courseOfflineService.selectWebByPage(pageIndex, pageSize);
			if (null != page) {
				modelMap.put("offlineCourseList", page.getResult());
				modelMap.put("pageIndex", pageIndex);
				modelMap.put("pageSize", pageSize);
				modelMap.put("total", page.getTotal());
				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
				modelMap.put("totalPage", totalPage);
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_course";
	}
	
	/**
	 * 网点课程详情
	 * @return
	 */
	@GetMapping("platform_course_detail")
	public String platformCourse(ModelMap modelMap, @RequestParam(value="id") Long id) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		try {
			modelMap.put("offlineDetail", courseOfflineService.selectOneByUser(id, super.getCurrentUserId()));		
			modelMap.put("offlineRecommandList", courseOfflineService.selectHomePageAll());
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_course_detail";
	}
	
	/**
	 * 网点课程
	 * @return
	 */
	@GetMapping("platform_teacher")
	public String platformTeacher(ModelMap modelMap, 
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			Page<Teacher> page = teacherService.selectWebByPage(pageIndex, pageSize);
			if (null != page) {
				modelMap.put("teacherList", page.getResult());
				modelMap.put("pageIndex", pageIndex);
				modelMap.put("pageSize", pageSize);
				modelMap.put("total", page.getTotal());
				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
				modelMap.put("totalPage", totalPage);
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_teacher";
	}
	
	/**
	 * 网点课程详情
	 * @return
	 */
	@GetMapping("platform_teacher_detail")
	public String platformTeacherDetail(ModelMap modelMap, @RequestParam(value="id") Long id,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,
			@RequestParam(value="duplicate", required=false, defaultValue = "0") Integer duplicate) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			TeacherShowDetailVo tData = teacherService.selectDetailOne(id);
			if (null == tData) {
				return "error";
			}
			modelMap.put("teacherDetail", tData);		
			modelMap.put("onlineCourseList", courseOnlineService.selectPageByTeacher(id, pageIndex, pageSize));
			// 老师发布私教课程			
			modelMap.put("privateTutorList", privateTutorService.selectByTeacher(tData.getId()));
			modelMap.put("recommandList", privateTutorService.selectRecommandByPage(1, 10, null));
			// comment
			Page<TeacherCommentVo> pageComment = teacherCommentService.selectByPage(id, pageIndex, pageSize);
			modelMap.put("commentList", pageComment);
			modelMap.put("duplicate", duplicate);
			modelMap.put("pageIndex", pageIndex);
			modelMap.put("pageSize", pageSize);
			modelMap.put("total", pageComment.getTotal());
			int totalPage = (Integer.parseInt(pageComment.getTotal() + "") + pageSize - 1) / pageSize;
			modelMap.put("totalPage", totalPage);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_teacher_detail";
	}
	
	/**
	 * 视频列表
	 * @return
	 */
	@RequestMapping("platform_video")
	public String platformVideo(ModelMap modelMap,
			@RequestParam(value="teacherId", required=false) Long teacherId,
			@RequestParam(value="kemu", required=false) Integer kemu,
			@RequestParam(value="payType", required=false) Integer payType,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
	        modelMap.put("teachers", teacherService.selectAll());
			Page<CourseOnlineVo> page = courseOnlineService.selectPublicByPage(teacherId, kemu, payType, pageIndex, pageSize);
			modelMap.put("onlineCourseList", page);	
			modelMap.put("pageIndex", pageIndex);
			modelMap.put("pageSize", pageSize);
			modelMap.put("total", page.getTotal());
			int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
			modelMap.put("totalPage", totalPage);
			// query value
			modelMap.put("teacherId", teacherId);	
			modelMap.put("kemu", kemu);
			modelMap.put("payType", payType);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_video";
	}
	
	/**
	 * 视频详情
	 * @return
	 */
	@GetMapping("platform_video_detail")
	public String platformvideoDetail(HttpServletRequest request, ModelMap modelMap, @RequestParam(value="id") Long id) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);
		try {
			UserCourseOnlineVo course = courseOnlineService.selectUserOne(id, userId);
			course.setPlayNumber(courseOnlineService.getVideoPlayNumber(id));
			modelMap.put("onlineCourse", course);
			// 推荐视频
			modelMap.put("onlineCourseList", courseOnlineService.selectHomePageAll());			
			// 视频播放记录
			if (null != course) {
				courseOnlineService.videoPlayRecord(id, super.getCurrentUser(), CourseOnlineService.getIpAddress(request));
				// 视频目录权限控制 付费视频 在购买后才放开权限 vip用户可以直接观看
				if (course.getIsPay() == 2 && !userCourseVipService.isVipUser(userId)) {
					// 付费视频 用户未支付
					return "platform_video_detail_2";
				} 
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_video_detail_1";
	}
	
	/**
	 * 驾考政策
	 * @return
	 */
	@GetMapping("policy_regulations")
	public String policyRegulations(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			Page<DrivingPolicyVo> page = drivingPolicyService.selectWebByPage(pageIndex, pageSize);
			if (null != page) {
				modelMap.put("policyList", page.getResult());
				modelMap.put("pageIndex", pageIndex);
				modelMap.put("pageSize", pageSize);
				modelMap.put("total", page.getTotal());
				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
				modelMap.put("totalPage", totalPage);
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "policy_regulations";
	}
	
	/**
	 * 政策详情
	 * @return
	 */
	@GetMapping("policy_regulations_detail")
	public String policRegulationsDetail(ModelMap modelMap, @RequestParam(value="id") Long id) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);
		try {
			modelMap.put("policyDetail", drivingPolicyService.selectWebOne(id));
			// 相关政策
			modelMap.put("policyList", drivingPolicyService.selectByPage(1, 10));			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "policy_regulations_detail";
	}
	
	/**
	 * 法律法规
	 * @return
	 */
	@GetMapping("traffic_regulations")
	public String trafficRegulations(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);	
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			Page<TrafficLawVo> page = trafficLawService.selectWebByPage(pageIndex, pageSize);
			if (null != page) {
				modelMap.put("lawList", page.getResult());
				modelMap.put("pageIndex", pageIndex);
				modelMap.put("pageSize", pageSize);
				modelMap.put("total", page.getTotal());
				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
				modelMap.put("totalPage", totalPage);
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "traffic_regulations";
	}
	
	/**
	 * 法律法规详情
	 * @return
	 */
	@GetMapping("traffic_regulations_detail")
	public String trafficRegulationsDetail(ModelMap modelMap, @RequestParam(value="id") Long id) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);	
		try {
			modelMap.put("lawDetail", trafficLawService.selectWebOne(id));	
			// 相关政策
			modelMap.put("lawList", trafficLawService.selectByPage(1, 5));	
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "traffic_regulations_detail";
	}
	
	/**
	 * 平台通知列表
	 * @return
	 */
	@GetMapping("platform_notice")
	public String platformNotice(ModelMap modelMap,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			Page<PlatformNotice> page = platformNoticeService.selectByPage(pageIndex, pageSize);
			if (null != page) {
				modelMap.put("platformNoticeList", page.getResult());
				modelMap.put("pageIndex", pageIndex);
				modelMap.put("pageSize", pageSize);
				modelMap.put("total", page.getTotal());
				int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
				modelMap.put("totalPage", totalPage);

			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_notice";
	}
	
	/**
	 * 平台通知详情
	 * @return
	 */
	@GetMapping("platform_notice_detail")
	public String platformNoticeDetail(ModelMap modelMap, @RequestParam(value="id") Long id) {
		Long userId = super.getCurrentUserId();
		modelMap.put("isLogin", userId == 0 ? false:true);
		try {
			modelMap.put("platformNoticeDetail", platformNoticeService.selectOne(id));
					
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "platform_notice_detail";
	}
	
	/**
	 * app下载
	 * @return
	 */
	@GetMapping("app_download")
	public String appDownload(ModelMap modelMap) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		return "app_download";
	}
	
//	/**
//	 * 报名驾校
//	 * @return
//	 */	
//	@RequestMapping("school_sign_up")
//	public String personSignUp(ModelMap modelMap, @RequestParam(value = "areaId", required = false) Integer areaId,
//			@RequestParam(value = "priceFrom", required = false) Integer priceFrom, @RequestParam(value = "priceTo", required = false) Integer priceTo,
//			@RequestParam(value = "licence", required = false) String licence, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
//			@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "areaName", required = false) String areaName,
//			@RequestParam(value = "priceStr", required = false) String priceStr, @RequestParam(value = "licenceName", required = false) String licenceName) {
//		User user = super.getCurrentUser();
//		modelMap.put("isLogin", user == null ? false:true);		
//		if (null == pageIndex) {
//			pageIndex = super.pageIndex;
//		}
//		if (null == pageSize) {
//			pageSize = 12;
//		}
//		try {
//			modelMap.put("areaList", drivingSchoolService.selectAreaList());		
//			modelMap.put("licenceList", drivingSchoolService.selectLicenceList());
//			Page<DrivingSchoolVo> page = drivingSchoolService.selectConditionByPage(areaId, priceFrom, priceTo, licence, pageIndex,
//					pageSize);
//			modelMap.put("schoolList", page);
//			modelMap.put("pageIndex", pageIndex);
//			modelMap.put("pageSize", pageSize);
//			modelMap.put("total", page.getTotal());
//			int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
//			modelMap.put("totalPage", totalPage);
//			modelMap.put("param", new QuerySchoolParam(areaId, priceFrom, priceTo, licence
//					, pageIndex, pageSize, areaName, priceStr, licenceName));
//		} catch (Exception e) {
//			log.info("query:" + e.toString());
//		}
//
//		return "school_sign_up";
//	}
	
	/**
	 * 报名驾校
	 * @return
	 */	
	@RequestMapping("school_sign_up")
	public String personSignUp(ModelMap modelMap, @RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "priceRange", required = false) String priceRange,
			@RequestParam(value = "schoolName", required = false) String schoolName, 
			@RequestParam(value = "schoolAddress", required = false) String schoolAddress,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);		
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

		return "school_sign_up";
	}
	
	/**
	 * 预约考试查询
	 * @return
	 */
	@GetMapping("test_plan")
	public String personOrderTest(ModelMap modelMap, 
			@RequestParam(value = "licence", required = false) String licence, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "kemu", required = false) Integer kemu, @RequestParam(value = "kemuStr", required = false) String kemuStr,
			@RequestParam(value = "licenceName", required = false) String licenceName,
			@RequestParam(value = "areaId", required = false) Long areaId,
			@RequestParam(value = "examPlaceId", required = false) Long examPlaceId,
			@RequestParam(value = "address", required = false) String address) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);			
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
		return "test_plan";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@GetMapping("about_us_1")
	public String aboutUs1(ModelMap modelMap) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		return "about_us_1";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@GetMapping("about_us_2")
	public String aboutUs2(ModelMap modelMap) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		return "about_us_2";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@GetMapping("about_us_3")
	public String aboutUs3(ModelMap modelMap) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		return "about_us_3";
	}
	
	/**
	 * 私教
	 * @return
	 */
	@GetMapping("private_tutor")
	public String privateTutor(ModelMap modelMap, 
			@RequestParam(value = "teacherId", required = false) Long teacherId,
			@RequestParam(value = "kemu", required = false) Integer kemu, 
			@RequestParam(value = "payType", required = false) Integer payType, 
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);		
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = 12;
		}	
		try {
			// 老师列表
	        modelMap.put("teachers", teacherService.selectAll());
			modelMap.put("kemu", kemu);
			modelMap.put("teacherId", teacherId);
			modelMap.put("payType", payType);
			Map<String, String> filters = new HashMap<String, String>();
			if (null != teacherId && teacherId > 0) {
				filters.put("teacherId", teacherId + "");
			}
			if (null != kemu && kemu > 0) {
				filters.put("kemu", kemu + "");
			}
			if (null != payType && payType > 0) {
				filters.put("payType", payType + "");
			}
			Page<PrivateTutorOpsVo> page = privateTutorService.selectConditionsByPage(pageIndex, pageSize, filters);
			modelMap.put("dataList", page);	
			modelMap.put("pageIndex", pageIndex);
			modelMap.put("pageSize", pageSize);
			modelMap.put("total", page.getTotal());
			int totalPage = (Integer.parseInt(page.getTotal() + "") + pageSize - 1) / pageSize;
			modelMap.put("totalPage", totalPage);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "private_tutor";
	}
	
	/**
	 * 私教detail
	 * @return
	 */
	@GetMapping("private_tutor_detail")
	public String privateTutorDetail(ModelMap modelMap, @RequestParam(value = "id") Long id) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		// 私教课程信息
		PrivateTutor tutor =  privateTutorService.selectById(id);
		modelMap.put("data", tutor);
		if (null != tutor) {
			try {
				// 教师信息
				TeacherDetailVo tData = teacherService.selectOne(tutor.getTeacherId());
				modelMap.put("tData", tData);
				boolean isPay = false;
				// 是否已经购买
				if (null != user && user.getId() > 0) {
					// 已登录
					UserCourse uc = userCourseService.selectPayCourseByUser(id, user.getId(), UserCourseTypeEnum.tutor.code);
					if (null != uc) {
						isPay = true;
					}
				} 
				modelMap.put("isPay", isPay);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 平台推荐课程
		try {
			modelMap.put("recommandList", privateTutorService.selectRecommandByPage(1, 5, id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "private_tutor_detail";
	}
	
	/**
	 * vip课程
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("choose_course")
	public String chooseCourse(ModelMap modelMap) throws Exception {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		modelMap.put("vipList", vipCourseService.selectAll());
		return "choose_course";
	}
	
	/**
	 * vip课程
	 * @return
	 * @throws Exception 
	 */
	@NeedLogin
	@GetMapping("choose_course_1")
	public String chooseCourse1(@RequestParam(value = "course") String course, ModelMap modelMap) throws Exception {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		if (StringUtils.isEmpty(course)) {
			return "error";
		}
		if (course.contains("5") && course.length() > 1) {
			return "error";
		}
		List<VipCourse> vipCourseList = vipCourseService.selectAll();
		List<VipCourse> chooseList = new ArrayList<VipCourse>();
		BigDecimal totalPrice = new BigDecimal(0);
		String kemus = "";
		for (VipCourse data : vipCourseList) {
			if (course.contains(data.getKemu() + "")) {
				chooseList.add(data);
				totalPrice = totalPrice.add(data.getPrice());
				kemus += data.getId() + ",";
			}
		}
	
		if (kemus.length() > 1) {
			kemus = kemus.substring(0, kemus.length() - 1);
		}
		VipCourseParam param = new VipCourseParam();
		param.setcList(chooseList);
		param.settPrice(totalPrice);
		param.setNumber(chooseList.size());
		param.setKemus(kemus);
		param.setBeginTimeStr(DateUtil.getCurDate());
		modelMap.put("vpParam", param);
		modelMap.put("vpParamStr", JSONObject.toJSONString(param));
		return "vip_course";
	}
	
	/**
	 * 报名vip课程
	 * @param kemus 所选科目	
	 * @param payType 
	 * @param ip
	 * @return
	 */
	@RequestMapping("/vipCoureOrder")
	public String vipCoureOrder(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("kemus") String kemus, @RequestParam("beginTimeStr") String beginTimeStr,
			@RequestParam(value = "vpParamStr", required = false) String vpParamStr) {	
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		if (StringUtils.isNoneBlank(vpParamStr)) {
			VipCourseParam param = JSONObject.parseObject(vpParamStr, VipCourseParam.class);
			modelMap.put("vpParam", param);
			modelMap.put("vpParamStr", vpParamStr);
		}
		// 相关课程查询
    	if (StringUtils.isEmpty(kemus) || StringUtils.isEmpty(beginTimeStr)) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("参数异常");
			modelMap.put("error", res);
			return "vip_course";
    	} 	
	    Date beginTime = DateUtil.getDate(beginTimeStr, DateUtil.DATAFORMAT_STR);		
    	// 相关课程查询
    	if (StringUtils.isEmpty(kemus)) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			modelMap.put("error", res);
			return "vip_course";
    	}
		if (null == user) {
			res.setStatus("0");
			res.setMsg("您还未登录");
			modelMap.put("error", res);
			return "redirect:/home/auth/toLogin";
		}	
		Long userId = user.getId();
		// 已购买vip课程，无法重复购买
		List<UserCourseVip> uCourse = userCourseVipService.selectPayCourseByUserId(userId);
    	if (null != uCourse && uCourse.size() > 0) {
    		// 已购买，返回
    		res.setStatus("0");
    		res.setCode("900001");
			res.setMsg("您已购买成功，无法重复购买");
			modelMap.put("error", res);
			return "vip_course";
    	} 
	    
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
			res.setMsg("您选择的课程价格有误，请联系管理员");
			modelMap.put("error", res);
			return "vip_course";
    	}   
    	// 创建user vip course
    	UserCourseVip vipCourse = new UserCourseVip();
    	vipCourse.setKemus(kemus);
    	vipCourse.setIsDelete(0);
    	vipCourse.setPayStatus(0);
    	vipCourse.setUserCourseId(0L);
    	vipCourse.setMobile(user.getMobile());
    	vipCourse.setUserId(userId);
    	vipCourse.setPrice(totalPrice);
    	vipCourse.setBeginTime(beginTime);
    	vipCourse.setCreateTime(new Date());
    	vipCourse.setUpdateTime(new Date());
    	int flag = userCourseVipService.createOrUpdate(vipCourse);
    	if (flag > 0) {
    		String body = UserCourseTypeEnum.vip.desc;
    	    modelMap.put("body", body);
    	    modelMap.put("courseType", UserCourseTypeEnum.vip.code);
    	    modelMap.put("courseId", vipCourse.getId());
    	    modelMap.put("price", totalPrice);
    		return "pay_choose";
    	} else {
    		res.setStatus("0");
    		res.setCode("900009");
			res.setMsg("系统异常");
			modelMap.put("error", res);
			return "vip_course";
    	}
	}
	
	/**
	 * vip课程
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("before_guide")
	public String beforeGuide(ModelMap modelMap) throws Exception {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		return "before_guide";
	}
	
	/**
	 * 老师注册
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("teacher_regist")
	public String teacherRegiste(ModelMap modelMap) throws Exception {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		return "teacher_regist";
	}
}
