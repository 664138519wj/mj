package com.migu.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.migu.online.service.DrivingPolicyService;
import com.migu.online.service.PlatformNoticeService;
import com.migu.online.service.TrafficLawService;

/**
 * Created by Liaopan on 2018/1/23.
 */
@Controller
@RequestMapping("/h5")
public class H5Controller {
	
	private Logger log = LoggerFactory.getLogger(H5Controller.class);

	
	@Autowired
	private TrafficLawService trafficLawService;	
	@Autowired
	private DrivingPolicyService drivingPolicyService;
	@Autowired
	private PlatformNoticeService platformNoticeService;
	
	/* 获取交通安全法律法规 */
	@RequestMapping("/law/{id}")
	public String getLawDetail(ModelMap modelMap, @PathVariable("id") Long id) {
		try {
			modelMap.put("data", trafficLawService.selectOne(id));
		} catch (Exception e) {
			log.error(e.toString());
		}
		return "h5/law-detail";
	}
	
	/* 获取交通安全法律法规 */
	@RequestMapping("/policy/{id}")
	public String getPolicyDetail(ModelMap modelMap, @PathVariable("id") Long id) {
		try {
			modelMap.put("data", drivingPolicyService.selectOne(id));
		} catch (Exception e) {
			log.error(e.toString());
		}
		return "h5/policy-detail";
	}
	
	/* 获取交通安全法律法规 */
	@RequestMapping("/notice/{id}")
	public String getNoticeDetail(ModelMap modelMap, @PathVariable("id") Long id) {
		try {
			modelMap.put("data", platformNoticeService.selectOne(id));
		} catch (Exception e) {
			log.error(e.toString());
		}
		return "h5/notice-detail";
	}
	
}
