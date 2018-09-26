package com.migu.online.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.common.OnlineTypeEnum;
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.mapper.CourseOfflineMapper;
import com.migu.online.mapper.CourseOnlineMapper;
import com.migu.online.mapper.DrivingSchoolLessonMapper;
import com.migu.online.mapper.PrivateTutorMapper;
import com.migu.online.mapper.TeacherMapper;
import com.migu.online.mapper.UserCourseMapper;
import com.migu.online.model.CourseOffline;
import com.migu.online.model.CourseOnline;
import com.migu.online.model.DrivingSchoolLesson;
import com.migu.online.model.PrivateTutor;
import com.migu.online.model.Teacher;
import com.migu.online.model.UserCourse;
import com.migu.online.ops.vo.PrivateTutorOpsVo;
import com.migu.online.ops.vo.UserCourseOpsVo;
import com.migu.online.ops.vo.UserSchoolCourseVo;
import com.migu.online.utils.DateUtil;
import com.migu.online.utils.NumberUtil;
import com.migu.online.vo.CourseOfflineVo;
import com.migu.online.vo.CourseOnlineVo;
import com.migu.online.vo.UserDrivingLessonVo;
import com.migu.online.vo.UserOfflineCourseVo;
import com.migu.online.vo.UserOnlineCourseVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserCourseService {

    @Autowired
    private UserCourseMapper userCourseMapper;
    @Autowired
    private CourseOnlineMapper courseOnlineMapper;
    @Autowired
    private CourseOfflineMapper courseOfflineMapper;
    @Autowired
    private DrivingSchoolLessonMapper drivingSchoolLessonMapper;   
    @Autowired
    private PrivateTutorMapper privateTutorMapper;
    @Autowired
    private TeacherMapper teacherMapper;


    /**
     * 用户线上课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<UserOnlineCourseVo> selectOnlineByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        List<UserOnlineCourseVo> result = new ArrayList<UserOnlineCourseVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(UserCourse.class);
        example.createCriteria().andCondition("is_delete = 0").andCondition("course_type = 1")
                .andCondition("pay_status = 2").andCondition("user_id=", userId);
        List<UserCourse> pageList = userCourseMapper.selectByExample(example);
        for (UserCourse data : pageList) {
            UserOnlineCourseVo vo = new UserOnlineCourseVo();
            // 查询线上课程明细
            if (null != data) {
                BeanUtils.copyProperties(vo, data);
                CourseOnline online = new CourseOnline();
                online.setId(data.getCourseId());
                online = courseOnlineMapper.selectOne(online);
                vo.setType(online.getType());
                vo.setContent(online.getContent());
                vo.setImagePath(online.getImagePath());
                vo.setPlayNumber(online.getPlayNumber());
                vo.setTitle(online.getTitle());
                String time = NumberUtil.addZeroForNum(online.getHour().toString(), 2) + ":"
                        + NumberUtil.addZeroForNum(online.getMinute().toString(), 2) + ":"
                        + NumberUtil.addZeroForNum(online.getSecond().toString(), 2);
                vo.setTime(time);
                vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATAFORMAT_STR));
                result.add(vo);
            }
        }
        return result;
    }

    /**
     * 用户线下课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<UserOfflineCourseVo> selectOfflineByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        List<UserOfflineCourseVo> result = new ArrayList<UserOfflineCourseVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(UserCourse.class);
        example.createCriteria().andCondition("is_delete = 0").andCondition("course_type = 2")
                .andCondition("pay_status = 2").andCondition("user_id=", userId);
        List<UserCourse> pageList = userCourseMapper.selectByExample(example);
        for (UserCourse data : pageList) {
            UserOfflineCourseVo vo = new UserOfflineCourseVo();
            // 查询线上课程明细
            if (null != data) {
                BeanUtils.copyProperties(vo, data);
                CourseOffline offline = new CourseOffline();
                offline.setId(data.getCourseId());
                offline = courseOfflineMapper.selectOne(offline);
                vo.setContent(offline.getContent());
                vo.setImagePath(offline.getImagePath());
                vo.setTitle(offline.getTitle());
                vo.setStartTime(DateUtil.dateToDateString(offline.getStartTime(), DateUtil.DATAFORMAT_STR));
                vo.setAddress(offline.getAddress());
                result.add(vo);
            }
        }
        return result;
    }

    /**
     * 用户线上未购买付费课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<CourseOnlineVo> selectUnpayOnlineByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        // 查出已支付列表
        List<Long> userPayCourseList = userCourseMapper.queryPayCourse(userId, UserCourseTypeEnum.online.code);

        List<CourseOnlineVo> result = new ArrayList<CourseOnlineVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(CourseOnline.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        criteria.andCondition("is_hot_and_charge >= 2");

        if (null != userPayCourseList && userPayCourseList.size() > 0) {
            criteria.andNotIn("id", userPayCourseList);
        }
        List<CourseOnline> list = courseOnlineMapper.selectByExample(example);
        for (CourseOnline data : list) {
            CourseOnlineVo vo = new CourseOnlineVo();
            BeanUtils.copyProperties(vo, data);
            String time = NumberUtil.addZeroForNum(data.getHour().toString(), 2) + ":"
                    + NumberUtil.addZeroForNum(data.getMinute().toString(), 2) + ":"
                    + NumberUtil.addZeroForNum(data.getSecond().toString(), 2);
            vo.setTime(time);
            vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATAFORMAT_STR));
            result.add(vo);
        }
        return result;
    }

    /**
     * 用户线上已购买付费课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<CourseOnlineVo> selectPayOnlineByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        // 查出已支付列表
        List<Long> userPayCourseList = userCourseMapper.queryPayCourse(userId, UserCourseTypeEnum.online.code);

        List<CourseOnlineVo> result = new ArrayList<CourseOnlineVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(CourseOnline.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        criteria.andCondition("is_hot_and_charge >= 2");

        if (null != userPayCourseList && userPayCourseList.size() > 0) {
            criteria.andIn("id", userPayCourseList);
        } else {
            return null;
        }
        List<CourseOnline> list = courseOnlineMapper.selectByExample(example);
        for (CourseOnline data : list) {
            CourseOnlineVo vo = new CourseOnlineVo();
            BeanUtils.copyProperties(vo, data);
            String time = NumberUtil.addZeroForNum(data.getHour().toString(), 2) + ":"
                    + NumberUtil.addZeroForNum(data.getMinute().toString(), 2) + ":"
                    + NumberUtil.addZeroForNum(data.getSecond().toString(), 2);
            vo.setTime(time);
            vo.setUpdateTime(DateUtil.dateToDateString(data.getUpdateTime(), DateUtil.DATAFORMAT_STR));
            result.add(vo);
        }
        return result;
    }

    /**
     * 用户线下未购买课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<CourseOfflineVo> selectUnpayOfflineByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        // 查出已支付列表
        List<Long> userPayCourseList = userCourseMapper.queryPayCourse(userId, UserCourseTypeEnum.offline.code);

        List<CourseOfflineVo> result = new ArrayList<CourseOfflineVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(CourseOffline.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (null != userPayCourseList && userPayCourseList.size() > 0) {
            criteria.andNotIn("id", userPayCourseList);
        }
        //单表自定义查询
        List<CourseOffline> pageList = courseOfflineMapper.selectByExample(example);
        for (CourseOffline data : pageList) {
            CourseOfflineVo vo = new CourseOfflineVo();
            BeanUtils.copyProperties(vo, data);
            vo.setStartTime(DateUtil.dateToDateString(data.getStartTime(), DateUtil.DATAFORMAT_STR));
            result.add(vo);
        }
        return result;
    }

    /**
     * 用户线下已购买课程查询 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<CourseOfflineVo> selectPayOfflineByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        // 查出已支付列表
        List<Long> userPayCourseList = userCourseMapper.queryPayCourse(userId, UserCourseTypeEnum.offline.code);

        List<CourseOfflineVo> result = new ArrayList<CourseOfflineVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(CourseOffline.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (null != userPayCourseList && userPayCourseList.size() > 0) {
            criteria.andIn("id", userPayCourseList);
        } else {
            return null;
        }
        //单表自定义查询
        List<CourseOffline> pageList = courseOfflineMapper.selectByExample(example);
        for (CourseOffline data : pageList) {
            CourseOfflineVo vo = new CourseOfflineVo();
            BeanUtils.copyProperties(vo, data);
            vo.setStartTime(DateUtil.dateToDateString(data.getStartTime(), DateUtil.DATAFORMAT_STR));
            result.add(vo);
        }
        return result;
    }

    /**
     * 已报名驾校列表
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws Exception
     */
    public List<UserDrivingLessonVo> selectUserDrivingCourse(Long userId)
            throws Exception {
        return userCourseMapper.selectSignUserSchoolCourse(userId);
    }

    /**
     * 后台分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws Exception
     */
    public Page<UserCourseOpsVo> selectConditionByPage(Integer pageIndex, Integer pageSize, String mobile, Integer courseType) {
        Page<UserCourseOpsVo> pageVo = new Page<UserCourseOpsVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, true);

        //单表自定义查询
        Example example = new Example(UserCourse.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andLike("userName", "%" + mobile + "%");
        }
        if (null != courseType) {
            criteria.andCondition("course_type = ", courseType);
        }
        example.setOrderByClause("update_time desc");
        Page<UserCourse> pageList = (Page<UserCourse>) userCourseMapper.selectByExample(example);
        for (UserCourse uc : pageList) {
            UserCourseOpsVo vo = new UserCourseOpsVo();
            try {
                BeanUtils.copyProperties(vo, uc);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String courseName = "";
            if (uc.getCourseType() == UserCourseTypeEnum.online.code) {
                CourseOnline record = new CourseOnline();
                record.setId(uc.getCourseId());
                ;
                record = courseOnlineMapper.selectOne(record);
                courseName = record.getTitle();
            } else if (uc.getCourseType() == UserCourseTypeEnum.offline.code) {
                CourseOffline record = new CourseOffline();
                record.setId(uc.getCourseId());
                ;
                record = courseOfflineMapper.selectOne(record);
                courseName = record.getTitle();
            } else if (uc.getCourseType() == UserCourseTypeEnum.lesson.code) {
                DrivingSchoolLesson record = new DrivingSchoolLesson();
                record.setId(uc.getCourseId());
                record = drivingSchoolLessonMapper.selectOne(record);
                courseName = record.getName();
            }
            vo.setCourseName(courseName);
            pageVo.add(vo);
        }
        pageVo.setTotal(pageList.getTotal());
        return pageVo;
    }


    /**
     * 后台查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws Exception
     */

    public Page<UserCourseOpsVo> selectConditions(Map<String, String> filters) {
        boolean byPage = true;
        int pageIndex = 0;
        int pageSize = 0;
        //单表自定义查询
        Example example = new Example(UserCourse.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        for (String key : filters.keySet()) {
            switch (key) {
                case "course_type":
                    criteria.andEqualTo("courseType", Integer.parseInt(filters.get(key)));
                    break;
                case "teacher_id":
                    Long teacherId = Long.parseLong(filters.get("teacher_id"));
                    String courseType = filters.getOrDefault("course_type", "course_type:1-线上 2-线下，如果不过滤，则下面两个if都会执行");
                    if(courseType.equals("1")){
                        List<Long> courseIds = userCourseMapper.queryOnlineCourseByTeacher(teacherId);
                        if (courseIds.isEmpty()){
                            criteria.andCondition("1 = 2");
                        }else{
                            criteria.andIn("courseId", courseIds);
                        }
                    }else if(courseType.equals("2")){
                        List<Long> courseIds = userCourseMapper.queryOfflineCourseByTeacher(teacherId);
                        if (courseIds.isEmpty()){
                            criteria.andCondition("1 = 2");
                        }else{
                            criteria.andIn("courseId", courseIds);
                        }
                    }else if(courseType.equals("6")){
                    	// 私教课程
                        List<Long> courseIds = userCourseMapper.queryPrivateTutorCourseByTeacher(teacherId);
                        if (courseIds.isEmpty()){
                            criteria.andCondition("1 = 2");
                        }else{
                            criteria.andIn("courseId", courseIds);
                        }
                    }else{
                        List<Long> ids = userCourseMapper.queryCourseByTeacher(teacherId);
                        if (ids.isEmpty()){
                            criteria.andCondition("1 = 2");
                        }else{
                            criteria.andIn("id", ids);
                        }
                    }
                    break;
                case "pay_status":
                    int payStatus = Integer.parseInt(filters.get(key));
                    criteria.andEqualTo("payStatus", payStatus);
                    break;
                case "refund_status":
                    int refundStatus = Integer.parseInt(filters.get(key));
                    criteria.andEqualTo("refundStatus", refundStatus);
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

        Page<UserCourseOpsVo> pageVo = new Page<UserCourseOpsVo>();
        if (byPage) {
            PageHelper.startPage(pageIndex, pageSize, true);
        }
        List<UserCourse> pageList = (List<UserCourse>) userCourseMapper.selectByExample(example);
        for (UserCourse uc : pageList) {
            UserCourseOpsVo vo = new UserCourseOpsVo();
            try {
                BeanUtils.copyProperties(vo, uc);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String courseName = "";
            if (uc.getCourseType() == UserCourseTypeEnum.online.code) {
                CourseOnline record = new CourseOnline();
                record.setId(uc.getCourseId());
                ;
                record = courseOnlineMapper.selectOne(record);
                courseName = record.getTitle();
            } else if (uc.getCourseType() == UserCourseTypeEnum.offline.code) {
                CourseOffline record = new CourseOffline();
                record.setId(uc.getCourseId());
                ;
                record = courseOfflineMapper.selectOne(record);
                courseName = record.getTitle();
            } else if (uc.getCourseType() == UserCourseTypeEnum.lesson.code) {
                DrivingSchoolLesson record = new DrivingSchoolLesson();
                record.setId(uc.getCourseId());
                record = drivingSchoolLessonMapper.selectOne(record);
                courseName = record.getName();
            } else if (uc.getCourseType() == UserCourseTypeEnum.tutor.code) {
                PrivateTutor record = new PrivateTutor();
                record = privateTutorMapper.selectByPrimaryKey(uc.getCourseId());
                courseName = record.getTitle();
            }
            vo.setCourseName(courseName);
            pageVo.add(vo);
        }
        if (byPage) {
            pageVo.setTotal(((Page) pageList).getTotal());
        }
        return pageVo;
    }


    /**
     * selectOneByUser
     *
     * @return
     */
    public UserCourse selectOneByUser(Long id, Long userId) {
        UserCourse record = new UserCourse();
        record.setId(id);
        record.setIsDelete(0);
        record.setUserId(userId);
        return userCourseMapper.selectOne(record);
    }

    /**
     * selectOneByUser
     *
     * @return
     */
    public UserCourse selectCourseByUser(Long courseId, Long userId, Integer courseType) {
        UserCourse record = new UserCourse();
        record.setIsDelete(0);
        record.setUserId(userId);
        record.setCourseId(courseId);
        record.setCourseType(courseType);
        return userCourseMapper.selectOne(record);
    }
    
    /**
     * 同一驾校报名 是否有未支付校验
     * @return
     */
    public List<UserCourse> selectSchoolUnpayCourse(Long courseId, Long userId) {
    	Example example = new Example(UserCourse.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");        
        criteria.andCondition("course_type = 3");
        criteria.andCondition("user_id=", userId);
        criteria.andCondition("course_id=", courseId);
        criteria.andNotEqualTo("payStatus", 3);
        return userCourseMapper.selectByExample(example);
    }

    /**
     * 用户已支付订单
     *
     * @return
     */
    public UserCourse selectPayCourseByUser(Long courseId, Long userId, Integer courseType) {
        UserCourse record = new UserCourse();
        record.setIsDelete(0);
        record.setUserId(userId);
        record.setCourseId(courseId);
        record.setCourseType(courseType);
        record.setPayStatus(2);
        return userCourseMapper.selectOne(record);
    }
    
    /**
     * 用户已支付订单
     *
     * @return
     */
    public List<UserCourse> selectPayCourseListByUser(Long userId, Integer courseType) {
    	Example example = new Example(UserCourse.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("user_id = ", userId);
        criteria.andCondition("is_delete = 0");
        criteria.andCondition("course_type = ", courseType);
        criteria.andCondition("pay_status = 2");
        return userCourseMapper.selectByExample(example);
    }

    /**
     * 根据订单号查询记录
     *
     * @return
     */
    public UserCourse selectCourseByOutOrderNo(String outTradeNo) {
        UserCourse record = new UserCourse();
        record.setIsDelete(0);
        record.setOutTradeNo(outTradeNo);
        return userCourseMapper.selectOne(record);
    }

    /**
     * 改&插
     *
     * @param model
     * @return
     */
    public int createOrUpdate(UserCourse model) {
        if (null != model.getId() && model.getId() > 0) {
            // update
            return userCourseMapper.updateByPrimaryKeySelective(model);
        } else {
            return userCourseMapper.insert(model);
        }
    }

    public List<UserSchoolCourseVo> selectUserSchoolCourse(Map<String, Object> params) {
        List<UserSchoolCourseVo> result = userCourseMapper.selectUserSchoolCourse(params);
        return result;
    }

    public int selectUserSchoolCourseCnt(Map<String, Object> params) {
        int cnt = userCourseMapper.selectUserSchoolCourseCnt(params);
        return cnt;
    }

    public int selectSchoolStudentCourseCnt(Map<String, Object> params) {
        int cnt = userCourseMapper.selectSchoolStudentCourseCnt(params);
        return cnt;
    }

    public List<UserSchoolCourseVo> selectSchoolStudentCourse(Map<String, Object> params) {
        List<UserSchoolCourseVo> result = userCourseMapper.selectSchoolStudentCourse(params);
        return result;
    }

    /**
     * 查询学校收益总额
     *
     * @param schoolId
     * @return
     */
    public BigDecimal selectSchoolTotalIncome(Long schoolId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schoolId", schoolId);
        return userCourseMapper.selectSchoolTotalIncome(params);
    }

    public BigDecimal selectSchoolWithdraw(Long schoolId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schoolId", schoolId);
        return userCourseMapper.selectSchoolTotalIncome(params);
    }

    public UserCourse selectById(Long id) {
        return userCourseMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 单一用户只能报名一个驾校一个驾照类型
     * @return
     */
    public List<UserCourse> selectSignSchoolCourse(Long userId) {
    	Example example = new Example(UserCourse.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");        
        criteria.andCondition("course_type = 3");
        criteria.andCondition("user_id=", userId);
        criteria.andCondition("pay_status = 2");
        criteria.andNotEqualTo("refundStatus", 2);
        return userCourseMapper.selectByExample(example);
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
    public List<PrivateTutorOpsVo> selectPayTutorByPage(Integer pageIndex, Integer pageSize, Long userId)
            throws Exception {
        // 查出已支付列表
        List<Long> userPayCourseList = userCourseMapper.queryPayCourse(userId, UserCourseTypeEnum.tutor.code);

        List<PrivateTutorOpsVo> result = new ArrayList<PrivateTutorOpsVo>();
        // 单表分页
        PageHelper.startPage(pageIndex, pageSize, false);

        // 单表自定义查询
        Example example = new Example(PrivateTutor.class);
        Criteria criteria = example.createCriteria();

        if (null != userPayCourseList && userPayCourseList.size() > 0) {
            criteria.andIn("id", userPayCourseList);
        } else {
            return null;
        }
        List<PrivateTutor> list = privateTutorMapper.selectByExample(example);
        for (PrivateTutor data : list) {
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
}
