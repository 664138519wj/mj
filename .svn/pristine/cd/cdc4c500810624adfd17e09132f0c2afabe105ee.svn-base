package com.migu.online.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liaopan on 2018/1/23.
 */
@RestController
@RequestMapping("/test")
public class ApiController {

	@RequestMapping("/test")
    public String index(){
		String userName= (String)SecurityUtils.getSubject().getPrincipal(); 
        return userName;
    }
}
