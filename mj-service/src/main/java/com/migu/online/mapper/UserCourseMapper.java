package com.migu.online.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.migu.online.model.UserCourse;
import com.migu.online.ops.vo.UserSchoolCourseVo;
import com.migu.online.vo.UserDrivingLessonVo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserCourseMapper extends Mapper<UserCourse> {

	List<Long> queryPayCourse(@Param("userId")Long userId, @Param("courseType") int courseType);

	List<Long> queryOnlineCourseByTeacher(@Param("teacherId")Long teacherId);

	List<Long> queryOfflineCourseByTeacher(@Param("teacherId")Long teacherId);

	List<Long> queryCourseByTeacher(@Param("teacherId")Long teacherId);
	
	List<Long> queryPrivateTutorCourseByTeacher(@Param("teacherId")Long teacherId);

	List<UserCourse> queryCourseByCourseIdAndType(@Param("courseId")Long courseId, @Param("courseType") int courseType);

	/* 购买课程信息*/
	List<UserCourse> queryCourseByUserIdAndType(@Param("userId")Long userId, @Param("courseType") int courseType);


	Long insertAndGetId(@Param("uc")UserCourse userCourse);

	/**
	 * 查询驾校报名学员信息
	 * @param params
	 * @return
	 */
	List<UserSchoolCourseVo> selectUserSchoolCourse(Map<String, Object> params);

	int selectUserSchoolCourseCnt(Map<String, Object> params);

	int selectSchoolStudentCourseCnt(Map<String, Object> params);

	List<UserSchoolCourseVo> selectSchoolStudentCourse(Map<String, Object> params);

	BigDecimal selectSchoolTotalIncome(Map<String, Object> params);

	/**
	 * 查询学员已报名驾校
	 * @param params
	 * @return
	 */
	List<UserDrivingLessonVo> selectSignUserSchoolCourse(@Param("userId") Long userId);

}
