package com.migu.online.mapper;



import org.springframework.stereotype.Repository;

import com.migu.online.model.Teacher;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface TeacherMapper extends Mapper<Teacher> {
}
