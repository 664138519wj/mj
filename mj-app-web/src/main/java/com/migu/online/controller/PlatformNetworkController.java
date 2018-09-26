package com.migu.online.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.PlatformNetworkService;


/*平台网点*/
@Controller
@RequestMapping("/api/plat")
public class PlatformNetworkController extends BaseController{
	
	private static final long serialVersionUID = 2701008600562806397L;
	
	private Logger log = LoggerFactory.getLogger(PlatformNetworkController.class);
	
	@Autowired
	private PlatformNetworkService platformNetworkService;
	@Autowired
	private CourseOfflineService courseOfflineService;

	/*平台列表*/
	@RequestMapping("/getPage/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getPlatformServiceNetworks(@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			res.setData(platformNetworkService.selectByPage(pageIndex, pageSize));
		} catch (Exception e) {
		    log.error(e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 详情 */
	@RequestMapping("/detail/id/{id}")
	@ResponseBody
	public ResposeModel getPlatformServiceNetwork(@PathVariable("id") Long id) {
		if (id <= 0) {
			return null;
		}
		ResposeModel res = new ResposeModel();
		try {
			res.setData(platformNetworkService.selectOne(id));
		} catch (Exception e) {
		    log.error(e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}
		return res;
	}
	
	/* 获取平台网点的线下课程 */
	@RequestMapping("/offline/platId/{platId}/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getPlatformServiceNetworkCourse(
			@PathVariable("platId") Long platId,
			@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		if (platId <= 0) {
			return null;
		}
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		ResposeModel res = new ResposeModel();
		try {
			res.setData(courseOfflineService.selectPlatCourseByPage(pageIndex, pageSize, platId));
		} catch (Exception e) {
		    log.error(e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}
		return res;
	}
	
	/**
	 * 网点分布
	 * 
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public ResposeModel platformInfo(ModelMap modelMap, @RequestParam(value = "areaId", required = false) Long areaId,
			@RequestParam(value = "platName", required = false) String platName,
			@RequestParam(value = "teacherId", required = false) Long teacherId,
			@RequestParam(value = "kemu", required = false) Integer kemu,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			Map<String, Object> filters = new HashMap<>();
			filters.put("areaId", areaId);
			filters.put("platName", platName);
			filters.put("teacherId", teacherId);
			filters.put("kemu", kemu);
			filters.put("address", address);
			filters.put("startTime", startTime);
			res.setData(platformNetworkService.selectWebConditionByPage(filters, pageIndex, pageSize));

		} catch (Exception e) {
			log.error(e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}
		return res;
	}

}
