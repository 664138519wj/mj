package com.migu.online.controller.ops;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.migu.online.controller.BaseController;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.DrvTempMid;
import com.migu.online.model.system.SysUser;
import com.migu.online.service.DrvTempMidService;

@Controller
@RequestMapping("/ops/schoolHisStu/")
public class SchoolHisStudentController extends BaseController{
	
    @Autowired
    private DrvTempMidService drvTempMidService;  
       
    /**
	 * ------页面跳转 start-----
	 */

	@GetMapping("index")
	public String index(ModelMap modelMap) {
		return "ops/school/school_his_student";
	}

	/**
	 * 编辑，新增，查看 方法，。
	 * 
	 * @param modelMap
	 * @param id
	 * @param editFlag
	 *            edit = 1, new = 0 , show = 2
	 * @return
	 */
	@GetMapping("edit")
	public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "flag", defaultValue = "1") Integer editFlag) {
		DrvTempMid model = new DrvTempMid();
		if (editFlag > 0) {
			model = drvTempMidService.selectById(id);
		}
		modelMap.put("data", model);
		modelMap.put("edit", editFlag != 2);
		return "ops/school/school_his_detail";
	}

	/**
	 * ------页面跳转 end------
	 */

	/**
	 * 数据处理
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("data")
	@ResponseBody
	public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") Integer limit,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "idNo", required = false) String idNo,
			@RequestParam(value = "mobile", required = false) String mobile) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("idNo", idNo);
        param.put("mobile", mobile);
        SysUser user = super.getCurrentUser();
		PageInfo page = drvTempMidService.selectByPage(offset, limit, param, Integer.parseInt(user.getRelateId() + ""));
		return new ResponsePageData<DrvTempMid>(page.getTotal(), page.getList());
	}

}
