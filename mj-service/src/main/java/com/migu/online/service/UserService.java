package com.migu.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.common.Constants;
import com.migu.online.mapper.CourseOnlineMapper;
import com.migu.online.mapper.UserMapper;
import com.migu.online.model.CourseOnline;
import com.migu.online.model.User;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CourseOnlineMapper courseOnlineMapper;
	@Autowired
	private RedisService redisService;

	/**
	 * 添加：
	 * 可自动生成Id,
	 * @param demo
	 * @return
	 */
	public User insert(User model) {
		// 通用mapper中的两个新增方式
		userMapper.insert(model); // 插入所有数据，保存字段值为null的。
//		int id = model.getId(); // 调用插入方法后，通过这样的方式获取插入的数据的新ID
		return model;
	}
	
	/* update*/
	public void update(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	/**
	 * 手机号码查找
	 * @param mobile
	 * @return
	 */
	public User selectOneByMobile(String mobile) {
		User user = new User();
		user.setMobile(mobile);
		user.setIsDelete(0);
		return userMapper.selectOne(user);
	}

	public List<CourseOnline> getMyCourseOnlines(Integer customerid) {
		List<CourseOnline> lst= courseOnlineMapper.getCustomerCourseOnline(customerid);
		return lst;
	}
	
	 /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	User record = userMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return userMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public User selectById(Long id) {
    	return userMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(User model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return userMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return userMapper.insert(model);
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
    public Page<User> selectConditionByPage(Integer pageIndex, Integer pageSize, String mobile, String realName) {
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
//        criteria.andCondition("is_delete = 0");
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andLike("mobile", "%" + mobile + "%");
        }
        if (!StringUtils.isEmpty(realName)) {
            criteria.andLike("realName", "%" + realName + "%");
        }
        example.setOrderByClause("update_time desc");
        return (Page<User>)userMapper.selectByExample(example);
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public User selectCachUserById(Long id) {
    	String userKey = Constants.REDIS_KEY_USER + id;
    	User user = (User)redisService.get(userKey);
    	if (null == user) {
    		user = userMapper.selectByPrimaryKey(id);
    		redisService.set(userKey, user, 60 * 60 * 24);
    	}
    	return user;
    }
}
