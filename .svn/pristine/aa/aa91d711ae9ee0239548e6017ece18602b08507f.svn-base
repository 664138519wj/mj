package com.migu.online.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.migu.online.model.DrvTempMid;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface DrvTempMidMapper extends Mapper<DrvTempMid> {
	
	public List<DrvTempMid> selectBySchoolCode(Map<String, Object> params);
	
	public int selectCountBySchoolCode(Map<String, Object> params);
}
