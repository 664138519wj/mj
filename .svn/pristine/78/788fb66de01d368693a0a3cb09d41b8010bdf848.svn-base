package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.PlatformNoticeMapper;
import com.migu.online.model.PlatformNotice;
import com.migu.online.utils.DateUtil;
import com.migu.online.vo.PlatformNoticeVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class PlatformNoticeService {

    @Autowired
    private PlatformNoticeMapper platformNoticeMapper;
    
    /**
     * 详情
     * @param id
     * @return
     */
    public PlatformNoticeVo selectOne(Long id) throws Exception {
    	PlatformNoticeVo vo = new PlatformNoticeVo();
    	PlatformNotice model = new PlatformNotice();
    	model.setId(id);
    	model.setIsDelete(0);
    	PlatformNotice data = platformNoticeMapper.selectOne(model);
		if (null != data) {
			BeanUtils.copyProperties(vo, data);
            vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATATIMEF_STR));
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
    public Page<PlatformNotice> selectByPage(Integer pageIndex,Integer pageSize) throws Exception{
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize, true);

        //单表自定义查询
        Example example = new Example(PlatformNotice.class);
        example.createCriteria().andCondition("is_delete = 0");
		example.setOrderByClause("update_time desc");        
        return (Page<PlatformNotice>)platformNoticeMapper.selectByExample(example);
    }
       
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<PlatformNotice> selectConditonByPage(String filter, Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(PlatformNotice.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("title", filter);
        }
		example.setOrderByClause("update_time desc");
        return platformNoticeMapper.selectByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	PlatformNotice record = platformNoticeMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return platformNoticeMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public PlatformNotice selectById(Long id) {
    	return platformNoticeMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(PlatformNotice model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return platformNoticeMapper.updateByPrimaryKey(model);
    	} else {
    		// add    
    		return platformNoticeMapper.insert(model);
    	}
    }
    
    /**
     * 分页查询 用户已预约考试
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<PlatformNoticeVo> selectByPageApp(Integer pageIndex,Integer pageSize) throws Exception{
    	List<PlatformNoticeVo> result = new ArrayList<PlatformNoticeVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);

        //单表自定义查询
        Example example = new Example(PlatformNotice.class);
        example.createCriteria().andCondition("is_delete = 0");
        example.setOrderByClause("update_time desc");
        List<PlatformNotice> pageList = platformNoticeMapper.selectByExample(example);
        for (PlatformNotice data : pageList) {     	
			if (null != data) {
				PlatformNoticeVo vo = new PlatformNoticeVo();
				BeanUtils.copyProperties(vo, data);
				vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATAFORMAT_STR));				
				result.add(vo);
			}
		}
        return result;
    }
}
