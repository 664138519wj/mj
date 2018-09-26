package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.TrafficLawMapper;
import com.migu.online.model.TrafficLaw;
import com.migu.online.vo.TrafficLawVo;
import com.migu.online.vo.TrafficLawWebVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class TrafficLawService {

    @Autowired
    private TrafficLawMapper trafficLawMapper;
 
    /**
     * 详情
     * @param id
     * @return
     */
    public TrafficLawVo selectOne(Long id) throws Exception {
    	TrafficLawVo vo = new TrafficLawVo();
    	TrafficLaw model = new TrafficLaw();
    	model.setId(id);
    	model.setIsDelete(0);
    	TrafficLaw data = trafficLawMapper.selectOne(model);
		BeanUtils.copyProperties(vo, data);
		vo.setTopicStr(vo.conventTopi(data.getTopic()));

        return vo;
    }
    
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<TrafficLawVo> selectByPage(Integer pageIndex,Integer pageSize) throws Exception{
    	
		List<TrafficLawVo> result = new ArrayList<TrafficLawVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);

        //单表自定义查询
        Example example = new Example(TrafficLaw.class);
        example.createCriteria().andCondition("is_delete = 0");
        example.setOrderByClause("topic");   
        List<TrafficLaw> pageList = trafficLawMapper.selectByExample(example);
        for (TrafficLaw data : pageList) {
        	TrafficLawVo vo = new TrafficLawVo();
			BeanUtils.copyProperties(vo, data);
			vo.setTopicStr(vo.conventTopi(data.getTopic()));
			vo.setContent(data.getSummary());
			result.add(vo);
		}
        return result;
    }
    
    /**
     * 详情
     * @param id
     * @return
     */
    public TrafficLawWebVo selectWebOne(Long id) throws Exception {
    	TrafficLawWebVo vo = new TrafficLawWebVo();
    	TrafficLaw model = new TrafficLaw();
    	model.setId(id);
    	model.setIsDelete(0);
    	TrafficLaw data = trafficLawMapper.selectOne(model);
    	if (null != data) {
    		BeanUtils.copyProperties(vo, data);
    		vo.setTopicStr(vo.conventTopi(data.getTopic()));
    		String ids[] = data.getRelateId().split(";");
            model.setId(Long.parseLong(ids[0]));
            TrafficLaw upPolicy = trafficLawMapper.selectOne(model);
            if (null != upPolicy) {
                vo.setUpId(upPolicy.getId());
                vo.setUpTitle(upPolicy.getTitle());
            }
            model.setId(Long.parseLong(ids[1]));
            TrafficLaw downPolicy = trafficLawMapper.selectOne(model);
            if (null != downPolicy) {
                vo.setDownId(downPolicy.getId());
                vo.setDownTitle(downPolicy.getTitle());
            }
    	}
		
        return vo;
    }
    
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<TrafficLawVo> selectWebByPage(Integer pageIndex,Integer pageSize) throws Exception{
    	
    	Page<TrafficLawVo> result = new Page<TrafficLawVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(TrafficLaw.class);
        example.createCriteria().andCondition("is_delete = 0");
        example.setOrderByClause("topic");   
        Page<TrafficLaw> pageList = (Page<TrafficLaw>)trafficLawMapper.selectByExample(example);
        for (TrafficLaw data : pageList) {
        	TrafficLawVo vo = new TrafficLawVo();
			BeanUtils.copyProperties(vo, data);
			vo.setTopicStr(vo.conventTopi(data.getTopic()));
			vo.setContent(data.getSummary());
			result.add(vo);
		}
        result.setTotal(pageList.getTotal());
        return result;
    }

    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<TrafficLaw> selectConditonByPage(String filter, Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(TrafficLaw.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("title", filter);
        }
		example.setOrderByClause("id desc");
        return trafficLawMapper.selectByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	TrafficLaw record = trafficLawMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return trafficLawMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public TrafficLaw selectById(Long id) {
    	return trafficLawMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(TrafficLaw model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return trafficLawMapper.updateByPrimaryKey(model);
    	} else {   		
    		return trafficLawMapper.insert(model);
    	}
    }

}
