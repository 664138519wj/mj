package com.migu.online.controller.ops;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.ExamPlace;
import com.migu.online.ops.vo.ExamPlaceOpsVo;
import com.migu.online.service.DrivingSchoolService;
import com.migu.online.service.ExamPlaceService;

/**
 * Created by Liaopan on 2018/1/25.
 */
@Controller
@RequestMapping("/ops/examplace/")
public class ExamPlaceOpsController {

	@Autowired
	private ExamPlaceService examPlaceService;
	@Autowired
	private DrivingSchoolService drivingSchoolService;


	/**
	 * ------页面跳转 start-----
	 */

	@GetMapping("index")
	public String index(ModelMap modelMap) {
		return "ops/examplace/index";
	}

	/**
	 * 编辑，新增，查看 方法，。
	 * 
	 * @param modelMap
	 * @param id
	 * @param editFlag
	 *            edit = 1, new = 0 , show = 2
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@GetMapping("edit")
	public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws IllegalAccessException, InvocationTargetException {
		ExamPlace model = new ExamPlace();
		if (editFlag > 0) {
			model = examPlaceService.selectOpsById(id);
		}
		modelMap.put("data", model);
		modelMap.put("areaList", drivingSchoolService.selectAreaList());
		modelMap.put("edit", editFlag != 2);
		return "ops/examplace/edit";
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
			@RequestParam(value = "filter", required = false) String filter) throws Exception {

		Page<ExamPlaceOpsVo> page = (Page<ExamPlaceOpsVo>) examPlaceService.selectConditonByPage2(filter, offset, limit);
		
		return new ResponsePageData<ExamPlaceOpsVo>(page.getTotal(), page);
	}

	@PostMapping("save")
	@ResponseBody
	public String save(ExamPlace model) {
		if (null == model) {
			return "error";
		}

		if (model.getId() != null && model.getId() > 0) {
			// 更新
			ExamPlace record = examPlaceService.selectById(model.getId());
			if (null == record) {
				return "error";
			}
			record.setAddress(model.getAddress());
			record.setName(model.getName());
			record.setAreaId(model.getAreaId());
			return examPlaceService.createOrUpdate(record) >= 1 ? "success" : "error";
		} 
		return examPlaceService.createOrUpdate(model) >= 1 ? "success" : "error";
	}
}
