package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.SMS;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface SMSMapper extends Mapper<SMS> {
}
