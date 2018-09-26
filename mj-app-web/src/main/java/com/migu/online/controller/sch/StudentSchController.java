package com.migu.online.controller.sch;

import com.github.pagehelper.Page;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.Student;
import com.migu.online.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sch/student/")
public class StudentSchController {

    @Autowired
    private StudentService studentService;

    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "sch/student/index";
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
    	Student model = new Student();
        if(editFlag > 0) {
            model = studentService.getStudent(id);
        }
        modelMap.put("student",model);
        modelMap.put("edit",editFlag != 2);
        return "sch/student/edit";
    }
    /**
     * 数据处理
     */
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                 @RequestParam(value = "filter",required = false) String filter){
        System.out.println(filter);
        Page<Student> page = (Page<Student>)studentService.getStudents(filter,offset, limit);
        return new ResponsePageData<Student>(page.getTotal(),page);
    }

    @PostMapping("edit")
    @ResponseBody
    public int edit(@RequestBody Student model){
    	Student re = studentService.createOrUpdate(model);
        return re != null? 1:0;
    }

    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Integer id){
        return studentService.delete(id);
    }
}
