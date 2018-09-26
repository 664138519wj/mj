package com.migu.online.controller;

import java.util.Date;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.util.StringUtils;
import com.migu.online.common.Constants;
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.model.PracticeCard;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.User;
import com.migu.online.model.UserCourse;
import com.migu.online.service.PracticeCardService;
import com.migu.online.service.RedisService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserService;
import com.migu.online.utils.MD5Util;
import com.migu.online.vo.UserVo;

/**
 * Created by Liaopan on 2018/1/10.
 */
@Controller
@RequestMapping("/auth/app")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
	private RedisService redisService;
    @Autowired
	private UserCourseService userCourseService;	
    @Autowired
	private PracticeCardService practiceCardService;
      
    @RequestMapping("/login")
    @ResponseBody
    public ResposeModel appLogin(@Valid @RequestParam("username") String loginName, @RequestParam("password") String password
    		, @RequestParam("appNo") String appNo) {
    	ResposeModel res = new ResposeModel();
        logger.info("准备登陆用户 => {}", loginName);
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
        	res.setStatus("0");
			res.setMsg("请输入账号密码");
            return res;
        }
        // base64解密密码
		String decodePwd = Base64.decodeToString(password);
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, MD5Util.getMD5(decodePwd));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + loginName + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
            res.setStatus("0");
			res.setMsg("未知账户");
            return res;
        } catch (IncorrectCredentialsException ice) {
            logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
            res.setStatus("0");
			res.setMsg("密码不正确");
            return res;
        } catch (LockedAccountException lae) {
            logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
            res.setStatus("0");
			res.setMsg("账户已锁定");
            return res;
        } catch (ExcessiveAttemptsException eae) {
            logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
            res.setStatus("0");
			res.setMsg("用户名或密码错误次数过多");
            return res;
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            res.setStatus("0");
			res.setMsg("用户名或密码不正确");
            return res;
        } catch (Exception e) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            res.setStatus("0");
			res.setMsg("用户名或密码不正确");
            return res;
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            logger.info("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");              
            User user = userService.selectOneByMobile(loginName);
            if (null != user.getAppNo() && !appNo.equals(user.getAppNo())) {
            	// 静默用户，两次登录设备号不一致 更新session         
            	res.setMsg("静默用户，两次登录设备号不一致");
            	// 清除上次登录sessionId           	
            	redisService.del(Constants.SHIRO_SESSIONID + user.getSessionId());
            } else {
    			res.setMsg("登录成功");
            }
            user.setAppNo(appNo);
            user.setLastLoginTime(new Date());
            user.setSessionId(currentUser.getSession().getId().toString());
            userService.update(user);
            UserVo vo = new UserVo();
            vo.setId(user.getId());
            vo.setMobile(user.getMobile());
            vo.setAvatar(user.getAvatar());
            vo.setName(user.getName());
            vo.setSessionId(user.getSessionId());
             // 查询用户选项卡购买情况
            //付费判断
    		UserCourse pay = userCourseService.selectPayCourseByUser(1l, user.getId(), UserCourseTypeEnum.practice.code);
    		if (null == pay) {
    			vo.setIsBuyCard(0);
    		} else {
    			vo.setIsBuyCard(1);
    		}
    		PracticeCard card = practiceCardService.getOne(1L);
    		vo.setCardPrice(card.getPrice());
    		vo.setRealName(user.getRealName());
    		vo.setIdNo(user.getIdNo());
			res.setData(vo);
            return res;
        } else {
            token.clear();           
        }
        return res;
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResposeModel logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        ResposeModel res = new ResposeModel();
    	res.setStatus("1");
    	res.setCode("000000");
		res.setMsg("success");	
        return res;
    }
    
    @GetMapping("/unauthorized")
    @ResponseBody
    public ResposeModel unauthorized() {
    	ResposeModel res = new ResposeModel();
    	res.setStatus("0");
    	res.setCode("000001");
		res.setMsg("未获得授权，请先登录");		
        return res;
    }
}
