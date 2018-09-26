package com.migu.online.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.mapper.TeacherCommentMapper;
import com.migu.online.mapper.TeacherMapper;
import com.migu.online.model.PrivateTutor;
import com.migu.online.model.Teacher;
import com.migu.online.model.TeacherComment;
import com.migu.online.model.User;
import com.migu.online.model.UserCourse;
import com.migu.online.vo.TeacherCommentVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class TeacherCommentService {

    @Autowired
    private TeacherCommentMapper teacherCommentMapper;
    @Autowired
    private UserService userService;
    @Autowired
	private TeacherMapper teacherMapper;
    @Autowired
	private UserCourseService userCourseService;
    @Autowired
	private PrivateTutorService privateTutorService;

    /**
     * 推荐列表
     *
     * @param type
     * @param isRecommended
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Page<TeacherCommentVo> selectByPage(Long teacherId, Integer pageIndex, Integer pageSize) throws Exception {
        Page<TeacherCommentVo> result = new Page<>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);

        //单表自定义查询
        Example example = new Example(TeacherComment.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (null != teacherId) {
            criteria.andCondition("teacher_id = ", teacherId);
        }
        example.setOrderByClause("update_time desc");
        Page<TeacherComment> pageList = (Page<TeacherComment>)teacherCommentMapper.selectByExample(example);
        result.setTotal(pageList.getTotal());
        for (TeacherComment data : pageList) {
            TeacherCommentVo vo = new TeacherCommentVo();
            BeanUtils.copyProperties(vo, data);
            User user = userService.selectCachUserById(vo.getUserId());
            if (null != user) {
            	vo.setUserName(user.getUserName());
            	vo.setUserImage(user.getAvatar());
            }
            result.add(vo);
        }
        return result;
    }
    
    /**
     * 推荐列表
     *
     * @param type
     * @param isRecommended
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<TeacherComment> selectByUserIdAndTeacherId(Long teacherId, Long userId) {
        //单表自定义查询
        Example example = new Example(TeacherComment.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (null != teacherId) {
            criteria.andCondition("teacher_id = ", teacherId);
        }
        if (null != teacherId) {
            criteria.andCondition("user_id = ", userId);
        }
        example.and(criteria);
        example.setOrderByClause("update_time desc");        
        return teacherCommentMapper.selectByExample(example);
    }


    
    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int deleteById(Long id) {
        TeacherComment record = teacherCommentMapper.selectByPrimaryKey(id);
        if (null != record) {
            record.setIsDelete(1);
            return teacherCommentMapper.updateByPrimaryKey(record);
        }
        return 0;
    }

    /**
     * 查
     *
     * @param id
     * @return
     */
    public TeacherComment selectById(Long id) {
        return teacherCommentMapper.selectByPrimaryKey(id);
    }

    /**
     * 改&插
     *
     * @param model
     * @return
     */
    public int createOrUpdate(TeacherComment model) {
        if (null != model.getId() && model.getId() > 0) {
            // update
            return teacherCommentMapper.updateByPrimaryKey(model);
        } else {
            // add
            return teacherCommentMapper.insert(model);
        }
    }
    
    /**
     * 计算老师综合评分
     *
     * @param type
     * @param isRecommended
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public void calculateTeacherScore(Long teacherId) {
    	Integer score = teacherCommentMapper.selectAvgScoreByTeacherId(teacherId);
    	if (null != score && score >= 0) {
    		Teacher teacher = new Teacher();
        	teacher.setId(teacherId);
        	teacher.setScore(score);
            teacherMapper.updateByPrimaryKeySelective(teacher);
    	}      
    }
    
    /**
     * check user is buy vip course
     *
     * @param type
     * @param isRecommended
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public boolean checkUserIsBuyVipCourse(Long teacherId, Long userId) {
        List<UserCourse> list = userCourseService.selectPayCourseListByUser(userId, UserCourseTypeEnum.tutor.code);
        if (null != list && list.size() > 0) {
        	for (UserCourse data : list) {
        		PrivateTutor tutor = privateTutorService.selectById(data.getCourseId());
        		if (null != tutor) {
        			if (tutor.getTeacherId() == teacherId) {
        				return true;
        			}
        		}
        	}
        }
        return false;
    }


}
