package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.DrivingPolicyMapper;
import com.migu.online.model.DrivingPolicy;
import com.migu.online.utils.DateUtil;
import com.migu.online.vo.DrivingPolicyVo;
import com.migu.online.vo.DrivingPolicyWebVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DrivingPolicyService {

    @Autowired
    private DrivingPolicyMapper drivingPolicyMapper;
    
    /**
     * 详情
     * @param id
     * @return
     */
    public DrivingPolicyVo selectOne(Long id) throws Exception {
    	DrivingPolicyVo vo = new DrivingPolicyVo();
    	DrivingPolicy model = new DrivingPolicy();
    	model.setId(id);
    	model.setIsDelete(0);
    	DrivingPolicy data = drivingPolicyMapper.selectOne(model);
		if (null != data) {
			BeanUtils.copyProperties(vo, data);
            vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATATIMEF_STR));
		}    
        return vo;
    }
    
    /**
     * web详情
     * @param id
     * @return
     */
    public DrivingPolicyWebVo selectWebOne(Long id) throws Exception {
    	DrivingPolicyWebVo vo = new DrivingPolicyWebVo();
    	DrivingPolicy model = new DrivingPolicy();
    	model.setId(id);
    	model.setIsDelete(0);
    	DrivingPolicy data = drivingPolicyMapper.selectOne(model);
		if (null != data) {
			BeanUtils.copyProperties(vo, data);
            vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATATIMEF_STR));
            String ids[] = data.getRelateId().split(";");
            model.setId(Long.parseLong(ids[0]));
            DrivingPolicy upPolicy = drivingPolicyMapper.selectOne(model);
            if (null != upPolicy) {
                vo.setUpId(upPolicy.getId());
                vo.setUpTitle(upPolicy.getTitle());
            }
            model.setId(Long.parseLong(ids[1]));
            DrivingPolicy downPolicy = drivingPolicyMapper.selectOne(model);
            if (null != downPolicy) {
                vo.setDownId(downPolicy.getId());
                vo.setDownTitle(downPolicy.getTitle());
            }
            
		}    
        return vo;
    }
    
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<DrivingPolicyVo> selectByPage(Integer pageIndex,Integer pageSize) throws Exception{
    	List<DrivingPolicyVo> result = new ArrayList<DrivingPolicyVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);

        //单表自定义查询
        Example example = new Example(DrivingPolicy.class);
        example.createCriteria().andCondition("is_delete = 0");
		example.setOrderByClause("update_time desc");
        List<DrivingPolicy> pageList = drivingPolicyMapper.selectByExample(example);
        for (DrivingPolicy data : pageList) {
        	DrivingPolicyVo vo = new DrivingPolicyVo();
			BeanUtils.copyProperties(vo, data);
			vo.setContent(data.getSummary());
			result.add(vo);
		}
        return result;
    }
    
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<DrivingPolicyVo> selectWebByPage(Integer pageIndex,Integer pageSize) throws Exception{
    	Page<DrivingPolicyVo> result = new Page<DrivingPolicyVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(DrivingPolicy.class);
        example.createCriteria().andCondition("is_delete = 0");
		example.setOrderByClause("update_time desc");
		Page<DrivingPolicy> pageList = (Page<DrivingPolicy>)drivingPolicyMapper.selectByExample(example);
        for (DrivingPolicy data : pageList) {
        	DrivingPolicyVo vo = new DrivingPolicyVo();
			BeanUtils.copyProperties(vo, data);
			vo.setContent(data.getSummary());
			result.add(vo);
		}
        result.setTotal(pageList.getTotal());
        return result;
    }
    
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<DrivingPolicy> selectConditonByPage(String filter, Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(DrivingPolicy.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("title", filter);
        }
		example.setOrderByClause("update_time desc");
        return drivingPolicyMapper.selectByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	DrivingPolicy record = drivingPolicyMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return drivingPolicyMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public DrivingPolicy selectById(Long id) {
    	return drivingPolicyMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(DrivingPolicy model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return drivingPolicyMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		DrivingPolicy data = selectLastedOne(1, 1);
    		model.setRelateId(data.getId() + ";" + 0);
    		int count = drivingPolicyMapper.insert(model);
    		// 获取
    		String relateId = data.getRelateId();
    		if (relateId.contains(";")) {
    			String args[] = relateId.split(";");
    			relateId = args[0] + ";" + model.getId();
    			data.setRelateId(relateId);
    			drivingPolicyMapper.updateByPrimaryKey(data);
    		}
    		return count;
    	}
    }
    
    /**
     * id最大一条
     * @param pageIndex
     * @param pageSize
     * @return
     */
    private DrivingPolicy selectLastedOne(Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(DrivingPolicy.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
		example.setOrderByClause("id desc");
		List<DrivingPolicy> list = drivingPolicyMapper.selectByExample(example);		
        return list.get(0);
    }
}
