package com.migu.online.controller.ops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.User;
import com.migu.online.service.UserService;

@Controller
@RequestMapping("/ops/user/")
public class UserOpsController {

    @Autowired
    private UserService userService;

    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "ops/user/index";
    }

    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     */
    @GetMapping("edit")
    public String edit(ModelMap modelMap,@RequestParam(value = "id",required = false) Long id,
                       @RequestParam(value = "flag",defaultValue = "1") Integer editFlag){
    	User model = new User();
        if(editFlag > 0) {
            model = userService.selectById(id);
        }
        modelMap.put("user",model);
        modelMap.put("edit",editFlag != 2);
        return "ops/user/edit";
    }
    /**
     * 数据处理
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page",defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                 @RequestParam(value = "mobile",required = false) String mobile,
                                 @RequestParam(value = "realName",required = false) String realName){
        Page<User> page = (Page<User>)userService.selectConditionByPage(offset, limit, mobile, realName);
        return new ResponsePageData<User>(page.getTotal(),page);
    }

 
    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Long id){
        return userService.deleteById(id);
    }
}