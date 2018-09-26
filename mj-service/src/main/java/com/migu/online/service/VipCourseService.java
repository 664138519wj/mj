package com.migu.online.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.VipCourseMapper;
import com.migu.online.model.VipCourse;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class VipCourseService {

    @Autowired
    private VipCourseMapper vipCourseMapper;
 
 
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<VipCourse> selectByPage(Integer kemu, Integer pageIndex,Integer pageSize) throws Exception{
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);
        //单表自定义查询
        Example example = new Example(VipCourse.class);
        if (null != kemu) {
        	Criteria criteria = example.createCriteria();
        	criteria.andCondition("kemu=", kemu);
        }
        Page<VipCourse> pageList = (Page<VipCourse>)vipCourseMapper.selectByExample(example);
        return pageList;
    }
    
  
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<VipCourse> selectConditonByPage(String filter, Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(VipCourse.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("title", filter);
        }
		example.setOrderByClause("id desc");
        return vipCourseMapper.selectByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	VipCourse record = vipCourseMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	return vipCourseMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public VipCourse selectById(Long id) {
    	return vipCourseMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(VipCourse model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return vipCourseMapper.updateByPrimaryKey(model);
    	} else {   		
    		return vipCourseMapper.insert(model);
    	}
    }
    
    public List<VipCourse> selectAll() {
        return vipCourseMapper.selectAll();
    }
    
}
