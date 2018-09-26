package com.migu.online.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.CommonMapper;
import com.migu.online.model.Common;

import tk.mybatis.mapper.entity.Example;

@Service
public class CommonService {

	@Autowired
	private CommonMapper commonMapper;

	public List<Common> getCommons(Integer pageIndex, Integer pageSize) {
		// 单表分页
		PageHelper.startPage(pageIndex, pageSize);

		// 单表自定义查询
		// Example example = new Example(Demo.class);

		return commonMapper.selectAll();
	}
	
	public List<Common> getCommons(String type,String filter,Integer pageIndex,Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(Common.class);
        example.createCriteria().andLike("type", type);
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            example.createCriteria().andLike("name", filter);
        }

        return commonMapper.selectByExample(example);
    }

	public Common getCommon(Integer id) {
		Common model = new Common();
		model.setId(id);
		return commonMapper.selectOne(model);
	}

	public Common createOrUpdate(Common model) {
        // 新增
        if(model.getId() == null){
        	commonMapper.insert(model);
        }else{
        	commonMapper.updateByPrimaryKeySelective(model);
        }
        return model;	   
	}
	
	public int delete(Integer id) {
		Common model = new Common();
		model.setId(id);
        return commonMapper.deleteByPrimaryKey(model);
    }
}
