package com.migu.online.controller;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.migu.online.common.Constants;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.User;
import com.migu.online.service.MessageSendService;
import com.migu.online.service.RedisService;
import com.migu.online.service.UserService;
import com.migu.online.utils.MD5Util;
import com.migu.online.vo.UserVo;

@Controller
@RequestMapping("/api/register")
public class RegisterController {
	private Logger log = LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	private MessageSendService smsService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private UserService userService;
	

	/**
	 * 注册发送验证码
	 * @param phoneNum
	 * @param type 1 注册发送 2找回密码发送
	 * @return
	 */	
	@PostMapping(value = "/sendCode")
	@ResponseBody
	public ResposeModel sendCode(@Valid @RequestParam("phoneNum") String phoneNum, @Valid @RequestParam("type") Integer type) {
		ResposeModel res = new ResposeModel();		
		try {
			// 校验手机号
			Boolean ismobile = smsService.isPhone(phoneNum);
			if (!ismobile) {
				res.setStatus("0");
				res.setMsg("手机号格式不正确");
				return res;
			}
			// 判断手机号是否存在
			User user = userService.selectOneByMobile(phoneNum);
			if (type == 1 && null != user) {
				// 注册流程
				res.setStatus("0");
				res.setMsg("手机号码已存在");
				return res;
			} else if (type == 2 && null == user) {
				// 找回密码流程
				res.setStatus("0");
				res.setMsg("手机号码未注册");
				return res;
			}			
			res = smsService.sendCode(phoneNum);			
		} catch (Exception e) {
			log.error(e.toString());
			res.setStatus("0");
			res.setMsg("短信发送出错");
		}

		return res;
	}

	/**
	 * 验证验证码
	 * @param phoneNum
	 * @param code
	 * @param type 1:注册校验 2：找回密码校验
	 * @return
	 */
	@PostMapping(value = "/checkCode")
	@ResponseBody
	public ResposeModel checkCode(@Valid @RequestParam("phoneNum") String phoneNum,
			@Valid @RequestParam("code") String code, @RequestParam("type") Integer type) {
		ResposeModel res = new ResposeModel();
		try {
			
			String cacheCode = redisService.getString(Constants.SMS_PHONE_CODE + phoneNum);
			if (StringUtils.isEmpty(cacheCode)) {
				res.setStatus("0");
				res.setMsg("验证码已过期");
				return res;
			} else if (!cacheCode.equals(code)) {
				res.setStatus("0");
				res.setMsg("验证码不正确");
				return res;
			}
			// 手机号码通过校验
			redisService.set(Constants.SMS_PHONE_IS_PASS + phoneNum, type, 60 * 5);
			res.setStatus("1");
			res.setMsg("成功");
			res.setData(phoneNum);
			return res;			
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("出错");
		}
		return res;
	}
	
	/*账号密码注册*/
	@PostMapping(value = "/addPwd")
	@ResponseBody
	public ResposeModel addPwd(@Valid @RequestParam("phoneNum") String phoneNum,
			@Valid @RequestParam("pwd") String pwd) {
		ResposeModel res = new ResposeModel();
		try {
			if (StringUtils.isEmpty(phoneNum) || StringUtils.isEmpty(pwd)) {
				res.setStatus("0");
				res.setMsg("参数不能为空");
				return res;
			}
			Integer isPass = (Integer)redisService.get(Constants.SMS_PHONE_IS_PASS + phoneNum);
			isPass = 1;
			if (null != isPass && isPass == 1) {
				// 成功注册
				User user = new User();
				user.setMobile(phoneNum);
				// base64解密密码
				String decodePwd = Base64.decodeToString(pwd);
				user.setPwd(MD5Util.getMD5(decodePwd));
				user.setIsDelete(0);
				user.setCreateTime(new Date());
				user.setUpdateTime(new Date());
				user.setAvatar(Constants.USER_DEFAULT_AVATAR);
				user = userService.insert(user);
				res.setMsg("注册成功");
				UserVo vo = new UserVo();
				BeanUtils.copyProperties(vo, user);
				res.setData(vo);
				return res;
			} else  {
				res.setStatus("0");
				res.setMsg("手机号码未经过验证");
				return res;
			}	
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("手机号码已存在");
		}
		return res;
	}
	
	/*修改密码*/
	@PostMapping(value = "/updatePwd")
	@ResponseBody
	public ResposeModel updatePwd(@Valid @RequestParam("phoneNum") String phoneNum,
			@Valid @RequestParam("pwd") String pwd) {
		ResposeModel res = new ResposeModel();
		if (StringUtils.isEmpty(phoneNum) || StringUtils.isEmpty(pwd)) {
			res.setStatus("0");
			res.setMsg("参数不能为空");
			return res;
		}
		try {
			
			Integer isPass = (Integer)redisService.get(Constants.SMS_PHONE_IS_PASS + phoneNum);
			if (null != isPass && isPass == 2) {
				// 成功注册
				User user = userService.selectOneByMobile(phoneNum);
				if (null == user) {
					res.setStatus("0");
					res.setMsg("手机号码不存在");
					return res;
				}
				// base64解密密码
				String decodePwd = Base64.decodeToString(pwd);
				user.setPwd(MD5Util.getMD5(decodePwd));
				user.setUpdateTime(new Date());
				userService.update(user);
				res.setMsg("密码修改成功");
				return res;
			} else  {
				res.setStatus("0");
				res.setMsg("手机号码未经过验证");
				return res;
			}	
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("密码更新失败");
		}
		return res;
	}

}
