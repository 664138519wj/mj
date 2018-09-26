package com.migu.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.service.TrafficLawService;

/*交通法规*/
@Controller
@RequestMapping("/api/TrafficLaw")
public class TrafficLawController extends BaseController{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6476093954808729950L;

	private static final Logger log = LoggerFactory.getLogger(TrafficLawController.class);

	@Autowired
	private TrafficLawService trafficLawService;

	/* 获取交通安全法律法规 */
	@RequestMapping("/getTrafficLaws/pageIndex/{pageIndex}/pageSize/{pageSize}")
	@ResponseBody
	public ResposeModel getTrafficLaws(@PathVariable("pageIndex") Integer pageIndex,
			@PathVariable("pageSize") Integer pageSize) {
		ResposeModel res = new ResposeModel();
		try {
			if (null == pageIndex) {
				pageIndex = super.pageIndex;
			}
			if (null == pageSize) {
				pageSize = super.pageSize;
			}
			res.setData(trafficLawService.selectByPage(pageIndex, pageSize));
		} catch (Exception e) {
			log.error(e.toString());
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	
	/* 获取交通安全法律法规 */
	@RequestMapping("/getTrafficLaw/id/{id}")
	@ResponseBody
	public ResposeModel getTrafficLaw(@PathVariable("id") Long id) {
		ResposeModel res = new ResposeModel();
		try {
			res.setData(trafficLawService.selectOne(id));
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}

		return res;
	}
	

}
