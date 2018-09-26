package com.migu.online.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.migu.online.model.TeacherComment;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface TeacherCommentMapper extends Mapper<TeacherComment> {
	
	Integer selectAvgScoreByTeacherId(@Param("teacherId")Long teacherId);
 
}
