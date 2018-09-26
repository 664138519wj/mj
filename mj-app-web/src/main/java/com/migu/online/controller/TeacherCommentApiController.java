package com.migu.online.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.common.Constants;
import com.migu.online.model.ResposeModel;
import com.migu.online.model.Teacher;
import com.migu.online.model.TeacherComment;
import com.migu.online.model.User;
import com.migu.online.service.TeacherCommentService;
import com.migu.online.service.TeacherService;
import com.migu.online.utils.FileUtils;

/*老师评论*/
@Controller
@RequestMapping("/api/comment")
public class TeacherCommentApiController extends BaseController {

	private static final long serialVersionUID = 6797165114062940759L;

	@Autowired
	private TeacherCommentService teacherCommentService;
	@Autowired
	private TeacherService teacherService;

	/* 获取我的线下课程 */
	@PostMapping("/submit")
	@ResponseBody
	public ResposeModel submitComment(@RequestParam("comment") String comment, @RequestParam("teacherId") Long teacherId,
			@RequestParam("score") Integer score,
			@RequestParam(value = "upload_image_1", required = false) String upload_image_1,
			@RequestParam(value = "upload_image_2", required = false) String upload_image_2,
			@RequestParam(value = "upload_image_3", required = false) String upload_image_3,
			@RequestParam(value = "upload_image_4", required = false) String upload_image_4,
			@RequestParam(value = "upload_image_5", required = false) String upload_image_5) {
		ResposeModel res = new ResposeModel();	
		if (null == teacherId) {
			res.setStatus("0");
			res.setMsg("参数异常");
			return res;
		}
		User user = super.getCurrentUser();
		if (null == user) {
			res.setStatus("0");
			res.setMsg("用户未登录");
			return res;
		}
		try {
			// 
			Teacher teacher = teacherService.selectById(teacherId);
			if (null == teacher) {
				res.setStatus("0");
				res.setMsg("参数异常");
				return res;
			}
			// check user is buy vip coures
			if(!teacherCommentService.checkUserIsBuyVipCourse(teacherId, user.getId())) {
				// 未购买，无法评论
				res.setStatus("0");
				res.setCode("E10000001");
				res.setMsg("您未购买，无法评论");
				return res;
			}
			
			List<TeacherComment> list = teacherCommentService.selectByUserIdAndTeacherId(teacherId, user.getId());

			if (null != list && list.size() > 0) {
				// 未购买，无法评论
				res.setStatus("0");
				res.setCode("E10000002");
				res.setMsg("已评论，无法重复评论");
				return res;
			}

			// 文件生成		
			String fileDir = FileUtils.generateFileDir(Constants.UPLOAD_FILE_DIR, user.getId());
			TeacherComment model = new TeacherComment();
			if (StringUtils.isNotEmpty(upload_image_1)) {
				String updateImage1 = fileDir + "/updateImage1.png";			
				FileUtils.decoderBase64File(upload_image_1, updateImage1);			
				model.setImageUrl1(FileUtils.getImageUrl(updateImage1));
			}
			if (StringUtils.isNotEmpty(upload_image_2)) {
				String updateImage2 = fileDir + "/updateImage2.png";			
				FileUtils.decoderBase64File(upload_image_2, updateImage2);			
				model.setImageUrl2(FileUtils.getImageUrl(updateImage2));
			}
			if (StringUtils.isNotEmpty(upload_image_3)) {
				String updateImage3 = fileDir + "/updateImage3.png";			
				FileUtils.decoderBase64File(upload_image_3, updateImage3);			
				model.setImageUrl3(FileUtils.getImageUrl(updateImage3));
			}
			if (StringUtils.isNotEmpty(upload_image_4)) {
				String updateImage4 = fileDir + "/updateImage4.png";			
				FileUtils.decoderBase64File(upload_image_4, updateImage4);			
				model.setImageUrl4(FileUtils.getImageUrl(updateImage4));
			}
			if (StringUtils.isNotEmpty(upload_image_5)) {
				String updateImage5 = fileDir + "/updateImage5.png";			
				FileUtils.decoderBase64File(upload_image_5, updateImage5);			
				model.setImageUrl5(FileUtils.getImageUrl(updateImage5));
			}
					
			model.setContent(comment);
			model.setScore(score);
			model.setTeacherId(teacherId);
			model.setUserId(user.getId());
			model.setIsDelete(0);
			model.setUpdateTime(new Date());
			model.setCreateTime(new Date());
			int flag = teacherCommentService.createOrUpdate(model);
			if (flag >= 1) {
				// 计算老师分数
				teacherCommentService.calculateTeacherScore(teacherId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		res.setStatus("1");
		res.setMsg("评论成功");
		return res;
	}

	
	/* 获取教练 */
	@RequestMapping("list")
	@ResponseBody
	public ResposeModel platformTeacherDetail(@RequestParam(value="teacherId") Long teacherId,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		ResposeModel res = new ResposeModel();	
		if (null == pageIndex) {
			pageIndex = super.pageIndex;
		}
		if (null == pageSize) {
			pageSize = super.pageSize;
		}
		try {
			// comment
			res.setData(teacherCommentService.selectByPage(teacherId, pageIndex, pageSize));		
		} catch (Exception e) {
			res.setStatus("0");
			res.setMsg("获取数据出错");
		}
		return res;
	}
}
