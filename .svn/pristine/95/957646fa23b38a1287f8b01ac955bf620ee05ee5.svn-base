package com.migu.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.online.mapper.UserVideoRecordMapper;
import com.migu.online.model.UserVideoRecord;

@Service
public class UserVideoRecordService {

	@Autowired
	private UserVideoRecordMapper userVideoRecordMapper;

	/**
	 * 添加：
	 * 可自动生成Id,
	 * @param demo
	 * @return
	 */
	public UserVideoRecord insert(UserVideoRecord model) {
		userVideoRecordMapper.insert(model); 
		return model;
	}
	
}
