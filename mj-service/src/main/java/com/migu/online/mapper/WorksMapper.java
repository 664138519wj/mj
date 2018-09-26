package com.migu.online.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.migu.online.model.Works;
import com.migu.online.ops.vo.SchoolLessonVo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface WorksMapper extends Mapper<Works> {

	//List<Works> selectAll();

}
