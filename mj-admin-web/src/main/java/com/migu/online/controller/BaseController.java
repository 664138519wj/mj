package com.migu.online.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.migu.online.model.system.SysUser;

/**
 * 
 * @author fanyunlong
 *
 */
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    
    public final Integer pageIndex = 1;
    
    public final Integer pageSize = 2;
   
    public SysUser getCurrentUser() {
    	SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
    	return user;
    }
    
    public Long getCurrentUserId() {
    	SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
    	if (null != user) {
    		return user.getId();
    	}
    	return 0L;
    }
}
