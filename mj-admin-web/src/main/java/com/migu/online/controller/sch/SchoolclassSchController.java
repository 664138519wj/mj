package com.migu.online.controller.sch;

import com.github.pagehelper.Page;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.SchoolClass;
import com.migu.online.service.SchoolClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sch/schoolclass/")
public class SchoolclassSchController {

    @Autowired
    private SchoolClassService schoolclassService;

    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "sch/schoolclass/index";
    }

    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     */
    @GetMapping("edit")
    public String edit(ModelMap modelMap,@RequestParam(value = "id",required = false) Integer id,
                       @RequestParam(value = "flag",defaultValue = "1") Integer editFlag){
    	SchoolClass model = new SchoolClass();
        if(editFlag > 0) {
            model = schoolclassService.getSchoolClass(id);
        }
        modelMap.put("model",model);
        modelMap.put("edit",editFlag != 2);
        return "sch/schoolclass/edit";
    }
    /**
     * 数据处理
     */
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                 @RequestParam(value = "filter",required = false) String filter){
        System.out.println(filter);
        Page<SchoolClass> page = (Page<SchoolClass>)schoolclassService.getSchoolClasses(filter,offset, limit);
        return new ResponsePageData<SchoolClass>(page.getTotal(),page);
    }

    @PostMapping("edit")
    @ResponseBody
    public int edit(@RequestBody SchoolClass model){
    	SchoolClass re = schoolclassService.createOrUpdate(model);
        return re != null? 1:0;
    }

    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Integer id){
        return schoolclassService.delete(id);
    }
}
