package com.migu.online.controller.tch;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.UserCourseVip;
import com.migu.online.model.system.SysUser;
import com.migu.online.ops.vo.UserCourseVipOpsVo;
import com.migu.online.service.UserCourseVipService;

/**
 * 学生报名VIP课程
 * @author fanyunlong
 *
 */
@Controller
@RequestMapping("/tch/stuvip")
public class StuVipTchController {

	 @Autowired
	 private UserCourseVipService userCourseVipService;
 

    private Long currentTeacherId() {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser.getRelateId();
    }

    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "tch/stuvip/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                 @RequestParam(value = "filters", required = false) String _filters) throws Exception {
        Map<String, String> filters = Maps.newHashMap();
        filters.put("teacherId", currentTeacherId().toString());

        if (_filters != null) {
            Map maps = (Map) JSON.parse(_filters);
            for (Object key : maps.keySet()) {
                filters.put((String) key, (String) maps.get(key));
            }
        }

        Page<UserCourseVipOpsVo> page = (Page<UserCourseVipOpsVo>)userCourseVipService.selectConditions(filters);
        return new ResponsePageData<UserCourseVipOpsVo>(page.getTotal(),page);
    }


    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
        if (editFlag > 0) {
            // show
            modelMap.put("data", userCourseVipService.selectById(id));
        } else {
            // new
            modelMap.put("data", new UserCourseVip());
        }
        modelMap.put("edit", editFlag != 2);
        return "tch/stuvip/edit";

    }

}
