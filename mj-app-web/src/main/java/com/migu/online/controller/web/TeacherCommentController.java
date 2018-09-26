package com.migu.online.controller.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.migu.online.common.Constants;
import com.migu.online.controller.BaseController;
import com.migu.online.model.Teacher;
import com.migu.online.model.TeacherComment;
import com.migu.online.model.User;
import com.migu.online.service.TeacherCommentService;
import com.migu.online.service.TeacherService;
import com.migu.online.utils.FileUtils;

/*老师评论*/
@Controller
@RequestMapping("/home")
public class TeacherCommentController extends BaseController {

	private static final long serialVersionUID = 6797165114062940759L;

	@Autowired
	private TeacherCommentService teacherCommentService;
	@Autowired
	private TeacherService teacherService;

	/* 获取我的线下课程 */
	@PostMapping("/teacherComment")
	public String submitComment(@RequestParam("comment") String comment, @RequestParam("teacherId") Long teacherId,
			@RequestParam("score") Integer score,
			@RequestParam(value = "upload_image_1", required = false) String upload_image_1,
			@RequestParam(value = "upload_image_2", required = false) String upload_image_2,
			@RequestParam(value = "upload_image_3", required = false) String upload_image_3,
			@RequestParam(value = "upload_image_4", required = false) String upload_image_4,
			@RequestParam(value = "upload_image_5", required = false) String upload_image_5) {
		if (null == teacherId) {
			return "error";
		}
		User user = super.getCurrentUser();
		if (null == user) {
			return "redirect:/home/auth/toLogin?redirectUrl=/home/platform_teacher_detail?id=" + teacherId;
		}
		try {
			// 
			Teacher teacher = teacherService.selectById(teacherId);
			if (null == teacher) {
				return "error";
			}
			// check user is buy vip coures
			if(!teacherCommentService.checkUserIsBuyVipCourse(teacherId, user.getId())) {
				// 未购买，无法评论
				return "redirect:/home/platform_teacher_detail?id=" + teacherId + "&duplicate=1";
			}
			
			List<TeacherComment> list = teacherCommentService.selectByUserIdAndTeacherId(teacherId, user.getId());

			if (null != list && list.size() > 0) {
				// 已评论过
				return "redirect:/home/platform_teacher_detail?id=" + teacherId + "&duplicate=2";
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
		return "redirect:/home/platform_teacher_detail?id=" + teacherId + "&duplicate=0";
	}

}
