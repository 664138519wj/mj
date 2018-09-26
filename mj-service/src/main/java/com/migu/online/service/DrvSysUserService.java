package com.migu.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.online.mapper.DrvSysUserMapper;
import com.migu.online.model.DrvSysUser;


@Service
public class DrvSysUserService {

    @Autowired
    private DrvSysUserMapper drvSysUserMapper;
 
    public List<DrvSysUser> selectAll(){
        return drvSysUserMapper.selectAll();
    }
   
}
