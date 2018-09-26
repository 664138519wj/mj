package com.migu.online.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.WorksMapper;
import com.migu.online.model.Tag;
import com.migu.online.model.Teacher;
import com.migu.online.model.Works;
import com.migu.online.vo.TeacherDetailVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class WorksService {

	
	@Autowired
	private WorksMapper worksMapper;
	
	
	public List<Works> selectAll() {
		List<Works> result = worksMapper.selectAll();
		return result;
	}
	
	


	
	/**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Integer id) {
    	return worksMapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public Works selectById(Integer id) {
    	return worksMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(Works model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return worksMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return worksMapper.insert(model);
    	}
    }





	public List<Works> getByParams(Map<String, String> filters) {

        //单表自定义查询
        Example example = new Example(Works.class);
        Criteria criteria = example.createCriteria();
        if (filters.size() > 0) {
            for (String key : filters.keySet()) {
                String value = filters.get(key);
                if (StringUtils.isEmpty(value)) {
                	continue;
                }
                switch (key) {
                    case "cnName":
                    	criteria.andLike("cnName", "%" + value + "%");
                        break;
                    case "enName":
                    	criteria.andLike("enName", "%" + value + "%");
                        break;
                   
                }
            }
        }
        example.and(criteria);
        example.setOrderByClause("create_time desc");   
        
        List<Works> result = worksMapper.selectByExample(example);
       
		return result;
	}
    

}
