package com.migu.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.PrivateTutorService;
import com.migu.online.service.TeacherService;

/*教练*/
@Controller
@RequestMapping("/api/Teacher")
public class TeacherController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2319284785605999907L;
	private Logger log = LoggerFactory.getLogger(TeacherController.class);
	@Autowired
	private TeacherService teacherService;	
	@Autowired
	private CourseOnlineService courseOnlineService;
	@Autowired
	private PrivateTutorService privateTutorService;
	
	/* 获取教练列表 */
	@RequestMapping("/getPage/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getTeachers(@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			res.setData(teacherService.selectByPage(pageIndex, pageSize));
		} catch (Exception e) {
			log.error(e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取教练 */
	@RequestMapping("/detail/id/{id}")
	@ResponseBody
	public ResposeModel getTeacher(@PathVariable("id") Long id) {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(teacherService.selectDetailOne(id));
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取教练的线上课程列表 */
	@RequestMapping("/onlineCourse/tId/{tId}/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getCourseOnlines(@PathVariable("tId") Long tId,
			@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			res.setData(courseOnlineService.selectPageByTeacher(tId, pageIndex, pageSize));
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}

	
	/* 获取教练的线上课程列表 */
	@RequestMapping("/onlineCourseList")
	@ResponseBody
	public ResposeModel getCourseOnlines(@RequestParam(value="teacherId") Long teacherId) {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(courseOnlineService.selectListByTeacher(teacherId));
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/**
	 * 老师列表
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getTeacherList")
	@ResponseBody
	public ResposeModel getTeacherList() throws Exception {
		ResposeModel res = new ResposeModel();
		res.setData(teacherService.selectAll());
		return res;
	}
	
	/* 老师发布私教课程 */
	@RequestMapping("privateTutorList")
	@ResponseBody
	public ResposeModel privateTutorList(@RequestParam(value="teacherId") Long teacherId) {
		ResposeModel res = new ResposeModel();
		try {	
			// 老师发布私教课程			
			res.setData(privateTutorService.selectByTeacher(teacherId));
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}
		return res;
	}
	
	/* 私教推荐课程 */
	@RequestMapping("recommandTutorList")
	@ResponseBody
	public ResposeModel recommandTutorList() {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(privateTutorService.selectRecommandByPage(1, 10, null));
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}
		return res;
	}
}
