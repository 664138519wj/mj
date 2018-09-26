package com.migu.online.controller.tch;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.Teacher;
import com.migu.online.model.system.SysUser;
import com.migu.online.ops.vo.UserCourseOpsVo;
import com.migu.online.service.TeacherService;
import com.migu.online.service.TeacherWithdrawService;
import com.migu.online.service.UserCourseService;
import com.migu.online.vo.TeacherWithdrawVo;


@Controller
@RequestMapping("/tch/income")
public class IncomeTchController {
    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private TeacherWithdrawService teacherWithdrawService;

    @Autowired
    private TeacherService teacherService;

    private Long currentTeacherId() {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser.getRelateId();
    }

    private BigDecimal getTotalIncome(Integer courseType) {
        BigDecimal total = new BigDecimal(0);
        HashMap<String, String> filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("pay_status", "2");
        filters.put("refund_status", "0");
        filters.put("by_page", "false");
        if (courseType != null) {
            filters.put("course_type", courseType.toString());
        }
        Page<UserCourseOpsVo> allPages = (Page<UserCourseOpsVo>) userCourseService.selectConditions(filters);
        for (UserCourseOpsVo course : allPages) {
            total = total.add(course.getPrice());
        }
        return total;
    }

    private BigDecimal getTotalWithdraw() {
        BigDecimal total = new BigDecimal(0);
        HashMap<String, String> filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("status_in", "0,1,2");
        filters.put("by_page", "false");
        Page<TeacherWithdrawVo> allPages = (Page<TeacherWithdrawVo>) teacherWithdrawService.selectByFilter(filters);
        for (TeacherWithdrawVo withdrawVo : allPages) {
            total = total.add(withdrawVo.getAmount());
        }
        return total;
    }


    @GetMapping("index")
    public String index(ModelMap modelMap) {
        BigDecimal onlineIncome = getTotalIncome(1);
        BigDecimal offlineIncome = getTotalIncome(2);
        BigDecimal privateTutorIncome = getTotalIncome(6);
        BigDecimal withdraw = getTotalWithdraw();
        Teacher teacher = teacherService.selectById(currentTeacherId());
        BigDecimal offlineBenefitShare = new BigDecimal(teacher.getOfflineBenefitShare());
        BigDecimal onlineBenefitShare = new BigDecimal(teacher.getOnlineBenefitShare());
        BigDecimal remain = (onlineIncome.multiply(onlineBenefitShare)).add(offlineIncome.multiply(offlineBenefitShare)).add(privateTutorIncome).subtract(withdraw);

        modelMap.put("offline_income", offlineIncome.setScale(4, BigDecimal.ROUND_FLOOR));
        modelMap.put("online_income", onlineIncome.setScale(4, BigDecimal.ROUND_FLOOR));
        modelMap.put("privateTutor_income", privateTutorIncome.setScale(4, BigDecimal.ROUND_FLOOR));

        modelMap.put("withdraw", withdraw.setScale(4, BigDecimal.ROUND_FLOOR));
        modelMap.put("remain", remain.setScale(4, BigDecimal.ROUND_FLOOR));
        modelMap.put("onlineBenefitShare", onlineBenefitShare.setScale(4, BigDecimal.ROUND_FLOOR));
        modelMap.put("offlineBenefitShare", offlineBenefitShare.setScale(4, BigDecimal.ROUND_FLOOR));
        return "tch/income/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                 @RequestParam(value = "course_type", required = false) Integer courseType) {
        HashMap<String, String> filters = Maps.newHashMap();
        filters.put("teacher_id", currentTeacherId().toString());
        filters.put("pay_status", "2");
        filters.put("refund_status", "0");
        filters.put("page_index", offset.toString());
        filters.put("page_size", limit.toString());
        if (courseType != null) {
            filters.put("course_type", courseType.toString());
        }
        Page<UserCourseOpsVo> page = (Page<UserCourseOpsVo>) userCourseService.selectConditions(filters);
        return new ResponsePageData<UserCourseOpsVo>(page.getTotal(), page);
    }
}
