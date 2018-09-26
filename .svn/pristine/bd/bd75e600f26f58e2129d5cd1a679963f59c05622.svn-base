package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.common.OnlineTypeEnum;
import com.migu.online.mapper.UserAppointmentMapper;
import com.migu.online.model.UserAppointment;
import com.migu.online.utils.DateUtil;
import com.migu.online.vo.UserAppointmentScoreVo;
import com.migu.online.vo.UserAppointmentVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserAppointmentService {

    @Autowired
    private UserAppointmentMapper userAppointmentMapper;
      
    /**
     * 分页查询 用户已预约考试
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<UserAppointmentVo> selectByPage(Integer pageIndex,Integer pageSize, Long userId) throws Exception{
    	List<UserAppointmentVo> result = new ArrayList<UserAppointmentVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(UserAppointment.class);
        example.createCriteria().andCondition("is_delete = 0").andCondition("user_id = ", userId);
        example.setOrderByClause("update_time desc");
        List<UserAppointment> pageList = userAppointmentMapper.selectByExample(example);
        for (UserAppointment data : pageList) {     	
			if (null != data) {
				UserAppointmentVo vo = new UserAppointmentVo();
				BeanUtils.copyProperties(vo, data);
				vo.setBeginTime(DateUtil.dateToDateString(data.getStartTime(), DateUtil.DATAFORMAT_STR));
				vo.setKemuStr(OnlineTypeEnum.getEnumByCode(data.getKemu()).desc);
				vo.setExamPlaceName(data.getExamPlaceName());
				vo.setExamPlaceAddress(data.getExamPlaceAddress());
				result.add(vo);
			}
		}
        return result;
    }
    
	/**
	 * 获取预约考试科目
	 * 
	 * @param userId
	 * @param kemu
	 * @return
	 * @throws Exception
	 */
	public UserAppointmentScoreVo selectScoreByKemu(Long userId, Integer kemu) throws Exception {
		UserAppointmentScoreVo vo = new UserAppointmentScoreVo();
		// 单表自定义查询
		Example example = new Example(UserAppointment.class);
		example.createCriteria().andCondition("is_delete = 0").andCondition("user_id = ", userId)
				.andCondition("kemu = ", kemu);
		example.setOrderByClause("update_time desc");
		List<UserAppointment> recordList = userAppointmentMapper.selectByExample(example);
		if (null != recordList && recordList.size() > 0) {
			UserAppointment record = recordList.get(0);
			vo.setBeginTime(DateUtil.dateToDateString(record.getStartTime(), DateUtil.DATAFORMAT_STR));
			vo.setExamPlaceAddress(record.getExamPlaceAddress());
			vo.setScore(record.getScore());
			vo.setUserId(userId);
			vo.setKemu(kemu);
			vo.setId(record.getId());
		} else {
			return null;
		}
		return vo;
	}
    
    /**
     * 根据id查询
     * @param model
     * @return
     */
    public UserAppointment queryById(Long id) {
        return userAppointmentMapper.selectByPrimaryKey(id);
    }
   
    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	UserAppointment record = userAppointmentMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return userAppointmentMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public UserAppointment selectById(Long id) {
    	return userAppointmentMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 查 最近一次用户课程预约情况，根据考场科目信息查询
     * @param id
     * @return
     */
    public UserAppointment selectByAppointId(Long appointId, Long userId) {
    	//单表自定义查询
        Example example = new Example(UserAppointment.class);
        example.createCriteria().andCondition("is_delete = 0")
        .andCondition("appoint_id = ", appointId).andCondition("user_id = ", userId);
        example.setOrderByClause("create_time desc");
        List<UserAppointment> list = userAppointmentMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
        	return list.get(0);
        }
    	return null;
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(UserAppointment model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return userAppointmentMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return userAppointmentMapper.insert(model);
    	}
    }
    
    /**
     * 分页查询 
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public Page<UserAppointment> selectConditionByPage(Integer pageIndex, Integer pageSize, String mobile, Integer status, Integer scoreStatus) {
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(UserAppointment.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andLike("userName", "%" + mobile + "%");
        }
        if (null != status) {
            criteria.andCondition("status=", status);
        }
        if (null != scoreStatus) {
        	if (scoreStatus == 0) {
        		// 未编辑
        	  criteria.andIsNull("score");
        	} else if (scoreStatus == 1) {
       		  criteria.andIsNotNull("score");
        	}           
        }
        example.setOrderByClause("update_time desc");
        return (Page<UserAppointment>)userAppointmentMapper.selectByExample(example);
    }
    
    /**
     * 分页查询 用户已预约考试成绩查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<UserAppointment> selectScoreByPage(Integer pageIndex,Integer pageSize, Long userId) throws Exception{
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);

        //单表自定义查询
        Example example = new Example(UserAppointment.class);
        example.createCriteria().andCondition("is_delete = 0").andCondition("user_id = ", userId).andGreaterThan("score", 0);
        example.setOrderByClause("update_time desc");
        return userAppointmentMapper.selectByExample(example);
    }
}
