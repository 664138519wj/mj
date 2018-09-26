package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.common.OnlineTypeEnum;
import com.migu.online.mapper.PrivateTutorMapper;
import com.migu.online.mapper.TeacherMapper;
import com.migu.online.mapper.UserCourseMapper;
import com.migu.online.model.PrivateTutor;
import com.migu.online.model.Teacher;
import com.migu.online.model.UserCourse;
import com.migu.online.ops.vo.PrivateTutorOpsVo;
import com.migu.online.ops.vo.StuPrivateTutorOpsVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class PrivateTutorService {

    @Autowired
    private PrivateTutorMapper privateTutorMapper;    
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private UserCourseMapper userCourseMapper;
    
    /**
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public Page<PrivateTutorOpsVo> selectConditionsByPage(Integer pageIndex, Integer pageSize, Map<String, String> filters) throws Exception {
        Page<PrivateTutorOpsVo> result = new Page<PrivateTutorOpsVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);

        //单表自定义查询
        Example example = new Example(PrivateTutor.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (filters.size() > 0) {
            for (String key : filters.keySet()) {
                String value = filters.get(key);
                switch (key) {
                    case "title":
                        criteria.andLike("title", "%" + value + "%");
                        break;
                    case "teacherId":
                        criteria.andEqualTo("teacherId", value);
                        break;
                    case "kemu":
                        criteria.andEqualTo("type", value);
                        break;
                    case "payType":
                    	if (value.equals("2")) {
                    		// 付费
                            criteria.andGreaterThan("price", 0);
                    	} else {
                    		// 免费
                            criteria.andEqualTo("price", 0);
                    	}
                    	break;
                    default:
                        break;
                }
            }
        }
        example.setOrderByClause("update_time desc");
        Page<PrivateTutor> pageList = (Page<PrivateTutor>) privateTutorMapper.selectByExample(example);
        for (PrivateTutor data : pageList) {
        	PrivateTutorOpsVo vo = new PrivateTutorOpsVo();
            if (null != data) {
                BeanUtils.copyProperties(vo, data);
                // 查询平台
                Teacher record = new Teacher();
                record.setId(data.getTeacherId());
                record = teacherMapper.selectOne(record);
                vo.setTeacherName(record.getName());
                vo.setKemu(OnlineTypeEnum.getEnumByCode(data.getType()).desc);
                vo.setScore(record.getScore());
                result.add(vo);
            }
        }
        result.setTotal(pageList.getTotal());
        return result;
    }
    
    /**
     * 后台显示详情
     *
     * @param id
     * @return
     */
    public PrivateTutorOpsVo selectOpsOne(Long id) throws Exception {
    	PrivateTutorOpsVo vo = new PrivateTutorOpsVo();
        PrivateTutor data = privateTutorMapper.selectByPrimaryKey(id);
        if (null != data) {
            BeanUtils.copyProperties(vo, data);
            // 查询平台
            Teacher record = new Teacher();
            record.setId(data.getTeacherId());
            record = teacherMapper.selectOne(record);
            vo.setTeacherName(record.getName());
            vo.setKemu(OnlineTypeEnum.getEnumByCode(data.getType()).desc);
        }
        return vo;
    }
 	
   
    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	PrivateTutor record = privateTutorMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return privateTutorMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public PrivateTutor selectById(Long id) {
    	return privateTutorMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(PrivateTutor model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return privateTutorMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return privateTutorMapper.insert(model);
    	}
    }
    
    /**
     * 推荐课程
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public Page<PrivateTutorOpsVo> selectRecommandByPage(Integer pageIndex, Integer pageSize, Long excludeId) throws Exception {
        Page<PrivateTutorOpsVo> result = new Page<PrivateTutorOpsVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);

        //单表自定义查询
        Example example = new Example(PrivateTutor.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        criteria.andCondition("recommand =1");
        if (null != excludeId && excludeId > 0) {
        	// 排除id
        	criteria.andNotEqualTo("id", excludeId);
        }
        example.setOrderByClause("update_time desc");
        Page<PrivateTutor> pageList = (Page<PrivateTutor>) privateTutorMapper.selectByExample(example);
        for (PrivateTutor data : pageList) {
        	PrivateTutorOpsVo vo = new PrivateTutorOpsVo();
            if (null != data) {
                BeanUtils.copyProperties(vo, data);
                // 查询平台
                Teacher record = new Teacher();
                record.setId(data.getTeacherId());
                record = teacherMapper.selectOne(record);
                if (null != record) {
                	vo.setTeacherName(record.getName());
                    vo.setKemu(OnlineTypeEnum.getEnumByCode(data.getType()).desc);
                    vo.setScore(record.getScore());
                }                
                result.add(vo);
            }
        }
        result.setTotal(pageList.getTotal());
        return result;
    }
    
    /**
     * 老师发布课程
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<PrivateTutorOpsVo> selectByTeacher(Long teacherId) throws Exception {
    	List<PrivateTutorOpsVo> result = new ArrayList<PrivateTutorOpsVo>();
        
        //单表自定义查询
        Example example = new Example(PrivateTutor.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        criteria.andCondition("teacher_id =", teacherId);       
        example.setOrderByClause("update_time desc");
        List<PrivateTutor> pageList = privateTutorMapper.selectByExample(example);
        for (PrivateTutor data : pageList) {
        	PrivateTutorOpsVo vo = new PrivateTutorOpsVo();
            if (null != data) {
                BeanUtils.copyProperties(vo, data);
                // 查询平台
                Teacher record = new Teacher();
                record.setId(data.getTeacherId());
                record = teacherMapper.selectOne(record);
                vo.setTeacherName(record.getName());
                vo.setKemu(OnlineTypeEnum.getEnumByCode(data.getType()).desc);
                vo.setScore(record.getScore());
                result.add(vo);
            }
        }
        return result;
    }
    
    /**
     * 用户线上已购买私教课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public Page<StuPrivateTutorOpsVo> selectPayTutorByPage(Integer pageIndex, Integer pageSize, Map<String, String> filters)
            throws Exception {
        Page<StuPrivateTutorOpsVo> result = new Page<StuPrivateTutorOpsVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);

        // 单表自定义查询
        Example example = new Example(UserCourse.class);
        Criteria criteria = example.createCriteria();
        if (filters.size() > 0) {
            for (String key : filters.keySet()) {
                String value = filters.get(key);
                switch (key) {
                    case "teacherId":
                        criteria.andEqualTo("publishCourseUserId", value);
                        break;
                    case "mobile":
                        criteria.andEqualTo("userName", value);
                        break;
                    default:
                        break;
                }
            }
        }   
        criteria.andCondition("pay_status = 2");
        criteria.andCondition("course_type = 6");
        example.setOrderByClause("update_time desc");
        Page<UserCourse> list = (Page<UserCourse>)userCourseMapper.selectByExample(example);
        for (UserCourse data : list) {
        	StuPrivateTutorOpsVo vo = new StuPrivateTutorOpsVo();
            if (null != data) {
                BeanUtils.copyProperties(vo, data);
                PrivateTutor tutor = privateTutorMapper.selectByPrimaryKey(data.getCourseId());
                if (null != tutor) {
                	// 查询平台
                    Teacher record = new Teacher();
                    record.setId(tutor.getTeacherId());
                    record = teacherMapper.selectOne(record);
                    vo.setTeacherName(record.getName());
                    vo.setKemu(OnlineTypeEnum.getEnumByCode(tutor.getType()).desc);
                    vo.setAddress(tutor.getAddress());
                    vo.setBeginTime(tutor.getBeginTime());
                    vo.setTitle(tutor.getTitle());
                }                
                result.add(vo);
            }
        }
        result.setTotal(list.getTotal());
        return result;
    }
 
}
