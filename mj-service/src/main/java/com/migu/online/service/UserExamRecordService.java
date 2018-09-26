package com.migu.online.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.migu.online.mapper.UserExamRecordMapper;
import com.migu.online.model.UserExamRecord;
import com.migu.online.utils.DateUtil;
import com.migu.online.vo.ExamTkAnsVo;
import com.migu.online.vo.UserExamRecordVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserExamRecordService {

	@Autowired
	private UserExamRecordMapper userExamRecordMapper;

	    
	/**
	 * query
	 */
	public UserExamRecord selectById(Long examId) {
		return userExamRecordMapper.selectByPrimaryKey(examId);
	}
	
	/**
	 * 查找用户模拟答题情况
	 */
	public List<UserExamRecordVo> selectByUserId(Long userId, Integer type) {
		List<UserExamRecordVo> result = new ArrayList<>();
		// 单表自定义查询
		Example example = new Example(UserExamRecord.class);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("user_id = ", userId);
		if (null != type) {
			criteria.andCondition("type = ", type);
		}
		example.and(criteria);
		example.setOrderByClause("update_time desc");
		List<UserExamRecord> pageList = userExamRecordMapper.selectByExample(example);
		for (UserExamRecord data: pageList) {
			UserExamRecordVo vo = new UserExamRecordVo();
			BeanUtils.copyProperties(data, vo);
			vo.setCreateTimeStr(DateUtil.dateToDateString(data.getCreateTime(), DateUtil.DATATIMEF_STR));
			// 错误率计算 用户回答过的总数计算
			if (StringUtils.isNotEmpty(data.getExamMap())) {
				List<ExamTkAnsVo> ansList = JSON.parseArray(data.getExamMap(), ExamTkAnsVo.class);
				int errorCount = 0;
				int totalCount = 0;
				for (ExamTkAnsVo ansVo: ansList) {
					if (null != ansVo && ansVo.getIsCorrect() > 0) {
						totalCount ++;
						// 错题
						if (ansVo.getIsCorrect() == 2) {
							errorCount++;
						} 
					}
				}
				if (errorCount > 0) {
					vo.setErrorRate(errorCount * 100/totalCount);
				} else {
					vo.setErrorRate(0);
				}
			} else {
				vo.setErrorRate(0);
			}
			int licType = data.getLicType();
			String licTypeStr = "";
			if (licType == 1) {
				licTypeStr = "A、B";
			} else if (licType == 2) {
				licTypeStr = "C1、C2、C3";
			} else if (licType == 3) {
				licTypeStr = "D、E、F";
			}
			vo.setLicTypeStr(licTypeStr);
			vo.setExamId(data.getId());
			vo.setType(data.getType());
			result.add(vo);
		}
		return result;
	}
	
	/**
	 * 查找自由考试用户是否当天有记录
	 * 如果没有，插入一条
	 */
	public UserExamRecord createFreeRecord(Long userId, Integer kemu, Integer licType, String userName, ExamTkAnsVo vo, Integer language) {
		UserExamRecord record;
		Example example = new Example(UserExamRecord.class);
		String nowDateStr = DateUtil.getCurDate();
		Date todayBegin = DateUtil.getDate(nowDateStr + " 00:00:00", DateUtil.DATATIMEF_STR);
		Date todayEnd = DateUtil.getDate(nowDateStr + " 59:59:59", DateUtil.DATATIMEF_STR);
		example.createCriteria().andCondition("user_id = ", userId).andCondition("kemu = ", kemu).andCondition("type = 1")
		.andBetween("createTime", todayBegin, todayEnd);
		example.setOrderByClause("create_time desc");
		List<UserExamRecord> pageList = userExamRecordMapper.selectByExample(example);
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();

		if (null != pageList && pageList.size() > 0) {
			record = pageList.get(0);
			// 跟新答题记录
			if (StringUtils.isNotEmpty(record.getExamMap())) {
				examAnsList = JSON.parseArray(record.getExamMap(), ExamTkAnsVo.class);				
			}
			if (null != vo) {
				examAnsList.add(vo);
				vo.setIndex(examAnsList.size());
				// 得分计算
				int score = 0;
				for (ExamTkAnsVo data: examAnsList) {
					if (data.getIsCorrect() == 1) {
						score++;
					}
				}
				record.setScore(score);
				record.setExamMap(JSON.toJSONString(examAnsList));
			}			
			record.setUpdateTime(new Date());
			record.setCount(examAnsList.size());			
			userExamRecordMapper.updateByPrimaryKeySelective(record);
		} else {
			// 插入一条心的记录
			record = new UserExamRecord();
			if (null != vo) {
				vo.setIndex(1);
				examAnsList.add(vo);
				record.setExamMap(JSON.toJSONString(examAnsList));
				record.setScore(vo.getIsCorrect() == 1 ? 1:0);
			} else {
				record.setScore(0);
			}
			record.setCount(1);
			record.setUserId(userId);
			record.setUserName(userName);
			record.setCreateTime(new Date());
			record.setUpdateTime(new Date());
			record.setStatus(0);
			record.setKemu(kemu);
			record.setType(1);
			record.setLicType(licType);
			record.setLanguage(language);
			userExamRecordMapper.insert(record);			
		}
		return record;
	}
	
	
}
