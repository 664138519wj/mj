package com.migu.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.PracticeCardMapper;
import com.migu.online.model.PracticeCard;

import tk.mybatis.mapper.entity.Example;

@Service
public class PracticeCardService {

	@Autowired
	private PracticeCardMapper practiceCardMapper;

	public PracticeCard getOne(Long id) {
		return practiceCardMapper.selectByPrimaryKey(id);
	}

	public PracticeCard createOrUpdate(PracticeCard model) {
        // 新增
        if(model.getId() == null){
        	practiceCardMapper.insert(model);
        }else{
        	practiceCardMapper.updateByPrimaryKeySelective(model);
        }
        return model;	   
	}
	
	public int delete(Long id) {
		PracticeCard model = new PracticeCard();
		model.setId(id);
        return practiceCardMapper.deleteByPrimaryKey(model);
    }
	
	 /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<PracticeCard> selectByPage(Integer pageIndex,Integer pageSize) throws Exception{
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize, true);

        //单表自定义查询
        Example example = new Example(PracticeCard.class);  
        return (Page<PracticeCard>)practiceCardMapper.selectByExample(example);
    }
}
