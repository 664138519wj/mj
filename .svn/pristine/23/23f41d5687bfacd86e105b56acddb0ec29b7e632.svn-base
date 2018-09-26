package com.migu.online.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.migu.online.mapper.*;
import com.migu.online.model.*;
import com.migu.online.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherWithdrawService {

    @Autowired
    private TeacherWithdrawMapper teacherWithdrawMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    public Page<TeacherWithdrawVo> selectByFilter(Map<String, String> filters) {
        boolean byPage = true;
        int pageIndex = 0;
        int pageSize = 0;
        Example example = new Example(TeacherWithdraw.class);
        Criteria criteria = example.createCriteria();
        for (String key : filters.keySet()) {
            switch (key) {
                case "teacher_id":
                    Long teacherId = Long.parseLong(filters.get(key));
                    criteria.andEqualTo("teacherId", teacherId);
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

        Page<TeacherWithdrawVo> pageVo = new Page<TeacherWithdrawVo>();
        if (byPage) {
            PageHelper.startPage(pageIndex, pageSize, true);
        }
        List<TeacherWithdraw> pageList = (List<TeacherWithdraw>) teacherWithdrawMapper.selectByExample(example);
        for (TeacherWithdraw tw : pageList) {
            TeacherWithdrawVo vo = new TeacherWithdrawVo();
            try {
                BeanUtils.copyProperties(vo, tw);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String teacherName = teacherMapper.selectByPrimaryKey(tw.getTeacherId()).getName();
            vo.setTeacherName(teacherName);
            pageVo.add(vo);
        }

        if (byPage) {
            pageVo.setTotal(((Page) pageList).getTotal());
        }
        return pageVo;
    }

    public int createOrUpdate(TeacherWithdraw model) {
        if (null != model.getId() && model.getId() > 0) {
            return teacherWithdrawMapper.updateByPrimaryKey(model);
        } else {
            return teacherWithdrawMapper.insert(model);
        }
    }

    public TeacherWithdraw selectById(Long id) {
        return teacherWithdrawMapper.selectByPrimaryKey(id);
    }

}
