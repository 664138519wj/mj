package com.migu.online.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.migu.online.controller.BaseController;
import com.migu.online.controller.response.Result;
import com.migu.online.model.Media;
import com.migu.online.model.Works;
import com.migu.online.model.system.SysUser;
import com.migu.online.service.MediaService;


@Controller
@RequestMapping("/media")
public class MediaController extends BaseController {
    
    @Autowired
    private MediaService mediaService;  

    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
    	
        return "admin/media/index";
    }
    
    

    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     * @throws Exception 
     */
    @GetMapping("edit")
    public String edit(ModelMap modelMap,@RequestParam(value = "id",required = false) Integer id,
    		@RequestParam(value = "flag",defaultValue = "1") Integer editFlag){
    		Media model = new Media();
		    if(editFlag > 0) {
		    	model = mediaService.selectById(id);
		    }
		    modelMap.put("model",model);
		    modelMap.put("edit",editFlag != 2);
		    return "admin/media/edit";
    }
    

    
    @SuppressWarnings("rawtypes")
	@GetMapping("data")
    @ResponseBody
    public Result data( @RequestParam(value = "name",required = false) String name) throws Exception{
    	Map<String,String> params = new HashMap<String, String>();
    	params.put("name", name);
    	
    	List<Media> works = mediaService.getByParams(params);
    	
    	return new Result(works);
    }


    
    
//    @GetMapping("edit")
//    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
//                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
//        return "ops/school/school_withdraw_edit";
//    }
//
    @PostMapping("save")
    @ResponseBody
    public String save(Media model) {
        if (null == model) {
            return "error";
        }
        if (model.getId() != null) {
        	  model.setCreateTime(new Date());
        	  SysUser currentUser = getCurrentUser();
              model.setCreateUser(currentUser.getId());
        }
      
        model.setUpdateTime(new Date());
      
    	
        return mediaService.createOrUpdate(model) >= 1 ? "success" : "error";
    }
    
    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Integer id){
        return mediaService.deleteById(id);
    }

}
