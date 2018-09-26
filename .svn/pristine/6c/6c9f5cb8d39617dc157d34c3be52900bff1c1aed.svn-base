package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.migu.online.mapper.DrivingSchoolMapper;
import com.migu.online.mapper.SchoolWithdrawMapper;
import com.migu.online.model.SchoolWithdraw;
import com.migu.online.model.TeacherWithdraw;
import com.migu.online.vo.SchoolWithdrawVo;
import com.migu.online.vo.TeacherWithdrawVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SchoolWithdrawService {

    @Autowired
    private SchoolWithdrawMapper schoolWithdrawMapper;

    @Autowired
    private DrivingSchoolMapper schoolMapper;

    public Page<SchoolWithdrawVo> selectByFilter(Map<String, String> filters) {
        boolean byPage = true;
        int pageIndex = 0;
        int pageSize = 0;
        Example example = new Example(SchoolWithdraw.class);
        Criteria criteria = example.createCriteria();
        for (String key : filters.keySet()) {
            switch (key) {
                case "school_id":
                    Long schoolId = Long.parseLong(filters.get(key));
                    criteria.andEqualTo("schoolId", schoolId);
                    break;
                case "status":
                    Integer status = Integer.parseInt(filters.get(key));
                    criteria.andEqualTo("status", status);
                    break;
                case "status_in":
                    ArrayList<Integer> codes = Lists.newArrayList();
                    for (String code : filters.get(key).split(",")) {
                        codes.add(Integer.parseInt(code));
                    }
                    criteria.andIn("status", codes);
                    break;
                case "by_page":
                    if (filters.get(key).equals("false")) {
                        byPage = false;
                    }
                    break;
                case "page_index":
                    pageIndex = Integer.parseInt(filters.get(key));
                    break;
                case "page_size":
                    pageSize = Integer.parseInt(filters.get(key));
                    break;
            }
        }
        example.setOrderByClause("update_time desc");

        Page<SchoolWithdrawVo> pageVo = new Page<SchoolWithdrawVo>();
        if (byPage) {
            PageHelper.startPage(pageIndex, pageSize, true);
        }
        List<SchoolWithdraw> pageList = (List<SchoolWithdraw>) schoolWithdrawMapper.selectByExample(example);
        for (SchoolWithdraw sw : pageList) {
        	SchoolWithdrawVo vo = new SchoolWithdrawVo();
            try {
                BeanUtils.copyProperties(vo, sw);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String schoolName = schoolMapper.selectByPrimaryKey(sw.getSchoolId().intValue()).getNameL();
            vo.setSchoolName(schoolName);
            pageVo.add(vo);
        }

        if (byPage) {
            pageVo.setTotal(((Page) pageList).getTotal());
        }
        return pageVo;
    }


    public int createOrUpdate(SchoolWithdraw model) {
        if (null != model.getId() && model.getId() > 0) {
            return schoolWithdrawMapper.updateByPrimaryKey(model);
        } else {
            return schoolWithdrawMapper.insert(model);
        }
    }


	public SchoolWithdraw selectById(Long id) {
		 return schoolWithdrawMapper.selectByPrimaryKey(id);
	}

}
