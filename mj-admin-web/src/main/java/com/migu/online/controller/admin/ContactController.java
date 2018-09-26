package com.migu.online.controller.admin;

import java.util.Date;
import java.util.HashMap;
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

import com.github.pagehelper.Page;
import com.migu.online.controller.BaseController;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.Contact;
import com.migu.online.model.system.SysUser;
import com.migu.online.service.ContactService;


@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController {
    
    @Autowired
    private ContactService contactService;  

    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
    	
        return "admin/contact/index";
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
    		Contact model = new Contact();
		    if(editFlag > 0) {
		    	model = contactService.selectById(id);
		    	model.setIntentStr(model.getIntent());
		    }
		    modelMap.put("model",model);
		    modelMap.put("edit",editFlag != 2);
		    return "admin/contact/edit";
    }
    

    
    @SuppressWarnings("rawtypes")
	@GetMapping("data")
    @ResponseBody
    public ResponsePageData data( @RequestParam(value = "page",defaultValue = "0") Integer pageIndex,
			            @RequestParam(value = "limit",defaultValue = "20") Integer pageSize,
			            @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "phone",required = false) String phone) throws Exception{
    	Map<String,String> filters = new HashMap<String, String>();
    	filters.put("name", name);
    	filters.put("phone", phone);
    	
    //	List<Contact> contacts = contactService.getByParams(filters);
    	
    	
    	Page<Contact> page = (Page<Contact>)contactService.selectByPage(pageIndex, pageSize, filters);
        return new ResponsePageData<Contact>(page.getTotal(), page);
    }


    
    
//    @GetMapping("edit")
//    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
//                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
//        return "ops/school/school_withdraw_edit";
//    }
//
    @PostMapping("save")
    @ResponseBody
    public String save(Contact model) {
        if (null == model) {
            return "error";
        }
        if (model.getId() != null) {
        	  model.setCreateTime(new Date());
        	  SysUser currentUser = getCurrentUser();
              model.setCreateUser(currentUser.getId());
        }
      
        model.setUpdateTime(new Date());
      
    	
        return contactService.createOrUpdate(model) >= 1 ? "success" : "error";
    }
    
    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Integer id){
        return contactService.deleteById(id);
    }

}
