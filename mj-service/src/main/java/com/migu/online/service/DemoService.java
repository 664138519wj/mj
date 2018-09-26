package com.migu.online.service;

import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.DemoMapper;
import com.migu.online.model.Demo;

import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public List<Demo> selectAll(){
        // 单表分页
        //PageHelper.startPage(0,10);

        //单表自定义查询
        //Example example = new Example(Demo.class);

        return demoMapper.selectAll();
    }

    public Demo selectOne(Integer id){
        Demo demo = new Demo();
        demo.setId(id);
        return demoMapper.selectOne(demo);
    }

    /**
     * 添加：
     * 在Demo po中的id列加入@GeneratedValue(strategy = GenerationType.IDENTITY) 可自动生成Id,
     * @param demo
     * @return
     */
    public Demo insert(Demo demo){
        //通用mapper中的两个新增方式
        demoMapper.insert(demo); //插入所有数据，保存字段值为null的。
       // demoMapper.insertSelective(demo); //只插入字段值不为null的数据。

        int id = demo.getId();  //调用插入方法后，通过这样的方式获取插入的数据的新ID
        System.out.println(id);
        return demo;
    }

    public Demo update(Demo demo){
        //与插入的方式类似，更新需要有主键，一般在表的模型定义里面加@Id
        //demoMapper.updateByPrimaryKey(demo);
        demoMapper.updateByPrimaryKeySelective(demo);

        return demo;
    }

    public int delete(Integer id){
        if(id != null){
            Demo demo = new Demo();
            demo.setId(id);
            return demoMapper.deleteByPrimaryKey(demo);
        }
        return 0;
    }

}
