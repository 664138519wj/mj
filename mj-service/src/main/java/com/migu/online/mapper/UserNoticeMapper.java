package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.UserNotice;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserNoticeMapper extends Mapper<UserNotice> {
}