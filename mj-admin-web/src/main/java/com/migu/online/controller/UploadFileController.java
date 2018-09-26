package com.migu.online.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/upload/")
public class UploadFileController  {
	
//	@Value("${image.upload.path}")
//	private String imageUploadPath;
	
	@Value("${video.upload.path}")
	private String videoUploadPath;

	@PostMapping("/doUploadImg")
	@ResponseBody
	public Map<String, String> doUploadImg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> result = new HashMap<>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(new Date());
		//String path2 = this.getClass().getResource("").getPath();
		String imageDir = request.getServletContext().getRealPath("/") +"upload/image/" + ymd + "/";
		// 创建文件夹
		File file = new File(imageDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String path = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件名
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
            String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
            String newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
			File uploadFile = new File(imageDir + newFileName);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				path = "/upload/image/" + ymd + "/" + newFileName;			
			} catch (IOException e) {
				e.printStackTrace();
				result.put("code", "100001");
				return result;
			}
		}
		result.put("code", "0");
		result.put("path", path);
		return result;
	}
	
	@PostMapping("/doUploadVideo")
	@ResponseBody
	public Map<String, String> doUploadVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> result = new HashMap<>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(new Date());
		String videoDir = videoUploadPath + "/" + ymd + "/";
		// 创建文件夹
		File file = new File(videoDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String path = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件名
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
            String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
            String newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
			File uploadFile = new File(videoDir + newFileName);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				path = "/upload/video/" + ymd + "/" + newFileName;			
			} catch (IOException e) {
				e.printStackTrace();
				result.put("code", "100001");
				return result;
			}
		}
		result.put("code", "0");
		result.put("path", path);
		return result;
	}
}
