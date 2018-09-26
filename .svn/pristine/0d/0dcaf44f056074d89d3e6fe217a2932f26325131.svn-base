package com.migu.online.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.model.CourseOffline;
import com.migu.online.model.CourseOnline;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.User;
import com.migu.online.model.UserCourse;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.UserCourseService;

/*线下课程*/
@Controller
@RequestMapping("/user/course")
public class UserCourseController extends BaseController{
	
	private static final long serialVersionUID = -8218831504167197421L;

	private Logger log = LoggerFactory.getLogger(UserCourseController.class);

	@Autowired
	private UserCourseService userCourseService;	
	@Autowired
	private CourseOfflineService offlineCourseService;
	@Autowired
	private CourseOnlineService onlineCourseService;
		
	
	/* 报名平台线下课程 */
    @PostMapping("/applyOffCourse")
	@ResponseBody
	public ResposeModel applyOffCourse(@RequestParam("courseId") Long courseId) {
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}	
	    // 获取课程
		try {
			// 数据校验	
			CourseOffline course = offlineCourseService.selectById(courseId);
			if (courseId == null) {
				res.setStatus("0");
				res.setMsg("课程不存在");
				return res;
			}
			UserCourse uc = userCourseService.selectCourseByUser(courseId, user.getId(), UserCourseTypeEnum.offline.code);
			if (uc != null) {
				res.setStatus("0");
				if (uc.getPayStatus() == 0) {
					res.setMsg("已下单，请去完成支付");
				} else if (uc.getPayStatus() == 2) {
					res.setMsg("已购买成功");
				}
				res.setData(uc.getPayStatus());
				return res;
			}
			UserCourse data = new UserCourse();
			data.setCourseId(courseId);
			data.setUserId(user.getId());
			data.setCreateTime(new Date());
			data.setUpdateTime(new Date());
			data.setIsDelete(0);
			data.setPrice(course.getPrice());
			data.setCourseType(UserCourseTypeEnum.offline.code);
			data.setUserName(user.getMobile());
			data.setPayStatus(0);
			data.setRefundStatus(0);
			// 插入数据
			userCourseService.createOrUpdate(data);
			res.setMsg("报名成功");;
		} catch (Exception e) {
			log.info("applyOffCourse:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
    
    /* 购买平台线上课程 */
    @PostMapping("/buyOnlineCourse")
	@ResponseBody
	public ResposeModel buyOnlineCourse(@RequestParam("courseId") Long courseId) {
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}	
	    // 获取课程
		try {
			// 数据校验	
			CourseOnline course = onlineCourseService.selectById(courseId);
			if (courseId == null) {
				res.setStatus("0");
				res.setMsg("课程不存在");
				return res;
			}
			UserCourse uc = userCourseService.selectCourseByUser(courseId, user.getId(), UserCourseTypeEnum.online.code);
			if (uc != null) {
				res.setStatus("0");
				if (uc.getPayStatus() == 0) {
					res.setMsg("已下单，请去完成支付");
				} else if (uc.getPayStatus() == 2) {
					res.setMsg("已购买成功");
				}
				res.setData(uc.getPayStatus());
				return res;
			}
			UserCourse data = new UserCourse();
			data.setCourseId(courseId);
			data.setUserId(user.getId());
			data.setCreateTime(new Date());
			data.setUpdateTime(new Date());
			data.setIsDelete(0);
			data.setPrice(course.getPrice());
			data.setCourseType(UserCourseTypeEnum.online.code);
			data.setUserName(user.getMobile());
			data.setPayStatus(0);
			data.setRefundStatus(0);
			// 插入数据
			userCourseService.createOrUpdate(data);
			res.setMsg("报名成功");;
		} catch (Exception e) {
			log.info("applyOffCourse:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
    
}
