package com.migu.online.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.ContactMapper;
import com.migu.online.model.Contact;
import com.migu.online.model.Teacher;
import com.migu.online.model.Works;
import com.migu.online.vo.TeacherDetailVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ContactService {

	
	@Autowired
	private ContactMapper contactMapper;
	
	
	public List<Contact> selectAll() {
		List<Contact> result = contactMapper.selectAll();
		return result;
	}
	
	


	
	/**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Integer id) {
    	return contactMapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public Contact selectById(Integer id) {
    	return contactMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(Contact model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return contactMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return contactMapper.insert(model);
    	}
    }





	public List<Contact> getByParams(Map<String, String> filters) {

        //单表自定义查询
        Example example = new Example(Contact.class);
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
                    case "phone":
                    	criteria.andEqualTo("phone", "%" + value + "%");
                        break;
                    case "intent":
                    	criteria.andEqualTo("intent", value);
                        break;
                   
                }
            }
        }
        example.and(criteria);
        example.setOrderByClause("create_time desc");   
        
        List<Contact> result = contactMapper.selectByExample(example);
        
        if (result.size() > 0) {
    		for (Contact contact:result) {
    			contact.setIntentStr(contact.getIntent());
    		}
    	}
       
		return result;
	}





	public Page<Contact> selectByPage(Integer pageIndex, Integer pageSize, Map<String, String> filters) {
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);

        //单表自定义查询
        Example example = new Example(Contact.class);
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
                    case "phone":
                    	criteria.andLike("phone", "%" + value + "%");
                        break;
                    case "intent":
                    	criteria.andEqualTo("intent", value);
                        break;
                   
                }
            }
        }
        example.and(criteria);
        example.setOrderByClause("create_time desc");   
        
        Page<Contact> pageList = (Page<Contact>)contactMapper.selectByExample(example);
        List<Contact> result = pageList.getResult();

        if (result.size() > 0) {
    		for (Contact contact:result) {
    			contact.setIntentStr(contact.getIntent());
    		}
    	}
        return pageList;
	}
    

}
