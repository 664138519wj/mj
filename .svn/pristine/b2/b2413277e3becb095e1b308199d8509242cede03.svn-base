package com.migu.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.migu.online.model.CourseOnline;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface CourseOnlineMapper extends Mapper<CourseOnline> {
	public List<CourseOnline> getCustomerCourseOnline(@Param("customerid") Integer customerid);
	
	public int updateTeacherNameById(@Param("teacherName") String teacherName, @Param("teacherId") String teacherId);
}
