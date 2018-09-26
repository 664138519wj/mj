package com.migu.online.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.migu.online.mapper.MediaMapper;
import com.migu.online.model.Media;
import com.migu.online.model.Works;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MediaService {

	
	@Autowired
	private MediaMapper mediaMapper;
	
	
	public List<Media> selectAll() {
		List<Media> result = mediaMapper.selectAll();
		return result;
	}
	
	


	
	/**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Integer id) {
    	return mediaMapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public  Media selectById(Integer id) {
    	return mediaMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(Media model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return  mediaMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return  mediaMapper.insert(model);
    	}
    }





	public List<Media> getByParams(Map<String, String> filters) {

        //单表自定义查询
        Example example = new Example(Media.class);
        Criteria criteria = example.createCriteria();
        if (filters.size() > 0) {
            for (String key : filters.keySet()) {
                String value = filters.get(key);
                if (StringUtils.isEmpty(value)) {
                	continue;
                }
                switch (key) {
                    case "name":
                    	criteria.andLike("name", "%" + value + "%");
                        break;
                   
                }
            }
        }
        example.and(criteria);
        example.setOrderByClause("create_time desc");   
        
        List<Media> result = mediaMapper.selectByExample(example);
       
		return result;
	}
    

}
