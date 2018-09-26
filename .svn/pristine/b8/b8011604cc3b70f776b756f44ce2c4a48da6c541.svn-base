
package com.migu.online.controller.ops;

import java.math.BigDecimal;
import java.util.Date;

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
import com.migu.online.common.UserCourseTypeEnum;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.AppointmentManage;
import com.migu.online.model.UserAppointment;
import com.migu.online.model.UserCourse;
import com.migu.online.service.AppointmentManageService;
import com.migu.online.service.UserAppointmentService;
import com.migu.online.service.UserCourseService;
import com.migu.online.service.UserNoticeService;
import com.migu.online.service.pay.AliPayService;
import com.migu.online.service.pay.WXPayService;

@Controller
@RequestMapping("/ops/userappointment/")
public class UserAppointmentOpsController {

    @Autowired
    private UserAppointmentService userAppointmentService;
    @Autowired
	private AppointmentManageService appointmentManageService;
    @Autowired
	private UserCourseService userCourseService;
    @Autowired
	private AliPayService aliPayService;
    @Autowired
	private WXPayService wxPayService;
    @Autowired
	private UserNoticeService userNoticeService;

    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "ops/userappointment/index";
    }
    
    /**
     *  ------页面跳转 start-----
     */
    @GetMapping("score_index")
    public String scoreIndex(ModelMap modelMap){
        return "ops/userappointment/score_index";
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
    	UserAppointment model = new UserAppointment();
        if(editFlag > 0) {
            model = userAppointmentService.selectById(id);
        }
        modelMap.put("userAppointment",model);
        modelMap.put("edit",editFlag != 2);
        return "ops/userappointment/edit";
    }
    
    
    /**
     * 编辑，新增，查看 方法，。
     * @param modelMap
     * @param id
     * @param editFlag edit = 1, new = 0 , show = 2
     * @return
     */
    @GetMapping("score_edit")
    public String scoreEdit(ModelMap modelMap,@RequestParam(value = "id",required = false) Long id,
                       @RequestParam(value = "flag",defaultValue = "1") Integer editFlag){
    	UserAppointment model = new UserAppointment();
        if(editFlag > 0) {
            model = userAppointmentService.selectById(id);
        }
        modelMap.put("userAppointment",model);
        modelMap.put("edit",editFlag != 2);
        return "ops/userappointment/score_edit";
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
                                 @RequestParam(value = "status",required = false) Integer status,
                                 @RequestParam(value = "scoreStatus",required = false) Integer scoreStatus){
        Page<UserAppointment> page = (Page<UserAppointment>)userAppointmentService.selectConditionByPage(offset, limit, mobile, status, scoreStatus);
        return new ResponsePageData<UserAppointment>(page.getTotal(),page);
    }
    
    @PostMapping("save")
	@ResponseBody
	public String save(UserAppointment model) {
		if (null == model) {
			return "error";
		}

		if (model.getId() != null && model.getId() > 0) {
			// 更新
			UserAppointment record = userAppointmentService.selectById(model.getId());
			if (null == record) {
				return "error";
			}
			record.setScore(model.getScore());
			return userAppointmentService.createOrUpdate(record) >= 1 ? "success" : "error";
		} else {
			model.setUpdateTime(new Date());
			model.setIsDelete(0);			
		}
		return userAppointmentService.createOrUpdate(model) >= 1 ? "success" : "error";
	}

 
    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Long id){
        return userAppointmentService.deleteById(id);
    }
    
    @GetMapping("approve")
    @ResponseBody
    public String approve(@RequestParam("id") Long id){
    	UserAppointment record = userAppointmentService.selectById(id);
		if (null == record) {
			return "error";
		} else if ((record.getPayType() == 0 && record.getPayStatus() != 1) || record.getStatus() != 0) {
			return "status error";
		}
		// 后台通过预约
		record.setStatus(1);
		if (record.getPayType() == 1) {
			record.setPayStatus(1); // 线下已付款
		}
		record.setUpdateTime(new Date());
        Integer flag =  userAppointmentService.createOrUpdate(record);
        if (flag < 1) {
        	return "error";
        }
        // 短信通知
     	userNoticeService.dealAppointMessage2(1, record);
        return "success";
        
    }
    
    @GetMapping("deny")
    @ResponseBody
    public String deny(@RequestParam("id") Long id){
    	UserAppointment record = userAppointmentService.selectById(id);
		if (null == record) {
			return "error";
		} else if (record.getStatus() != 0) {
			return "status error";
		}
		// 后台通过预约
		record.setStatus(2);
		record.setUpdateTime(new Date());
		int flag = userAppointmentService.createOrUpdate(record);
		if (flag < 1) {
			return "error";
		}				
		UserCourse uc = userCourseService.selectCourseByUser(record.getId(), record.getUserId(), UserCourseTypeEnum.appointment.code);
		if (null != uc && uc.getPayStatus() == 2 && record.getPayType() == 0) {
			// 线上已付款的，原路退款
			if (uc.getTradeType().equals("ali_web") || uc.getTradeType().equals("ali_app") ) {
				// 支付宝退款
				aliPayService.refund(uc.getOutTradeNo(), uc.getPrice().toString(), "预约失败");
			} else {
				// 微信退款
				String priceStr = uc.getPrice().multiply(new BigDecimal(100)).toString();
				if (priceStr.contains(".")) {
		    		priceStr = priceStr.substring(0, priceStr.indexOf("."));
		    	}
				wxPayService.doRefund(uc.getOutTradeNo(), priceStr);
			}
		} else {
			// 线下付款，插入线下退款记录
		}
		// 自动退款
		// 库存回补
		AppointmentManage data = appointmentManageService.selectById(record.getAppointId());
		appointmentManageService.increaseExamPlaceCount(data);
		// 拒绝通知
     	userNoticeService.dealAppointMessage2(2, record);
        return "success";
    }
}
