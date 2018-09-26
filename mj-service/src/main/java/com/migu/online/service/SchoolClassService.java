package com.migu.online.service;

import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.SchoolClassMapper;
import com.migu.online.model.Demo;
import com.migu.online.model.SchoolClass;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassMapper schoolClassMapper;
 

    /**
     * 添加：
     * 在Demo po中的id列加入@GeneratedValue(strategy = GenerationType.IDENTITY) 可自动生成Id,
     * @param demo
     * @return
     */
    public SchoolClass insert(SchoolClass model){
        //通用mapper中的两个新增方式
    	schoolClassMapper.insert(model); //插入所有数据，保存字段值为null的。
       // demoMapper.insertSelective(demo); //只插入字段值不为null的数据。

        int id = model.getId();  //调用插入方法后，通过这样的方式获取插入的数据的新ID
        System.out.println(id);
        return model;
    }

    public List<SchoolClass> selectAll(){
        // 单表分页
        //PageHelper.startPage(0,10);

        //单表自定义查询
        //Example example = new Example(Demo.class);

        return schoolClassMapper.selectAll();
    }
    

	public List<SchoolClass> getSchoolClasses(String filter,Integer pageIndex,Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(SchoolClass.class);
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            example.createCriteria().andLike("name", filter);
        }

        return schoolClassMapper.selectByExample(example);
    }

	public SchoolClass getSchoolClass(Integer id) {
		SchoolClass model = new SchoolClass();
		model.setId(id);
		return schoolClassMapper.selectOne(model);
	}

	public SchoolClass createOrUpdate(SchoolClass model) {
        // 新增
        if(model.getId() == null){
        	schoolClassMapper.insert(model);
        }else{
        	schoolClassMapper.updateByPrimaryKeySelective(model);
        }
        return model;	   
	}
	
	public int delete(Integer id) {
		SchoolClass model = new SchoolClass();
		model.setId(id);
        return schoolClassMapper.deleteByPrimaryKey(model);
    }
}
