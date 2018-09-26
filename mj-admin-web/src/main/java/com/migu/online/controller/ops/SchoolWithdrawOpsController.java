package com.migu.online.controller.ops;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.controller.BaseController;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.SchoolWithdraw;
import com.migu.online.model.system.SysUser;
import com.migu.online.service.SchoolWithdrawService;
import com.migu.online.vo.SchoolWithdrawVo;


@Controller
@RequestMapping("/ops/schoolwithdraw")
public class SchoolWithdrawOpsController extends BaseController{

    @Autowired
    private SchoolWithdrawService schoolWithdrawService;


    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "ops/schoolwithdraw/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
    	SysUser currentUser = getCurrentUser();
    	Long schoolId = null;
    	if (currentUser.getRelateId() != null) {
    		//SysUserOpsVo sysUserOpsVo =sysUserService.selectOpsById(currentUser.getId());
    		 schoolId = currentUser.getRelateId();
    		 if (schoolId == 0L || currentUser.getUserType() == 0) {
    			 // admin 权限
    			 schoolId = null;
    		 }
    	}
        HashMap<String, String> filters = Maps.newHashMap();
        if (null != schoolId) {
            filters.put("school_id", schoolId+"");
        }
        filters.put("page_index", offset.toString());
        filters.put("page_size", limit.toString());
        Page<SchoolWithdrawVo> page = (Page<SchoolWithdrawVo>) schoolWithdrawService.selectByFilter(filters);
        return new ResponsePageData<SchoolWithdrawVo>(page.getTotal(), page);
    }


    @PostMapping("update")
    @ResponseBody
    public String update(@RequestParam(value = "id") Long id,
                         @RequestParam(value = "status") Integer status) throws Exception {
    	SchoolWithdraw schoolWithdraw = schoolWithdrawService.selectById(id);
    	schoolWithdraw.setStatus(status);
    	schoolWithdraw.setUpdateTime(new Date());
    	schoolWithdrawService.createOrUpdate(schoolWithdraw);
        return "success";
    }


}
