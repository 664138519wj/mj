package com.migu.online.controller.ops;

import java.util.Map;

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
import com.migu.online.model.PrivateTutor;
import com.migu.online.ops.vo.StuPrivateTutorOpsVo;
import com.migu.online.service.PrivateTutorService;
import com.migu.online.service.TeacherService;

/**
 * 学生报名私教课程
 * @author fanyunlong
 *
 */
@Controller
@RequestMapping("/ops/stuTutor")
public class StuTutorOpsController {

    @Autowired
    private PrivateTutorService privateTutorService;
    @Autowired
    private TeacherService teacherService;
 
    @GetMapping("index")
    public String index(ModelMap modelMap) {
    	modelMap.put("teacherList", teacherService.selectAll());
        return "ops/stuTutor/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                 @RequestParam(value = "filters", required = false) String _filters) throws Exception {
        Map<String, String> filters = Maps.newHashMap();
        if (_filters != null) {
            Map maps = (Map) JSON.parse(_filters);
            for (Object key : maps.keySet()) {
                filters.put((String) key, (String) maps.get(key));
            }
        }

        Page<StuPrivateTutorOpsVo> page = (Page<StuPrivateTutorOpsVo>) privateTutorService
                .selectPayTutorByPage(offset, limit, filters);
        return new ResponsePageData<StuPrivateTutorOpsVo>(page.getTotal(), page);
    }


    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
        if (editFlag > 0) {
            // show
            modelMap.put("data", privateTutorService.selectOpsOne(id));
        } else {
            // new
            modelMap.put("data", new PrivateTutor());
        }
        modelMap.put("edit", editFlag != 2);
        return "ops/stuTutor/edit";

    }

}
