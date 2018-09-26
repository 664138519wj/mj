package com.migu.online.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.migu.online.mapper.JobsMapper;
import com.migu.online.model.Jobs;
import com.migu.online.model.Media;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class JobsService {

	
	@Autowired
	private JobsMapper jobsMapper;
	
	
	public List<Jobs> selectAll() {
		List<Jobs> result = jobsMapper.selectAll();
		return result;
	}
	
	


	
	/**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Integer id) {
    	return jobsMapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public  Jobs selectById(Integer id) {
    	return jobsMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(Jobs model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return  jobsMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return  jobsMapper.insert(model);
    	}
    }





	public List<Jobs> getByParams(Map<String, String> filters) {

        //单表自定义查询
        Example example = new Example(Jobs.class);
        Criteria criteria = example.createCriteria();
        if (filters.size() > 0) {
            for (String key : filters.keySet()) {
                String value = filters.get(key);
                if (StringUtils.isEmpty(value)) {
                	continue;
                }
                switch (key) {
                    case "post":
                    	criteria.andLike("post", "%" + value + "%");
                        break;
                   
                }
            }
        }
        example.and(criteria);
        example.setOrderByClause("create_time desc");   
        
        List<Jobs> result = jobsMapper.selectByExample(example);
       
		return result;
	}
    

}
