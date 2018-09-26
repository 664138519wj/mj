package com.migu.online.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.migu.online.controller.BaseController;
import com.migu.online.model.User;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.ShufflingService;
import com.migu.online.service.TeacherService;

/**
 * web 首页
 * @author fanyunlong
 *
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	private static final long serialVersionUID = 2931428145753112856L;

	private Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseOfflineService courseOfflineService;
	@Autowired
	private ShufflingService shufflingService;	
	@Autowired
	private CourseOnlineService courseOnlineService;

	/**
	 * 首页加载
	 * @return
	 */
	@GetMapping("/")
	public String index(ModelMap modelMap) {
		User user = super.getCurrentUser();
		modelMap.put("isLogin", user == null ? false:true);
		try {
			modelMap.put("offlineCourseList", courseOfflineService.selectHomePageAll());
			modelMap.put("shufflingList", shufflingService.selectAll());
			modelMap.put("teacherList", teacherService.selectHomePageAll());
			modelMap.put("onlineCourseList", courseOnlineService.selectHomePageAll());
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return "index";
	}
	
}
