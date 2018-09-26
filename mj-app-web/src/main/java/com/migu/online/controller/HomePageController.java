package com.migu.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.ShufflingService;
import com.migu.online.service.TeacherService;

@Controller
@RequestMapping("/api/HomePage")
public class HomePageController {
	private Logger log = LoggerFactory.getLogger(HomePageController.class);

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseOfflineService courseOfflineService;

	@Autowired
	private ShufflingService shufflingService;
	
	@Autowired
	private CourseOnlineService courseOnlineService;


	/* 获取线下培训课程 */
	@RequestMapping("/getCourseOfflines")
	@ResponseBody
	public ResposeModel getCourseOfflines() {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(courseOfflineService.selectHomePageAll());
		} catch (Exception e) {
			log.error("api-getCourseOfflines:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}

	/* 获取首页轮播 */
	@RequestMapping("/getShufflings")
	@ResponseBody
	public ResposeModel getShufflings() {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(shufflingService.selectAll());
		} catch (Exception e) {
			log.error("api-getShufflings:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}

	/* 获取首页轮播 */
	@RequestMapping("/getTeachers")
	@ResponseBody
	public ResposeModel getTeachers() {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(teacherService.selectHomePageAll());
		} catch (Exception e) {
			log.error("api-getTeachers:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取首页线上课程 */
	@RequestMapping("/getCourseOnlines")
	@ResponseBody
	public ResposeModel getHomePageCourseOnlines() {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(courseOnlineService.selectHomePageAll());
		} catch (Exception e) {
			log.error("api-getHomePageCourseOnlines:" + e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
}
