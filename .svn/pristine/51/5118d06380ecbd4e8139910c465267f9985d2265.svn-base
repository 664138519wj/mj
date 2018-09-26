package com.migu.online.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.model.PrivateTutor;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.UserCourse;
import com.migu.online.service.PrivateTutorService;
import com.migu.online.service.TeacherService;
import com.migu.online.service.UserCourseService;
import com.migu.online.vo.TeacherDetailVo;

/*用户答题*/
@Controller
@RequestMapping("/api/tutor")
public class PrivateTutorController extends BaseController{
	
	private static final long serialVersionUID = -8218831504167197421L;

	@Autowired
	private PrivateTutorService privateTutorService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserCourseService userCourseService;
		
	/**
	 * 私教
	 * @return
	 */
	@RequestMapping("privateTutor")
	@ResponseBody
	public ResposeModel privateTutor(@RequestParam(value = "teacherId", required = false) Long teacherId,
			@RequestParam(value = "kemu", required = false) Integer kemu, 
			@RequestParam(value = "payType", required = false) Integer payType, 
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ResposeModel res = new ResposeModel();
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = 12;
		}	
		try {
			Map<String, String> filters = new HashMap<String, String>();
			if (null != teacherId && teacherId > 0) {
				filters.put("teacherId", teacherId + "");
			}
			if (null != kemu && kemu > 0) {
				filters.put("kemu", kemu + "");
			}
			if (null != payType && payType > 0) {
				filters.put("payType", payType + "");
			}
			res.setData(privateTutorService.selectConditionsByPage(pageIndex, pageSize, filters));
			
		} catch (Exception e) {
			res.setStatus("0");
    		res.setCode("900009");
			res.setMsg("系统异常");
		}
		return res;
	}
	
	/**
	 * 私教detail
	 * @return
	 */
	@RequestMapping("privateTutorDetail")
	@ResponseBody
	public ResposeModel privateTutorDetail(@RequestParam(value = "id") Long id, @RequestParam(value = "userId", required = false) Long userId) {
		ResposeModel res = new ResposeModel();
		// 私教课程信息
		PrivateTutor tutor =  privateTutorService.selectById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("data", tutor);
		if (null != tutor) {
			try {
				// 教师信息
				TeacherDetailVo tData = teacherService.selectOne(tutor.getTeacherId());
				map.put("tData", tData);
				boolean isPay = false;
				// 是否已经购买
				if (null != userId && userId > 0) {
					// 已登录
					UserCourse uc = userCourseService.selectPayCourseByUser(id, userId, UserCourseTypeEnum.tutor.code);
					if (null != uc) {
						isPay = true;
					}
				} 
				map.put("isPay", isPay);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 平台推荐课程
		try {
			map.put("recommandList", privateTutorService.selectRecommandByPage(1, 5, id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setData(map);
		return res;
	}
}
