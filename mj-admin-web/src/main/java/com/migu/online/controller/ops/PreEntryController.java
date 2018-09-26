package com.migu.online.controller.ops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.migu.online.controller.BaseController;
import com.migu.online.model.DrivingSchool;
import com.migu.online.model.system.SysUser;
import com.migu.online.service.DrivingSchoolService;

@Controller
@RequestMapping("/")
public class PreEntryController extends BaseController{
	
    @Autowired
    private DrivingSchoolService drivingSchoolService;
    
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("pre_entry")
    public String index(ModelMap modelMap){
    	
    	SysUser currentUser = getCurrentUser();
    	Long schoolId = null;
    	String schoolName = null;
    	if (currentUser.getRelateId() != null) {
    		//SysUserOpsVo sysUserOpsVo =sysUserService.selectOpsById(currentUser.getId());
    		 schoolId = currentUser.getRelateId();
    		 DrivingSchool drivingSchool = drivingSchoolService.selectById(schoolId.intValue());
    		 modelMap.put("schoolId", drivingSchool.getId());
    	     modelMap.put("schoolName", drivingSchool.getNameL());
    	}
        return "pre_entry";
    }
    
  
}
