package com.migu.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.service.CourseOfflineService;

/*线下课程*/
@Controller
@RequestMapping("/api/offline")
public class CourseOfflineController extends BaseController{

	private static final long serialVersionUID = -498907568044950499L;

	private Logger log = LoggerFactory.getLogger(CourseOfflineController.class);

	@Autowired
	private CourseOfflineService courseOfflineService;

	/* 获取线下课程 */
	@RequestMapping("/getPage/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getCourseOfflines(@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		ResposeModel res = new ResposeModel();		
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			res.setData(courseOfflineService.selectByPage(pageIndex, pageSize));
		} catch (Exception e) {
			log.info("getCourseOfflines:"+e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取线下课程 */
	@RequestMapping("/detail/id/{id}/userId/{userId}")
	@ResponseBody
	public ResposeModel getCourseOffline(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
		ResposeModel res = new ResposeModel();
		if (id <= 0) {
			return null;
		}		
		try {
			res.setData(courseOfflineService.selectOneByUser(id, userId));
		} catch (Exception e) {
			log.info("getCourseOffline:"+e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}

 
}
