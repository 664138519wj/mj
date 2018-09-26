package com.migu.online.controller.ops;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.TeacherWithdraw;
import com.migu.online.service.TeacherWithdrawService;
import com.migu.online.vo.TeacherWithdrawVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;


@Controller
@RequestMapping("/ops/teacherwithdraw")
public class TeacherWithdrawOpsController {

    @Autowired
    private TeacherWithdrawService teacherWithdrawService;


    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "ops/teacherwithdraw/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        HashMap<String, String> filters = Maps.newHashMap();
        filters.put("page_index", offset.toString());
        filters.put("page_size", limit.toString());
        Page<TeacherWithdrawVo> page = (Page<TeacherWithdrawVo>) teacherWithdrawService.selectByFilter(filters);
        return new ResponsePageData<TeacherWithdrawVo>(page.getTotal(), page);
    }


    @PostMapping("update")
    @ResponseBody
    public String update(@RequestParam(value = "id") Long id,
                         @RequestParam(value = "status") Integer status) throws Exception {
        TeacherWithdraw teacherWithdraw = teacherWithdrawService.selectById(id);
        teacherWithdraw.setStatus(status);
        teacherWithdraw.setUpdateTime(new Date());
        teacherWithdrawService.createOrUpdate(teacherWithdraw);
        return "success";
    }


}
