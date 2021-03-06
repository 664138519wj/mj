package com.migu.online.controller.ops;

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
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.VipCourse;
import com.migu.online.service.VipCourseService;

@Controller
@RequestMapping("/ops/vipcourse/")
public class VipCourseOpsController {

    @Autowired
    private VipCourseService vipcourseService;
   
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "ops/vipcourse/index";
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
    	VipCourse model = new VipCourse();
        if(editFlag > 0) {
            model = vipcourseService.selectById(id);
        }
        modelMap.put("data",model);
        modelMap.put("edit",editFlag);
        if (editFlag == 2) {
        	return "ops/vipcourse/show";
        } 
        return "ops/vipcourse/edit";
    }
    
    /**
     * 数据处理
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page",defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                 @RequestParam(value = "kemu",required = false) Integer kemu) throws Exception{
        Page<VipCourse> page = (Page<VipCourse>)vipcourseService.selectByPage(kemu, offset, limit);
        return new ResponsePageData<VipCourse>(page.getTotal(),page);
    }
    
    @PostMapping("save")
	@ResponseBody
	public String save(VipCourse model, String startTimeStr) {
		if (null == model) {
			return "error";
		}
		if (model.getId() != null && model.getId() > 0) {
			// 更新
			VipCourse record = vipcourseService.selectById(model.getId());
			if (null == record) {
				return "error";
			}
			record.setPrice(model.getPrice());
			record.setContent(model.getContent());
			return vipcourseService.createOrUpdate(record) >= 1 ? "success" : "error";
		} else {
		}
		return vipcourseService.createOrUpdate(model) >= 1 ? "success" : "error";
	}

 
    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Long id){
        return vipcourseService.deleteById(id);
    }
}
