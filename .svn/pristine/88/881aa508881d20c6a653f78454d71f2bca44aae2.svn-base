package com.migu.online.controller.ops;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.common.Constants;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.Teacher;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.TeacherService;
import com.migu.online.vo.TeacherDetailVo;

@Controller
@RequestMapping("/ops/teacher/")
public class TeacherOpsController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DrivingSchoolService drivingSchoolService;


    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "ops/teacher/index";
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
    public String edit(ModelMap modelMap,@RequestParam(value = "id",required = false) Long id,
                       @RequestParam(value = "flag",defaultValue = "1") Integer editFlag) throws Exception{
    	String tags = "";
    	if (null != id && editFlag == 2) {
    		// 详情
            modelMap.put("teacher", teacherService.selectOpsOne2(id));
            return "ops/teacher/show";
    	} else if (null != id && editFlag == 1) {
    		Teacher teacher = teacherService.selectById(id);
    		if (null != teacher && StringUtils.isNotEmpty(teacher.getTag())) {
    			tags = teacher.getTag();
    		}
            modelMap.put("teacher", teacher);
    	} else {
            modelMap.put("teacher", new Teacher());
    	}
        modelMap.put("schoolMap",drivingSchoolService.selectSchoolMap());
        modelMap.put("tagList", teacherService.getTagList(tags));
        modelMap.put("edit",editFlag != 2);
        return "ops/teacher/edit";
    }
    /**
     * 数据处理
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page",defaultValue = "0") Integer pageIndex,
                                    @RequestParam(value = "limit",defaultValue = "20") Integer limit,
                                 @RequestParam(value = "filter",required = false) String filter) throws Exception{
        Map<String, String> filters = Maps.newHashMap();
    	if (filter != null) {
            Map maps = (Map) JSON.parse(filter);
            for (Object key : maps.keySet()) {
            	filters.put((String) key, (String) maps.get(key));
            }
        }
    	Page<TeacherDetailVo> page = (Page<TeacherDetailVo>)teacherService.selectByPage(pageIndex, limit, filters);
        return new ResponsePageData<TeacherDetailVo>(page.getTotal(), page);
    }

    @PostMapping("save")
    @ResponseBody
    public String save(Teacher model){
    	if (null == model) {
    		return "error";
    	}
    	if (StringUtils.isEmpty(model.getAvator())) {
    		model.setAvator(Constants.USER_DEFAULT_AVATAR);
    	}
    	if (model.getId() != null && model.getId() > 0) {
    		// 更新
    		Teacher record = teacherService.selectById(model.getId());
    		if (null == record) {
    		  return "error";
    		}
    		record.setTeachingAddress(model.getTeachingAddress());
    		record.setSchoolId(model.getSchoolId());
    		record.setRecommand(model.getRecommand());
    		record.setName(model.getName());
    		record.setIntroduce(model.getIntroduce());
    		record.setTag(model.getTag());
    		record.setAvator(model.getAvator());
    		record.setUpdateTime(new Date());
    		record.setTeachingAge(model.getTeachingAge());
            record.setIdNo(model.getIdNo());
            record.setTeacherNo(model.getTeacherNo());
            record.setMobile(model.getMobile());
            record.setOnlineBenefitShare(model.getOnlineBenefitShare());
            record.setOfflineBenefitShare(model.getOfflineBenefitShare());
            record.setDrivingAge(model.getDrivingAge());
            return teacherService.createOrUpdate(record) >= 1 ? "success" : "error";
    	} else {
    		model.setSort(1);
        	model.setCreateTime(new Date());
        	model.setUpdateTime(new Date());
        	model.setIsDelete(0);
    	}
        return teacherService.createOrUpdate(model) >= 1 ? "success" : "error";
    }

    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Long id){
        return teacherService.deleteById(id);
    }
    
    @GetMapping("approve")
    @ResponseBody
    public int appprove(@RequestParam("id") Long id){
        return teacherService.dealTeacherRegister(id, true, "");
    }
    
    @RequestMapping("deny")
    @ResponseBody
    public int deny(@RequestParam("id") Long id, @RequestParam("reason") String reason){
        return teacherService.dealTeacherRegister(id, false, reason);
    }
    
    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     * @throws Exception 
     */
    @GetMapping("toDeny")
    public String toDeny(ModelMap modelMap, @RequestParam(value = "id",required = false) Integer id){
    	modelMap.put("id", id);
		return "ops/teacher/deny";
    }
    
}
