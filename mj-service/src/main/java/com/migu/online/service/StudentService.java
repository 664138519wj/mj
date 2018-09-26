package com.migu.online.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.migu.online.mapper.StudentMapper;
import com.migu.online.model.Demo;
import com.migu.online.model.Student;
import com.migu.online.model.TeacherWithdraw;
import com.migu.online.vo.TeacherWithdrawVo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
 

    /**
     * 添加：
     * 在Demo po中的id列加入@GeneratedValue(strategy = GenerationType.IDENTITY) 可自动生成Id,
     * @param demo
     * @return
     */
    public Student insert(Student model){
        //通用mapper中的两个新增方式
        studentMapper.insert(model); //插入所有数据，保存字段值为null的。
       // demoMapper.insertSelective(demo); //只插入字段值不为null的数据。

        int id = model.getId();  //调用插入方法后，通过这样的方式获取插入的数据的新ID
        System.out.println(id);
        return model;
    }

    public List<Student> selectAll(){
        // 单表分页
        //PageHelper.startPage(0,10);

        //单表自定义查询
        //Example example = new Example(Demo.class);

        return studentMapper.selectAll();
    }
    

	public List<Student> getStudents(String filter,Integer pageIndex,Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(Student.class);
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            example.createCriteria().andLike("name", filter);
        }

        return studentMapper.selectByExample(example);
    }

	public Student getStudent(Integer id) {
		Student model = new Student();
		model.setId(id);
		return studentMapper.selectOne(model);
	}

	public Student createOrUpdate(Student model) {
        // 新增
        if(model.getId() == null){
        	studentMapper.insert(model);
        }else{
        	studentMapper.updateByPrimaryKeySelective(model);
        }
        return model;	   
	}
	
	public int delete(Integer id) {
		Student model = new Student();
		model.setId(id);
        return studentMapper.deleteByPrimaryKey(model);
    }

	public List<Student> selectByParams(Map<String, Object> filters) {
//	      boolean byPage = true;
//	        int pageIndex = 0;
//	        int pageSize = 0;
	        Example example = new Example(Student.class);
	        Criteria criteria = example.createCriteria();
	        for (String key : filters.keySet()) {
	            switch (key) {
	                case "userName":
	                    String userName = String.valueOf(filters.get(key));
	                    criteria.andEqualTo("name",userName);
	                    break;
	                case "telNo":
	                    String telNo = String.valueOf(filters.get(key));
	                    criteria.andEqualTo("mobile1", telNo);
	                    break;
	               
	            }
	        }
	        example.setOrderByClause("name desc");

	        List<Student> students = (List<Student>) studentMapper.selectByExample(example);
	       return students;
	}
}