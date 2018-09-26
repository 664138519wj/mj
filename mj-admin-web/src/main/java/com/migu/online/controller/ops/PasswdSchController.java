package com.migu.online.controller.ops;

import com.migu.online.model.system.SysUser;
import com.migu.online.service.SysUserManageService;
import com.migu.online.utils.MD5Util;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/ops/schoolpasswd")
public class PasswdSchController {

    @Autowired
    private SysUserManageService sysUserService;

    private Long currentUserID() {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser.getId();
    }


    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "ops/school/modify_pwd";
    }

    @PostMapping("update")
    @ResponseBody
    public String update(ModelMap modelMap, @RequestParam(value = "old_passwd") String oldPasswd,
                      @RequestParam(value = "new_passwd") String newPasswd) throws Exception {
        SysUser currentUser = sysUserService.selectById(currentUserID());
        if (!currentUser.getPassword().equals(MD5Util.getMD5(oldPasswd))) {
            return "error";
        }
        currentUser.setPassword(MD5Util.getMD5(newPasswd));
        sysUserService.createOrUpdate(currentUser);
        return "success";
    }


}
