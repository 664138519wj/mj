package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.Contact;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface ContactMapper extends Mapper<Contact> {

	//List<Works> selectAll();

}
