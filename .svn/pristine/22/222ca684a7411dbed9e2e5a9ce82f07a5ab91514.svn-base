package com.migu.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.SendMsgRecordMapper;
import com.migu.online.model.SendMsgRecord;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SendMsgRecordService {

	@Autowired
	private SendMsgRecordMapper sendMsgRecordMapper;
	

	/**
	 * 添加：
	 * 可自动生成Id,
	 * @param demo
	 * @return
	 */
	public SendMsgRecord insert(SendMsgRecord model) {
		// 通用mapper中的两个新增方式
		sendMsgRecordMapper.insert(model); // 插入所有数据，保存字段值为null的。
		return model;
	}
	
	/* update*/
	public void update(SendMsgRecord user) {
		sendMsgRecordMapper.updateByPrimaryKey(user);
	}

	/**
	 * 手机号码查找
	 * @param mobile
	 * @return
	 */
	public SendMsgRecord selectOneByMobile(String mobile) {
		SendMsgRecord user = new SendMsgRecord();
		user.setMobile(mobile);
		user.setIsDelete(0);
		return sendMsgRecordMapper.selectOne(user);
	}

	 /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	SendMsgRecord record = sendMsgRecordMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return sendMsgRecordMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public SendMsgRecord selectById(Long id) {
    	return sendMsgRecordMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(SendMsgRecord model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return sendMsgRecordMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return sendMsgRecordMapper.insert(model);
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
    public Page<SendMsgRecord> selectConditionByPage(Integer pageIndex, Integer pageSize, String mobile, String realName) {
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(SendMsgRecord.class);
        Criteria criteria = example.createCriteria();
//        criteria.andCondition("is_delete = 0");
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andLike("mobile", "%" + mobile + "%");
        }
        return (Page<SendMsgRecord>)sendMsgRecordMapper.selectByExample(example);
    }
    
    /**
     * 分页查询 
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<SendMsgRecord> selectUnSendMsgByPage(Integer pageIndex, Integer pageSize) {
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);
        //单表自定义查询
        Example example = new Example(SendMsgRecord.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        criteria.andLessThan("status", "3");
        example.setOrderByClause("create_time asc");
        return sendMsgRecordMapper.selectByExample(example);
    }
}
