//package com.migu.online.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.migu.online.service.ExamTkTkzpService;
//
///**
// * 题库图片处理
// * @author fanyunlong
// *
// */
//@Controller
//@RequestMapping("/upload/")
//public class ImageMangeController  {
//	
//	@Autowired
//	private ExamTkTkzpService examTkTkzpService;
//
//	@RequestMapping("/deGenerateImage")
//	@ResponseBody
//	public Map<String, String> deGenerateImage(HttpServletRequest request, HttpServletResponse response) {
//		Map<String, String> result = new HashMap<>();
//		try {
//			examTkTkzpService.generateTkImage();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		result.put("code", "0");
//		return result;
//	}
//
//}
