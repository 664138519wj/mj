package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.CourseOffline;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface CourseOfflineMapper extends Mapper<CourseOffline> {
}
