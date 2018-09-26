package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.UserAppointment;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserAppointmentMapper extends Mapper<UserAppointment> {
}
