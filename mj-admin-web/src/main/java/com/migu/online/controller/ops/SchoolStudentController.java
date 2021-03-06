package com.migu.online.controller.ops;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.migu.online.common.CertificateTypeEnum;
import com.migu.online.common.SexEnum;
import com.migu.online.common.SourceEnum;
import com.migu.online.controller.BaseController;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.DrivingSchoolLesson;
import com.migu.online.model.Student;
import com.migu.online.model.UserCourse;
import com.migu.online.model.UserCourseInfo;
import com.migu.online.model.system.SysUser;
import com.migu.online.ops.vo.UserSchoolCourseVo;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.StudentService;
import com.migu.online.service.UserCourseInfoService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserNoticeService;
import com.migu.online.service.pay.AliPayService;
import com.migu.online.service.pay.WXPayService;
import com.migu.online.utils.DateUtil;
import com.migu.online.utils.ExportExcel;
import com.migu.online.utils.ExportMapper;

@Controller
@RequestMapping("/ops/schoolStudent/")
public class SchoolStudentController extends BaseController{
	private Logger log = LoggerFactory.getLogger(SchoolStudentController.class);
	
    @Autowired
    private DrivingSchoolService drivingSchoolService;  
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private UserCourseInfoService userCourseInfoService;
    @Autowired
	private AliPayService aliPayService;
    @Autowired
	private WXPayService wxPayService;
    @Autowired
	private UserNoticeService userNoticeService;
    @Autowired
    private StudentService studentService;
    
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
    	return "ops/school/school_student";
    }
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("school_student_enroll")
    public String schoolStudentEnroll(ModelMap modelMap,@RequestParam(value = "id",required = false) Integer lessonId){
    	modelMap.put("lessonId", lessonId);
    	return "ops/school/school_student_enroll";
    }
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("adjustClass")
    public String adjustClass(ModelMap modelMap,@RequestParam(value = "lessonId",required = false) Integer lessonId
    		,@RequestParam(value = "userCourseId",required = false) Integer userCourseId){
    	modelMap.put("lessonId", lessonId);
    	modelMap.put("userCourseId", userCourseId);
    	return "ops/school/adjust_class";
    }
    
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("allSchoolStudent")
    public String allSchoolStudent(ModelMap modelMap){
    	return "ops/school/all_school_student";
    }
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("school_student_refund")
    public String studentRefund(ModelMap modelMap){
        return "ops/school/school_student_refund";
    }
    
    @SuppressWarnings("rawtypes")
	@GetMapping("getAdjustClasses")
    @ResponseBody
    public ResponsePageData getAdjustClasses(@RequestParam(value = "page",defaultValue = "0") Integer pageIndex,
            @RequestParam(value = "limit",defaultValue = "20") Integer limit,
         @RequestParam(value = "licence",required = false) String licence,
         @RequestParam(value = "lessonId",required = false) String lessonId,
         @RequestParam(value = "classNo",required = false) String classNo,
         @RequestParam(value = "className",required = false) String className,
         @RequestParam(value = "startTime",required = false) String startTime) throws Exception{
			SysUser currentUser = getCurrentUser();
			Long schoolId = null;
			if (currentUser.getRelateId() != null) {
			schoolId = currentUser.getRelateId();
			}
			Map<String,String> params = new HashMap<String, String>();
	    	params.put("pageIndex", pageIndex+"");
	    	params.put("pageSize", limit+"");
	    	params.put("licence", licence);
	    	params.put("lessonId", lessonId);
	    	params.put("schoolId", schoolId+"");
	    	params.put("classNo", classNo);
	    	params.put("className", className);
	    	params.put("startTime", startTime);
	    	
	    	List<DrivingSchoolLesson> result = drivingSchoolService.selectAdjustClassesByPage(params);
	    	
	    	return new ResponsePageData<DrivingSchoolLesson>(result.size(), result);
    }

    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     * @throws Exception 
     */
    @GetMapping("edit")
    public String edit(ModelMap modelMap,@RequestParam(value = "id",required = false) Integer id,
    		@RequestParam(value = "flag",defaultValue = "1") Integer editFlag){
    		DrivingSchoolLesson model = new DrivingSchoolLesson();
		    if(editFlag > 0) {
		    	model = drivingSchoolService.selectLessonById(Long.valueOf(id));
		    }
		    modelMap.put("model",model);
		    modelMap.put("edit",editFlag != 2);
		    return "ops/school/school_lesson_edit";
    }
    /**
     * 数据处理
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value ="lessonId",required = false) Integer lessonId,@RequestParam(value = "page",defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                    @RequestParam(value = "licence",required = false) String licence,
                                    @RequestParam(value = "classNo",required = false) String classNo,
                                    @RequestParam(value = "className",required = false) String className,
                                    @RequestParam(value = "userName",required = false) String userName,
                                    @RequestParam(value = "telNo",required = false) String telNo,
                                    @RequestParam(value = "startTime",required = false) String startTime,
                                    @RequestParam(value = "payType",required = false) String payType,
                                    @RequestParam(value = "isPreEntry",required = false) String isPreEntry) throws Exception{
    	SysUser currentUser = getCurrentUser();
    	Long schoolId = null;
    	if (currentUser.getRelateId() != null) {
    		//SysUserOpsVo sysUserOpsVo =sysUserService.selectOpsById(currentUser.getId());
    		 schoolId = currentUser.getRelateId();
    	}
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("schoolId", schoolId);
    	params.put("lessonId", lessonId);
    	params.put("licence", licence);
    	params.put("classNo", classNo);
    	params.put("className", className);
    	params.put("userName", userName);
    	params.put("telNo", telNo);
    	params.put("startTime", startTime);
    	params.put("payType", payType);
    	params.put("isPreEntry", isPreEntry);
    	params.put("startIndex", (pageIndex-1)*10);
		params.put("endIndex", pageIndex*limit);
    	int cnt = userCourseService.selectSchoolStudentCourseCnt(params);
    	List<UserSchoolCourseVo> result = userCourseService.selectSchoolStudentCourse(params);
    	
//    	List<UserSchoolCourseVo> newResult = new ArrayList<UserSchoolCourseVo>();
//    	   
//    	//是否需要预录入信息
//    	//if (isPreEntry != null) {
//    		for (UserSchoolCourseVo userSchoolCourseVo:result) {
//    			Map<String,Object> studentParams = new HashMap<String, Object>();
//    			studentParams.put("userName", userSchoolCourseVo.getUserName());
//    			studentParams.put("telNo", userSchoolCourseVo.getTelNo());
//    			List<Student> students = studentService.selectByParams(studentParams);
//    			if (students.size() > 0) {
//    				userSchoolCourseVo.setIsPreEntry(1);
//    				userSchoolCourseVo.setPreEntryStudent(students.get(0));
//    			} else {
//    				userSchoolCourseVo.setIsPreEntry(0);
//    			}
//    			//全部
//    			if (StringUtils.isEmpty(isPreEntry)) {
//    				newResult.add(userSchoolCourseVo);
//    			} else if(isPreEntry.equals(userSchoolCourseVo.getIsPreEntry()+"")) {
//    				newResult.add(userSchoolCourseVo);
//    			}
//    			
//    		}
//    		return new ResponsePageData<UserSchoolCourseVo>(newResult.size(), newResult);
//    	}else {
//    		return new ResponsePageData<UserSchoolCourseVo>(result.size(), result);
//    	}
    	return new ResponsePageData<UserSchoolCourseVo>(cnt, result);
    	
//    	for (UserSchoolCourseVo userSchoolCourseVo:result) {
//    		getCurrentUser();
//    		StringBuilder idUpsb = new StringBuilder();
//    		idUpsb.append("<a href='"+userSchoolCourseVo.getIdUp()+"' download='image' style='color:blue;'>");
//    		idUpsb.append("查看");
//    		idUpsb.append("</a>");
//    		
//    		StringBuilder idDownsb = new StringBuilder();
//    		idDownsb.append("<a href='"+userSchoolCourseVo.getIdDown()+"' download='image' style='color:blue;'>");
//    		idDownsb.append("查看");
//    		idDownsb.append("</a>");
//    		
//    		
//    		
//    		userSchoolCourseVo.setIdUp(idUpsb.toString());
//    		userSchoolCourseVo.setIdDown(idDownsb.toString());
//    	}
    	//return new ResponsePageData<UserSchoolCourseVo>(cnt, result);
    	
    }
    
    @GetMapping("preEntryDetail")
    public String preEntryDetail(ModelMap modelMap,@RequestParam(value = "id",required = false) Integer id
    		){
		    Student model = new Student();
		    model =  studentService.getStudent(id);
		    model.setCertificateTypeDesc(CertificateTypeEnum.getEnumByCode(model.getCertificateType()).getDesc());
		    model.setSexDesc(SexEnum.getEnumByCode(model.getSex()).getDesc());
		    model.setSourceDesc(SourceEnum.getEnumByCode(model.getSource()).getDesc());
		    
		    modelMap.put("model",model);
		    return "ops/school/pre_entry_detail";
    }
    
    
    /**
     * 数据处理
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("schoolStudentEnrollData")
    @ResponseBody
    public ResponsePageData schoolStudentEnrollData(@RequestParam(value = "page",defaultValue = "0") Integer pageIndex,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                    @RequestParam(value = "lessonId",required = false) String lessonId,
                                    @RequestParam(value = "licence",required = false) String licence,
                                    @RequestParam(value = "schoolName",required = false) String schoolName,
                                    @RequestParam(value = "userName",required = false) String userName) throws Exception{
    	SysUser currentUser = getCurrentUser();
    	Long schoolId = null;
    	if (currentUser.getRelateId() != null) {
    		 schoolId = currentUser.getRelateId();
    	}
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("schoolId", schoolId);
    	params.put("licence", licence);
    	params.put("schoolName", schoolName);
    	params.put("userName", userName);
    	params.put("lessonId", lessonId);
    	params.put("status", 1);
    	params.put("noRefund", 2);
    	params.put("startIndex", (pageIndex-1)*10);
		params.put("endIndex", pageIndex*limit);
    	int cnt = userCourseService.selectSchoolStudentCourseCnt(params);
    	List<UserSchoolCourseVo> result = userCourseService.selectSchoolStudentCourse(params);
    	return new ResponsePageData<UserSchoolCourseVo>(cnt, result);
    	
    }
    
    
    /**
     * 数据处理
     * @throws Exception 
     */
	@GetMapping("exportEnroll")
    @ResponseBody
    public void exportEnroll(HttpServletRequest request,
    		HttpServletResponse response,
                                    @RequestParam(value = "lessonId",required = false) String lessonId) throws Exception{
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("lessonId", lessonId);
    	params.put("status", 1);
    	List<UserSchoolCourseVo> result = userCourseService.selectSchoolStudentCourse(params);
    	
    	if (result.size() == 0) {
    		throw new Exception("无数据可导出!");
    	}
        for (UserSchoolCourseVo vo : result) {
        	vo.setStartTimeStr(DateUtil.dateToDateString(vo.getStartTime()));
        }
    	ExportExcel ex = new ExportExcel();
    	 
    	String jsonStr=JSON.toJSONString(result);
    	String sheaders="id,userName,licenceName,classNo,className,status,payStatus,startTimeStr,price,sex,firstApply,payType,telNo,idUp,idDown,";
    	String headerDesc="id,学生姓名,驾照,班级编号,班级名称,状态,支付状态,开班时间,报名费,性别,是否初次领证,付费方式,手机号码,身份证正面照片,身份证反面照片,";
    	String fileName="报名学生明细"+new Date().getTime()+".xls";
    	String title="明细";
    	

    	
    	ExportMapper statusMapper = new ExportMapper();
    	statusMapper.setName("status");
    	Map<String,String> statusMap = new HashMap<String, String>();
    	statusMap.put(0+"", "报名中");
    	statusMap.put(1+"", "报名成功");
    	statusMap.put(2+"", "报名失败");
    	statusMapper.setMap(statusMap);
    	
    	ExportMapper firstApplyMapper = new ExportMapper();
    	firstApplyMapper.setName("firstApply");
    	Map<String,String> firstApplyMap = new HashMap<String, String>();
    	firstApplyMap.put(0+"", "否");
    	firstApplyMap.put(1+"", "是");
    	firstApplyMapper.setMap(firstApplyMap);
    	
    	ExportMapper sexMapper = new ExportMapper();
    	sexMapper.setName("sex");
    	Map<String,String> sexMap = new HashMap<String, String>();
    	sexMap.put(0+"", "男");
    	sexMap.put(1+"", "女");
    	sexMapper.setMap(sexMap);
    	
    	ExportMapper payTypeMapper = new ExportMapper();
    	payTypeMapper.setName("payType");
    	Map<String,String> payTypeMap = new HashMap<String, String>();
    	payTypeMap.put(0+"", "在线支付");
    	payTypeMap.put(1+"", "线下支付");
    	payTypeMapper.setMap(payTypeMap);
    	
    	ExportMapper payStatusMapper = new ExportMapper();
    	payStatusMapper.setName("payStatus");
    	Map<String,String> payStatusMap = new HashMap<String, String>();
    	payStatusMap.put(0+"", "未付款");
    	payStatusMap.put(2+"", "付款完成");
    	payStatusMapper.setMap(payStatusMap);
    	
    	List<ExportMapper> exportMappers = new ArrayList<ExportMapper>();
    	exportMappers.add(payStatusMapper);
    	exportMappers.add(payTypeMapper);
    	exportMappers.add(sexMapper);
    	exportMappers.add(firstApplyMapper);
    	exportMappers.add(statusMapper);
    	
    	ex.export(jsonStr, headerDesc,sheaders, fileName, title,exportMappers, response, request);
    	
    }
    
    /**
     * 数据处理
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("allSchoolStudentData")
    @ResponseBody
    public ResponsePageData allSchoolStudentData(@RequestParam(value = "page",defaultValue = "0") Integer pageIndex,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                    @RequestParam(value = "licence",required = false) String licence,
                                    @RequestParam(value = "schoolName",required = false) String schoolName,
                                    @RequestParam(value = "userName",required = false) String userName,
                                    @RequestParam(value = "status",required = false) String status,
                                    @RequestParam(value = "refundStatus",required = false) Integer refundStatus) throws Exception{
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("licence", licence);
    	params.put("schoolName", schoolName);
    	params.put("userName", userName);
    	params.put("status", status);
    	params.put("startIndex", (pageIndex-1)*10);
		params.put("endIndex", pageIndex*limit);
		params.put("refundStatus", refundStatus);
    	int cnt = userCourseService.selectSchoolStudentCourseCnt(params);
    	List<UserSchoolCourseVo> result = userCourseService.selectSchoolStudentCourse(params);
    	return new ResponsePageData<UserSchoolCourseVo>(cnt, result);
    	
    }
    
    /**
     * 数据处理
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("refundData")
    @ResponseBody
    public ResponsePageData refundData(@RequestParam(value = "page",defaultValue = "0") Integer pageIndex,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                    @RequestParam(value = "licence",required = false) String licence,
                                    @RequestParam(value = "classNo",required = false) String classNo,
                                    @RequestParam(value = "className",required = false) String className,
                                    @RequestParam(value = "startTime",required = false) String startTime,
                                    @RequestParam(value = "refundStatus",required = false) String refundStatus) throws Exception{
    	SysUser currentUser = getCurrentUser();
    	Long schoolId = null;
    	if (currentUser.getRelateId() != null) {
    		//SysUserOpsVo sysUserOpsVo =sysUserService.selectOpsById(currentUser.getId());
    		 schoolId = currentUser.getRelateId();
    	}
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("schoolId", schoolId);
    	params.put("licence", licence);
    	params.put("classNo", classNo);
    	params.put("className", className);
    	params.put("startTime", startTime);
    	params.put("refundStatus", refundStatus == null? "1,2,3":refundStatus);
    	params.put("startIndex", (pageIndex-1)*10);
		params.put("endIndex", pageIndex*limit);
		
    	int cnt = userCourseService.selectSchoolStudentCourseCnt(params);
    	List<UserSchoolCourseVo> result = userCourseService.selectSchoolStudentCourse(params);
    	return new ResponsePageData<UserSchoolCourseVo>(cnt, result);
    	
    }

    
    
    @GetMapping("refunded")
    @ResponseBody
    public String refunded(@RequestParam("id") Long id){
    	UserCourseInfo record = userCourseInfoService.selectById(id);
		if (null == record) {
			return "error";
		}
		// 查询支付状态
		UserCourse data = userCourseService.selectById(record.getUserCourseId());
		if (null ==data || data.getPayStatus() != 2) {
			return "status error";
		}
		// 后台通过(退款成功)
		data.setRefundStatus(2);
		record.setUpdateTime(new Date());
        return userCourseService.createOrUpdate(data) >= 1 ? "success" : "error";
    }
    
    
    @PostMapping("save")
    @ResponseBody
    public String save(DrivingSchoolLesson model, String startTimeStr){
    	if (null == model) {
    		return "error";
    	}

		if (StringUtils.isNotEmpty(startTimeStr)) {
			model.setStartTime(DateUtil.getDate(startTimeStr, DateUtil.DATAFORMAT_STR));
		}
    	DrivingSchoolLesson record = null;
    	if (model.getId() != null && model.getId() > 0) {
    		// 更新
    		 record = drivingSchoolService.selectLessonById(model.getId());
    		if (null == record) {
    		  return "error";	
    		}
    		record.setStartTime(model.getStartTime());
    		record.setLicence(model.getLicence());;
    		record.setLimitNum(model.getLimitNum());;
    		record.setName(model.getName());
    		record.setPrice(model.getPrice());
    		record.setUpdateTime(new Date());
            return drivingSchoolService.createOrUpdateSchoolLesson(record) >= 1 ? "success" : "error";
    	} else {
    		record = new DrivingSchoolLesson();
    		
    		SysUser currentUser = getCurrentUser();
    		record.setSchoolId(currentUser.getRelateId().intValue());
			record.setStartTime(model.getStartTime());
			record.setLicence(model.getLicence());;
			record.setLimitNum(model.getLimitNum());;
			record.setName(model.getName());
			record.setPrice(model.getPrice());
			record.setUpdateTime(new Date());
			record.setCreateTime(new Date());
			record.setUpdateTime(new Date());
			record.setIsDelete(0);
    	}   	
        return drivingSchoolService.createOrUpdateSchoolLesson(record) >= 1 ? "success" : "error";
    }
    
    @PostMapping("saveAdjust")
    @ResponseBody
    public String saveAdjust(@RequestParam("userCourseId") Long userCourseId,
    		@RequestParam("disLessonId") Long disLessonId){
    	if (userCourseId == null || disLessonId== null) {
    		return "error";
    	}
    	
    	DrivingSchoolLesson record = drivingSchoolService.selectLessonById(disLessonId);
    	if (record == null) {
    		 return "error";
    	}
    	
    	UserCourse userCourse = userCourseService.selectById(userCourseId);
    	
    	if (userCourse == null) {
   		 	return "error";
    	}
    	
    	userCourse.setCourseId(disLessonId);
    	
        return userCourseService.createOrUpdate(userCourse) >= 1 ? "success" : "error";
    }

    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Integer id){
        return drivingSchoolService.deleteById(id);
    }
    
    @GetMapping("approve")
    @ResponseBody
    public String approve(@RequestParam("id") Long id){
    	UserCourseInfo record = userCourseInfoService.selectById(id);
		if (null == record) {
			return "error";
		}
		// 查询支付状态
		UserCourse data = userCourseService.selectById(record.getUserCourseId());
		if (null ==data || data.getPayStatus() != 2 || record.getStatus() != 0) {
			return "status error";
		}
		// 后台通过
		record.setStatus(1);
		record.setUpdateTime(new Date());
		int flag = userCourseInfoService.createOrUpdate(record);
		if (flag < 1) {
		  return "error";	
		}
		// TODO 通知
		userNoticeService.dealSchoolMessage2(1, data);
        return "success";
    }
    
    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     * @throws Exception 
     */
    @GetMapping("toDeny")
    public String toDeny(ModelMap modelMap, @RequestParam(value = "id",required = false) Integer id){
    	modelMap.put("id", id);
		return "ops/school/deny";
    }
    
    @RequestMapping("deny")
    @ResponseBody
    public String deny(@RequestParam("id") Long id, @RequestParam("reason") String reason){
    	UserCourseInfo record = userCourseInfoService.selectById(id);
		if (null == record) {
			return "error";
		} else if (record.getStatus() != 0) {
			return "status error";
		}
		// 后台通过预约
		record.setStatus(2);
		record.setUpdateTime(new Date());
		record.setReason(reason);
		int flag = userCourseInfoService.createOrUpdate(record);
		if (flag < 1) {
			return "error";
		}
		UserCourse uc = userCourseService.selectById(record.getUserCourseId());
		if (null == uc) {
			return "status error";
		}
		if (uc.getPayStatus() == 2 && record.getPayType() == 0) {
			// 线上已付款的，原路退款
			if (uc.getTradeType().equals("ali_web") || uc.getTradeType().equals("ali_app") ) {
				// 支付宝退款
				aliPayService.refund(uc.getOutTradeNo(), uc.getPrice().toString(), "预约失败");
			} else {
				// 微信退款
				String priceStr = uc.getPrice().multiply(new BigDecimal(100)).toString();
				if (priceStr.contains(".")) {
		    		priceStr = priceStr.substring(0, priceStr.indexOf("."));
		    	}
				wxPayService.doRefund(uc.getOutTradeNo(), priceStr);
			}
			uc.setRefundStatus(2);
			userCourseService.createOrUpdate(uc);
		} else {
			// 线下付款，插入线下退款记录
		}
		// 自动退款
		// 库存回补
		DrivingSchoolLesson data = drivingSchoolService.selectLessonById(uc.getCourseId());
		drivingSchoolService.increaseCount(data);
		// TODO 通知
		userNoticeService.dealSchoolMessage2(2, uc);
        return  "success";
    }
}
