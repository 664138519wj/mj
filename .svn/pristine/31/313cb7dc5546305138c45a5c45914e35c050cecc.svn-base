//package com.migu.online.controller;
//
//import com.migu.online.model.User;
//import com.migu.online.model.ResposeModel;
//import com.migu.online.service.CourseOnlineService;
//import com.migu.online.service.UserService;
//
//import java.util.regex.Pattern;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///*用户*/
//@Controller
//@RequestMapping("/api/Customer")
//public class CustomerController {
//	private Logger log = LoggerFactory.getLogger(CustomerController.class);
//
//	@Autowired
//	private UserService customerService;
//	 
//	/* 注册 */
//	@PostMapping("/register")
//	@ResponseBody
//	public ResposeModel register(@RequestBody User model) {
//		ResposeModel res = new ResposeModel();
//		try {
//			String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//			Boolean ismobile = Pattern.matches(REGEX_MOBILE, model.getMobile());
//			if (!ismobile) {
//				res.setStatus("0");
//				res.setMsg("手机号格式不正确");
//				return res;
//			}
//			
//			if (!(model.getPwd().equals(model.getPwdagain()))) {
//				res.setStatus("0");
//				res.setMsg("两次密码不一致");
//			} else {
//				User cus = customerService.selectOneByMobile(model.getMobile());
//				if (cus != null) {
//					res.setStatus("0");
//					res.setMsg("此手机号已经注册");
//					return res;
//				}
//				
//				customerService.insert(model);
//			}
//		} catch (Exception e) {
//			res.setStatus("0");
//			res.setMsg("出错");
//		}
//
//		return res;
//	}
//
//	/* 登录 */
//	@PostMapping("/login")
//	@ResponseBody
//	public ResposeModel login(@RequestBody User model) {
//		ResposeModel res = new ResposeModel();
//		try {
//			String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//			Boolean ismobile = Pattern.matches(REGEX_MOBILE, model.getMobile());
//			if (!ismobile) {
//				res.setStatus("0");
//				res.setMsg("手机号格式不正确");
//				return res;
//			}
//
//			User cus = customerService.selectOneByMobile(model.getMobile());
//
//			if (cus == null) {
//				res.setStatus("0");
//				res.setMsg("该手机号不存在");
//			} else if (!(cus.getPwd().equals(model.getPwd()))) {
//				res.setStatus("0");
//				res.setMsg("密码不正确");
//			}
//		} catch (Exception e) {
//			res.setStatus("0");
//			res.setMsg("出错");
//		}
//
//		return res;
//	}
//
//	/*用户购买线上课程列表 */
//	@RequestMapping("/buyCourseOnline/customerId/{customerId}/pageIndex/{pageIndex}/pageSize/{pageSize}")
//	@ResponseBody
//	public ResposeModel buyCourseOnline(
//			@PathVariable("customerId") Integer customerId,
//			@PathVariable("pageIndex") Integer pageIndex,
//			@PathVariable("pageSize") Integer pageSize) {
//		ResposeModel res = new ResposeModel();
//		try {
//			res.setData(customerService.getMyCourseOnlines(customerId));
//		} catch (Exception e) {
//			res.setStatus("0");
//			res.setMsg("查询出错");
//		}
//
//		return res;
//	}
//	
//}
