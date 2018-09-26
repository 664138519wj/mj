package com.migu.online.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.model.ResposeModel;
import com.migu.online.model.User;
import com.migu.online.model.UserCourseVip;
import com.migu.online.model.VipCourse;
import com.migu.online.param.UserCourseVipParam;
import com.migu.online.param.VipCourseParam;
import com.migu.online.service.UserCourseVipService;
import com.migu.online.service.VipCourseService;
import com.migu.online.utils.DateUtil;

/*用户答题*/
@Controller
@RequestMapping("/api/vip")
public class VipController extends BaseController{
	
	private static final long serialVersionUID = -8218831504167197421L;

	@Autowired
	private VipCourseService vipCourseService;
	@Autowired
	private UserCourseVipService userCourseVipService;
	
 
	/**
	 * vip课程
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getList")
	@ResponseBody
	public ResposeModel getList() throws Exception {
		ResposeModel res = new ResposeModel();
		res.setData(vipCourseService.selectAll());
		return res;
	}
	
    /**
	 * vip课程
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("chooseVipCourse")
	@ResponseBody
	public ResposeModel chooseVipCourse(@RequestParam(value = "course") String course) throws Exception {
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}	
		if (StringUtils.isEmpty(course)) {
			res.setStatus("0");
			res.setMsg("param error");
			return res;
		}
		if (course.contains("5") && course.length() > 1) {
			res.setStatus("0");
			res.setMsg("param error");
			return res;
		}
		List<VipCourse> vipCourseList = vipCourseService.selectAll();
		List<VipCourse> chooseList = new ArrayList<VipCourse>();
		BigDecimal totalPrice = new BigDecimal(0);
		String kemus = "";
		for (VipCourse data : vipCourseList) {
			if (course.contains(data.getKemu() + "")) {
				chooseList.add(data);
				totalPrice = totalPrice.add(data.getPrice());
				kemus += data.getId() + ",";
			}
		}
	
		if (kemus.length() > 1) {
			kemus = kemus.substring(0, kemus.length() - 1);
		}
		VipCourseParam param = new VipCourseParam();
		param.setcList(chooseList);
		param.settPrice(totalPrice);
		param.setNumber(chooseList.size());
		param.setKemus(kemus);
		param.setBeginTimeStr(DateUtil.getCurDate());
		res.setData(param);
		return res;
	}
	
	/**
	 * 报名vip课程
	 * @param kemus 所选科目	
	 * @param payType 
	 * @param ip
	 * @return
	 */
	@RequestMapping("/vipCoureOrder")
	@ResponseBody
	public ResposeModel vipCoureOrder(@RequestParam("kemus") String kemus, 
			@RequestParam("beginTimeStr") String beginTimeStr) {	
		ResposeModel res = new ResposeModel();
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}
		// 相关课程查询
    	if (StringUtils.isEmpty(kemus) || StringUtils.isEmpty(beginTimeStr)) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("参数异常");			
			return res;
    	} 	
	    Date beginTime = DateUtil.getDate(beginTimeStr, DateUtil.DATAFORMAT_STR);		
    	// 相关课程查询
    	if (StringUtils.isEmpty(kemus)) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("相关课程不存在，或已下架");
			return res;
    	}
		Long userId = user.getId();
		// 已购买vip课程，无法重复购买
		List<UserCourseVip> uCourse = userCourseVipService.selectPayCourseByUserId(userId);
    	if (null != uCourse && uCourse.size() > 0) {
    		// 已购买，返回
    		res.setStatus("0");
    		res.setCode("900001");
			res.setMsg("您已购买成功，无法重复购买");
			return res;
    	} 
	    
    	BigDecimal totalPrice = new BigDecimal(0);
    	List<VipCourse> vipList =  vipCourseService.selectAll();
    	for (VipCourse data: vipList) {
    	  if (kemus.contains(data.getId() + "")) {
    		  // 包含该科目 计算价格
    		  totalPrice = totalPrice.add(data.getPrice());
    	  }	
    	}
    	if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
    		// 课程不存在
    		res.setStatus("0");
    		res.setCode("900002");
			res.setMsg("您选择的课程价格有误，请联系管理员");
			return res;
    	}   
    	// 创建user vip course
    	UserCourseVip vipCourse = new UserCourseVip();
    	vipCourse.setKemus(kemus);
    	vipCourse.setIsDelete(0);
    	vipCourse.setPayStatus(0);
    	vipCourse.setUserCourseId(0L);
    	vipCourse.setMobile(user.getMobile());
    	vipCourse.setUserId(userId);
    	vipCourse.setPrice(totalPrice);
    	vipCourse.setBeginTime(beginTime);
    	vipCourse.setCreateTime(new Date());
    	vipCourse.setUpdateTime(new Date());
    	int flag = userCourseVipService.createOrUpdate(vipCourse);
    	if (flag > 0) {
    		res.setMsg("下单成功");
    		UserCourseVipParam param = new UserCourseVipParam();
    		param.setId(vipCourse.getId());
    		param.setBeginTime(vipCourse.getBeginTime());
    		param.setPrice(vipCourse.getPrice());
    		param.setUserId(vipCourse.getUserId());
    	    res.setData(param);
    		return res;
    	} else {
    		res.setStatus("0");
    		res.setCode("900009");
			res.setMsg("系统异常");
			return res;
    	}
	}
}
