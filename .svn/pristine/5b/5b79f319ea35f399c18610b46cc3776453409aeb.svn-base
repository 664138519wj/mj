package com.migu.online.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.online.mapper.SysManagerMapper;
import com.migu.online.model.system.SysPermission;
import com.migu.online.model.system.SysUser;
import com.migu.online.vo.MenuInfo;

/**
 * Created by Liaopan on 2018/1/10.
 */
@Service
public class SysManager {

    @Autowired
    private SysManagerMapper sysManagerMapper;

    public SysUser loadUserByUserName(String userName){
        return sysManagerMapper.selectUserByName(userName);
    }

    public List<MenuInfo> selectPermissionsByUserId(Long userId){
        List<SysPermission> permissions = sysManagerMapper.selectPermissionsByUserId(userId);

        //第一层菜单， parentid 为空的
        List<MenuInfo> menuInfos = new ArrayList<>();
        Optional.ofNullable(permissions).ifPresent(permissionList -> menuInfos.addAll(permissionList.stream()
                .filter(p -> p.getParentId() == null)
                .map(p -> {
                    MenuInfo menuInfo = new MenuInfo(p.getId().toString(),p.getName(),p.getUrl());
                    //第二层菜单，找menu里面parentid 是上层 id的数据
                    List<MenuInfo> children = permissionList.stream()
                            .filter(pt -> pt.getParentId() == p.getId())
                            .map(o -> new MenuInfo(o.getId().toString(),o.getName(),o.getUrl())).collect(Collectors.toList());
                    menuInfo.setChildren(children);
                    return menuInfo;
                }).collect(Collectors.toList())));
        return menuInfos;
    }
}
