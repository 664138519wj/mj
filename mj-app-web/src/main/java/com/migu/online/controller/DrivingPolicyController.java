package com.migu.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.service.DrivingPolicyService;

/*驾考政策*/
@Controller
@RequestMapping("/api/DrivingPolicy")
public class DrivingPolicyController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 13500915515806515L;
	private Logger log = LoggerFactory.getLogger(DrivingPolicyController.class);
	@Autowired
	private DrivingPolicyService drivingPolicyService;

	/* 获取驾考政策 */
	@RequestMapping("/getDrivingPolicies/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getDrivingPolicies(@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			res.setData(drivingPolicyService.selectByPage(pageIndex, pageSize));
		} catch (Exception e) {
			log.info("getDrivingPolicies:"+e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取驾考政策 */
	@RequestMapping("/getDrivingPolicy/id/{id}")
	@ResponseBody
	public ResposeModel getDrivingPolicy(@PathVariable("id") Long id) {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(drivingPolicyService.selectOne(id));
		} catch (Exception e) {
			log.info("getDrivingPolicy:"+e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
}
