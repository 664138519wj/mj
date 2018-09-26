package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.online.mapper.ShufflingMapper;
import com.migu.online.model.Shuffling;
import com.migu.online.vo.ShufflingVo;

import tk.mybatis.mapper.entity.Example;


@Service
public class ShufflingService {

    @Autowired
    private ShufflingMapper shufflingMapper;
 
    /**
     * 首页轮播图获取
     * @return
     * @throws Exception
     */
    public List<ShufflingVo> selectAll() throws Exception{
    	List<ShufflingVo> result = new ArrayList<ShufflingVo>();
        //单表自定义查询
        Example example = new Example(Shuffling.class);
        example.createCriteria().andCondition("is_delete = 0");
        example.setOrderByClause("sort desc");
        List<Shuffling> list = shufflingMapper.selectByExample(example);
        for (Shuffling data: list) {
        	ShufflingVo vo = new ShufflingVo();
        	BeanUtils.copyProperties(vo, data);
        	result.add(vo);
        }       
        return result;
    }
}
