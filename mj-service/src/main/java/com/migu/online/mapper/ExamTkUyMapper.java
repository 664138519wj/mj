package com.migu.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.migu.online.model.ExamTkUy;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface ExamTkUyMapper extends Mapper<ExamTkUy> {
	
	List<String> queryFreeExamIds(@Param("quType")String quType, @Param("examType")String examType
			, @Param("kemu") String kemu, @Param("licType") Integer licType);
}
