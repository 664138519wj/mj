package com.migu.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.migu.online.mapper.DrvTempMidMapper;
import com.migu.online.model.DrivingSchool;
import com.migu.online.model.DrvTempMid;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DrvTempMidService {

	@Autowired
	private DrvTempMidMapper drvTempMidMapper;
	@Autowired
	private DrivingSchoolService drivingSchoolService;


	/**
     * 分页查询 
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public PageInfo selectByPage(Integer pageIndex, Integer pageSize, Map<String, String> param, Integer schoolId) throws Exception{
        // 单表分页
		PageHelper.startPage(pageIndex, pageSize, true);
		Map<String, Object> params = new HashMap<>();
		params.put("startIndex", (pageIndex-1) * pageSize);
		params.put("endIndex", pageSize);
        //单表自定义查询
        Example example = new Example(DrvTempMid.class);
        Criteria criteria = example.createCriteria();
        String name = param.get("name");
        if (StringUtils.isNotEmpty(name)) {        	
        	criteria.andCondition("xm=",  name);
        	params.put("name", name);
        }
        String idNo = param.get("idNo");
        if (StringUtils.isNotEmpty(idNo)) {        	
        	criteria.andCondition("sfzmhm=",  idNo);
        	params.put("idNo", idNo);
        }
        String mobile = param.get("mobile");
        if (StringUtils.isNotEmpty(mobile)) {        	
        	criteria.andCondition("sjhm=",  mobile);
        	params.put("mobile", mobile);
        }
        if (null != schoolId) {
        	DrivingSchool school = drivingSchoolService.selectById(schoolId);
        	if (null == school) {
            	return null;
        	} else {
            	criteria.andCondition("jxmc=", school.getSchoolCode());
            	params.put("schoolCode", school.getSchoolCode());
        	}
    	}   
        // TODO 莫名其妙 分页不能用，只能手写sql
        int totalCount = drvTempMidMapper.selectCountBySchoolCode(params);
        List<DrvTempMid> list = drvTempMidMapper.selectBySchoolCode(params);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setTotal(totalCount);
        return pageInfo;
    }
    
    /**
     * id 查询
     * @param xyid
     * @return
     */
    public DrvTempMid selectById(Integer xyid) {
    	DrvTempMid record = new DrvTempMid();
    	record.setXyid(xyid);
    	record = drvTempMidMapper.selectOne(record);
    	return record;
    }
    
}
