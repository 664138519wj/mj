package com.migu.online.controller.tch;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.Teacher;
import com.migu.online.model.TeacherWithdraw;
import com.migu.online.model.system.SysUser;
import com.migu.online.ops.vo.UserCourseOpsVo;
import com.migu.online.service.TeacherService;
import com.migu.online.service.TeacherWithdrawService;
import com.migu.online.service.UserCourseService;
import com.migu.online.vo.TeacherWithdrawVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


@Controller
@RequestMapping("/tch/withdraw")
public class WithdrawTchController {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private TeacherWithdrawService teacherWithdrawService;

    @Autowired
    private TeacherService teacherService;


    private BigDecimal getRemain() {
        HashMap<String, String> filters;
        
        Teacher teacher = teacherService.selectById(currentTeacherId());
        BigDecimal onlineBenefitShare = new BigDecimal(teacher.getOnlineBenefitShare());
        BigDecimal offlineBenefitShare = new BigDecimal(teacher.getOfflineBenefitShare());

        BigDecimal incomeTotal = new BigDecimal(0);
        filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("pay_status", "2");
        filters.put("refund_status", "0");
        filters.put("by_page", "false");
        Page<UserCourseOpsVo> allPages = (Page<UserCourseOpsVo>) userCourseService.selectConditions(filters);
        for (UserCourseOpsVo course : allPages) {
        	if (null != course) {
        		if (course.getCourseType() == UserCourseTypeEnum.online.code) {
                    incomeTotal = incomeTotal.add(onlineBenefitShare.multiply(course.getPrice()));
        		} else if (course.getCourseType() == UserCourseTypeEnum.offline.code) {
                    incomeTotal = incomeTotal.add(offlineBenefitShare.multiply(course.getPrice()));
        		} else {
                    incomeTotal = incomeTotal.add(course.getPrice());
        		}     		
        	}
        }

        BigDecimal withdrawTotal = new BigDecimal(0);
        filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("status_in", "0,1,2");
        filters.put("by_page", "false");
        Page<TeacherWithdrawVo> pages = (Page<TeacherWithdrawVo>) teacherWithdrawService.selectByFilter(filters);
        for (TeacherWithdrawVo withdrawVo : pages) {
            withdrawTotal = withdrawTotal.add(withdrawVo.getAmount());
        }      

        return incomeTotal.subtract(withdrawTotal).setScale(4, BigDecimal.ROUND_HALF_DOWN);
    }


    private Long currentTeacherId() {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser.getRelateId();
    }


    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "tch/withdraw/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        HashMap<String, String> filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("page_index", offset.toString());
        filters.put("page_size", limit.toString());
        Page<TeacherWithdrawVo> page = (Page<TeacherWithdrawVo>) teacherWithdrawService.selectByFilter(filters);
        return new ResponsePageData<TeacherWithdrawVo>(page.getTotal(), page);
    }

    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
        modelMap.put("remain", getRemain());
        return "tch/withdraw/edit";
    }

    @PostMapping("save")
    @ResponseBody
    public String save(TeacherWithdraw model) {
        if (null == model) {
            return "error";
        }
        if (model.getAmount().compareTo(getRemain()) > 0) {
            return "error";
        }
        model.setTeacherId(currentTeacherId());
        model.setStatus(0);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return teacherWithdrawService.createOrUpdate(model) >= 1 ? "success" : "error";
    }


}
