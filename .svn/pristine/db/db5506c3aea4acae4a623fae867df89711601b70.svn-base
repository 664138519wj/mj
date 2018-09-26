package com.migu.online.controller.ops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.system.SysRole;
import com.migu.online.model.system.SysUser;
import com.migu.online.model.system.SysUserRole;
import com.migu.online.ops.vo.SysUserOpsVo;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.SysUserManageService;
import com.migu.online.service.TeacherService;
import com.migu.online.utils.MD5Util;
import com.migu.online.vo.TeacherDetailVo;

@Controller
@RequestMapping("/ops/sysusermanage/")
public class SysUserManageController {

    @Autowired
    private SysUserManageService sysUserService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DrivingSchoolService drivingSchoolService;

    /**
     * ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "ops/sysusermanage/index";
    }

    /**
     * 编辑，新增，查看 方法，。
     *
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     */
    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
        SysUserOpsVo user = new SysUserOpsVo();
        if (editFlag > 0) {
            user = sysUserService.selectOpsById(id);
            if (user.getUserType() == 0) {
                modelMap.put("relation", "管理员");
            } else if (user.getUserType() == 1) {
                drivingSchoolService.selectOpsOne(user.getRelateId().intValue());
                modelMap.put("relation", drivingSchoolService.selectOpsOne(user.getRelateId().intValue()).getNameL());
            } else if (user.getUserType() == 2) {
                TeacherDetailVo teacherDetailVo = teacherService.selectOne(user.getRelateId());
                modelMap.put("relation", teacherDetailVo.getName());
            }

        } else {
            // new
            List<SysRole> sysRoleList = sysUserService.getSysRoles();
            for (SysRole role : sysRoleList) {
                role.setAvailable(false);
            }
            user.setRoles(sysRoleList);
        }
        if (editFlag < 2) {
            // todo:没时间写select all了，先凑合,反正估计也不会有这么多教练
            modelMap.put("teachers", teacherService.selectByPage(1, 9999999));
            modelMap.put("schools", drivingSchoolService.selectByPage(1, 9999999, "")); // todo: 要是有这么多驾校我直播吃翔
        }
        modelMap.put("user", user);
        modelMap.put("edit", editFlag != 2);
        modelMap.put("flag", editFlag);
        return "ops/sysusermanage/edit";
    }

    /**
     * 数据处理
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                 @RequestParam(value = "filter", required = false) String filter) {
        Page<SysUser> page = (Page<SysUser>) sysUserService.selectConditionByPage(offset, limit, filter);
        return new ResponsePageData<SysUser>(page.getTotal(), page);
    }

    @PostMapping("save")
    @ResponseBody
    public String save(SysUserOpsVo model) {
        if (null == model || Strings.isNullOrEmpty(model.getRoleStr())) {
            return "error";
        }
        int roleId = Integer.parseInt(model.getRoleStr());
        if (roleId > 3 || roleId < 0) {
            return "error";
        }
        boolean isNewUser = model.getId() == null;
        // 用户名唯一校验
        List<SysUser>  dataList = sysUserService.checkExitUserName(model.getUserName(), model.getId());
        if (null != dataList && dataList.size() > 0) {
        	// 已存在名字
        	return "用户名已存在";
        }
        SysUser user = isNewUser ? new SysUser() : sysUserService.selectById(model.getId());
        if (user == null) {
            return "error";
        }
        user.setUserName(model.getUserName());
        // 判断密码是否修改
        if (!isNewUser) {
        	// 编辑
        	if (!model.getPassword().equals(user.getPassword())) {
                user.setPassword(MD5Util.getMD5(model.getPassword()));
        	}
        } else {
            user.setPassword(MD5Util.getMD5(model.getPassword()));
        }
        user.setNickName(model.getNickName());
        user.setUserType(roleId - 1); // 角色ID和用户类型ID刚好差1
        user.setRelateId(model.getRelateId());
        if (isNewUser) {
            user.setState(1);
        }
        sysUserService.createOrUpdate(user);
        // 插入权限
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(user.getId());
        sysUserRole.setRoleId(roleId);
        sysUserService.updateUserRole(Lists.newArrayList(sysUserRole), user.getId());
        // TODO 修改完权限，需要刷新用户权限
        return "success";
    }


    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Long id) {
        return sysUserService.deleteById(id);
    }
}
