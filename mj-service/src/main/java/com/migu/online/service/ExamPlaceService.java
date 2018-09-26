package com.migu.online.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.migu.online.mapper.AreaMapper;
import com.migu.online.mapper.ExamPlaceMapper;
import com.migu.online.model.Area;
import com.migu.online.model.ExamPlace;
import com.migu.online.ops.vo.ExamPlaceOpsVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ExamPlaceService {

    @Autowired
    private ExamPlaceMapper examPlaceMapper;
    @Autowired
    private AreaMapper areaMapper;
      
   
    public ExamPlace selectById(Integer id) {
        return examPlaceMapper.selectByPrimaryKey(id);
    }
    
    public ExamPlaceOpsVo selectOpsById(Integer id) throws IllegalAccessException, InvocationTargetException {
    	ExamPlace data = examPlaceMapper.selectByPrimaryKey(id);
    	if (null == data) {
    		return null;
    	}
    	ExamPlaceOpsVo vo = new ExamPlaceOpsVo();
		BeanUtils.copyProperties(vo, data);
		Area area = areaMapper.selectByPrimaryKey(data.getAreaId());
		if (null != area) {
			vo.setAreaName(area.getCity());
		}
        return vo;
    }
    
    public List<ExamPlace> selectAll() {
        return examPlaceMapper.selectAll();
    }
    
    public Map<Integer, ExamPlace> selectAllMap() {
    	List<ExamPlace> all = examPlaceMapper.selectAll();
    	Map<Integer, ExamPlace> map = new HashMap<>();
    	for (ExamPlace data : all) {
    		map.put(data.getId(), data);	
    	}
    	return map;
    }

    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(ExamPlace model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return examPlaceMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return examPlaceMapper.insert(model);
    	}
    }
    
   
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<ExamPlace> selectConditonByPage(String filter, Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);
        Example example = new Example(ExamPlace.class);
        Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("name", filter);
        }
		example.setOrderByClause("id desc");
        return examPlaceMapper.selectByExample(example);
    }
    
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public Page<ExamPlaceOpsVo> selectConditonByPage2(String filter, Integer pageIndex, Integer pageSize) throws IllegalAccessException, InvocationTargetException{
        // 单表分页
        Page<ExamPlaceOpsVo> result = new Page<ExamPlaceOpsVo>();
        PageHelper.startPage(pageIndex, pageSize, true);
        Example example = new Example(ExamPlace.class);
        Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("name", filter);
        }
		example.setOrderByClause("id desc");
        Page<ExamPlace> pageList = (Page<ExamPlace>)examPlaceMapper.selectByExample(example);
		for (ExamPlace data : pageList) {
			ExamPlaceOpsVo vo = new ExamPlaceOpsVo();
			BeanUtils.copyProperties(vo, data);
			Area area = areaMapper.selectByPrimaryKey(data.getAreaId());
			if (null != area) {
				vo.setAreaName(area.getCity());
			}
			result.add(vo);
		}
		result.setTotal(pageList.getTotal());
        return result;
    }
    
    /**
     * 
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Integer> selectByAddressOrAreaId(Long areaId, String address){
        Example example = new Example(ExamPlace.class);
        Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(address)) {
        	address = "%" + address + "%";
            criteria.andLike("address", address);
        }
        if(areaId != null) {
            criteria.andCondition("area_id = ", areaId);
        }
        List<Integer> ids = new ArrayList<>();
        List<ExamPlace> list = examPlaceMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
        	for (ExamPlace data : list) {
        		ids.add(data.getId());
        	}
        	return ids;
        }
        return null;
    }
}
