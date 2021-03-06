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

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.system.SysUser;
import com.migu.online.ops.vo.CourseOfflineOpsVo;
import com.migu.online.service.CourseOfflineService;


@Controller
@RequestMapping("/tch/offlinecourse")
public class OfflineCourseController {

    @Autowired
    private CourseOfflineService courseOfflineService;

    private Long currentTeacherId() {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser.getRelateId();
    }

    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "tch/offlinecourse/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit) throws Exception {
        Map<String, String> filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("page_index", offset.toString());
        filters.put("page_size", limit.toString());

        Page<CourseOfflineOpsVo> page = courseOfflineService.selectByFilter(filters);
        return new ResponsePageData<CourseOfflineOpsVo>(page.getTotal(), page);
    }


}
