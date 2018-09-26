package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.Common;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface CommonMapper extends Mapper<Common> {
}
