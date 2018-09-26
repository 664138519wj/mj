package com.migu.online.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.migu.online.model.DrivingSchoolLesson;
import com.migu.online.ops.vo.SchoolLessonVo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface DrivingSchoolLessonMapper extends Mapper<DrivingSchoolLesson> {

	List<SchoolLessonVo> selectAllSchoolLessionByPage(Map<String, Object> params);

	int selectAllSchoolLessionCnt(Map<String, Object> params);
}
