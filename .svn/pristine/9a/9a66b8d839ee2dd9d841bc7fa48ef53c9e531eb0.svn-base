package com.migu.online.controller.tch;

import com.migu.online.common.Constants;
import com.migu.online.model.Teacher;
import com.migu.online.model.system.SysUser;
import com.migu.online.ops.vo.TeacherOpsVo;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/tch/myinfo")
public class MyInfoTchController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DrivingSchoolService drivingSchoolService;
    
    @Autowired
    private CourseOnlineService courseOnlineService;

    private Long currentTeacherId() {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser.getRelateId();
    }

    @GetMapping("index")
    public String index(ModelMap modelMap) throws Exception {
        TeacherOpsVo teacher = teacherService.selectOpsOne(currentTeacherId());
        modelMap.put("teacher", teacher);
        return "tch/myinfo/index";
    }

    /**
     * 编辑，新增，查看 方法，。
     *
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, show = 2
     * @return
     * @throws Exception
     */
    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
        String tags = "";
        TeacherOpsVo teacher = teacherService.selectOpsOne(currentTeacherId());
        if (null != teacher && StringUtils.isNotEmpty(teacher.getTag())) {
            tags = teacher.getTag();
        }
        modelMap.put("teacher", teacher);
        modelMap.put("schoolMap", drivingSchoolService.selectSchoolMap());
        modelMap.put("tagList", teacherService.getTagList(tags));
        return "tch/myinfo/edit";
    }

    @PostMapping("save")
    @ResponseBody
    public String save(Teacher model) {
        if (null == model) {
            return "error";
        }
        if (StringUtils.isEmpty(model.getAvator())) {
            model.setAvator(Constants.USER_DEFAULT_AVATAR);
        }
        Teacher record = teacherService.selectById(currentTeacherId());
        if (null == record) {
            return "error";
        }
//        record.setTeachingAddress(model.getTeachingAddress());
//        record.setSchoolId(model.getSchoolId());
//        record.setRecommand(model.getRecommand());
        if (!model.getName().equals(record.getName())) {
        	// 教师名字修改，同步在线视频操作
        	courseOnlineService.updateTeacherNameByTeacherId(model.getName(), record.getId() + "");
        }
        record.setName(model.getName());       
//        record.setIntroduce(model.getIntroduce());
        record.setTag(model.getTag());
        record.setAvator(model.getAvator());
        record.setUpdateTime(new Date());
        record.setTeachingAge(model.getTeachingAge());
        record.setIdNo(model.getIdNo());
        record.setTeacherNo(model.getTeacherNo());
        record.setMobile(model.getMobile());
        return teacherService.createOrUpdate(record) >= 1 ? "success" : "error";
    }

}
