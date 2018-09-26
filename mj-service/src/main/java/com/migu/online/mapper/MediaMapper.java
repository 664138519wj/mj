package com.migu.online.mapper;

import org.springframework.stereotype.Repository;

import com.migu.online.model.Media;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface MediaMapper extends Mapper<Media> {

	//List<Works> selectAll();

}
