package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.ExamPlace;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface ExamPlaceMapper extends Mapper<ExamPlace> {
}
