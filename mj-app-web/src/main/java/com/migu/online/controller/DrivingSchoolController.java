package com.migu.online.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.User;
import com.migu.online.service.AppointmentManageService;
import com.migu.online.service.DrivingSchoolService;

/*线下课程*/
@Controller
@RequestMapping("/api/school")
public class DrivingSchoolController extends BaseController {

	private static final long serialVersionUID = -2474740602930776685L;

	private Logger log = LoggerFactory.getLogger(DrivingSchoolController.class);

	@Autowired
	private DrivingSchoolService drivingSchoolService;
	@Autowired
	private AppointmentManageService appointmentManageService;
	
	/* 选择驾校条件 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getBase")
	@ResponseBody
	public ResposeModel getBaseData() {
		ResposeModel res = new ResposeModel();
		try {
			List<List> baseList = new ArrayList<>();
			baseList.add(drivingSchoolService.selectAreaList());
			baseList.add(drivingSchoolService.selectLicenceList());
			res.setData(baseList);
		} catch (Exception e) {
			log.info("getBaseData:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}

	/* 按照添加查询驾校 */
	@RequestMapping("/query")
	@ResponseBody
	public ResposeModel query(@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "priceFrom", required = false) Integer priceFrom, @RequestParam(value = "priceTo", required = false) Integer priceTo,
			@RequestParam(value = "licence", required = false) String licence, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			res.setData(drivingSchoolService.selectConditionByPage(areaId, priceFrom, priceTo, licence, pageIndex,
					pageSize));
		} catch (Exception e) {
			log.info("query:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}

	/* 驾校详情 */
	@RequestMapping("/detail/id/{id}")
	@ResponseBody
	public ResposeModel getDetail(@PathVariable("id") Integer id) {
		ResposeModel res = new ResposeModel();
		try {
			Long userId = 0L;
			User user = super.getCurrentUser();
			if (null != user) {
				userId = user.getId();
			}
			res.setData(drivingSchoolService.selectOne(id, userId));
		} catch (Exception e) {
			log.info("getDetail:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 预约课程 驾校数据 */
	@RequestMapping("/appointment/getSchool")
	@ResponseBody
	public ResposeModel getAppointmentSchool() {
		ResposeModel res = new ResposeModel();
		try {			
			res.setData(drivingSchoolService.selectSchoolList());
		} catch (Exception e) {
			log.info("getAppointmentBase:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 预约课程 考场数据 */
	@RequestMapping("/appointment/getExamPlace")
	@ResponseBody
	public ResposeModel getAppointmentExamPlace() {
		ResposeModel res = new ResposeModel();
		try {			
			res.setData(drivingSchoolService.selectExamPlaceList());
		} catch (Exception e) {
			log.info("getAppointmentBase:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取课预约课程 */
    @PostMapping("/getAppointment")
	@ResponseBody
	public ResposeModel getAppointment(@RequestParam("licenceType") String licenceType, @RequestParam("kemu") Integer kemu
			, @RequestParam(name = "userId", required = false) Long userId) {
		ResposeModel res = new ResposeModel();	
	    // 获取课程
		try {
			// 数据校验
			if (StringUtils.isEmpty(licenceType)) {
				res.setStatus("0");
				res.setMsg("驾照类型不能为空");
				return res;
			} else if (null == kemu) {
				res.setStatus("0");
				res.setMsg("请选择驾校");
				return res;
			} 			
			// 获取
			res.setData(appointmentManageService.selectByCondition(kemu, licenceType, userId));
		} catch (Exception e) {
			log.info("getDetail:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
    
    /**
	 * 报名驾校
	 * @return
	 */	
	@RequestMapping("list")
	@ResponseBody
	public ResposeModel personSignUp(ModelMap modelMap, @RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "priceRange", required = false) String priceRange,
			@RequestParam(value = "schoolName", required = false) String schoolName, 
			@RequestParam(value = "schoolAddress", required = false) String schoolAddress,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {	
		ResposeModel res = new ResposeModel();	
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = 12;
		}
		try {
			Integer priceFrom = null, priceTo = null;
			if (!StringUtils.isEmpty(priceRange) && priceRange.contains("-")) {
				priceFrom = Integer.parseInt(priceRange.split("-")[0]);
				priceTo = Integer.parseInt(priceRange.split("-")[1]);
			}
			res.setData(drivingSchoolService.selectConditionByPage2(areaId, priceFrom, priceTo, schoolName, schoolAddress, pageIndex,
					pageSize));
		} catch (Exception e) {
			log.info("getDetail:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	
	
}
