package com.migu.online.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.migu.online.model.system.SysPermission;
import com.migu.online.model.system.SysUser;

import java.util.List;

/**
 * Created by Liaopan on 2018/1/10.
 */
@Repository
@Mapper
public interface SysManagerMapper {

    SysUser selectUserByName(@Param("userName") String userName);

    List<SysPermission> selectPermissionsByUserId(@Param("userId")Long userId);
}
