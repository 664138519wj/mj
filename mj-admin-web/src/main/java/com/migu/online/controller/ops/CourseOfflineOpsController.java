package com.migu.online.controller.ops;

import java.util.Date;

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

import com.github.pagehelper.Page;
import com.migu.online.common.Constants;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.CourseOffline;
import com.migu.online.ops.vo.CourseOfflineOpsVo;
import com.migu.online.service.CourseOfflineService;
import com.migu.online.service.PlatformNetworkService;
import com.migu.online.utils.DateUtil;

/**
 * Created by Liaopan on 2018/1/25.
 */
@Controller
@RequestMapping("/ops/courseoffline/")
public class CourseOfflineOpsController {

	@Autowired
	private CourseOfflineService courseOfflineService;

	@Autowired
	private PlatformNetworkService platformNetworkService;

	/**
	 * ------页面跳转 start-----
	 */

	@GetMapping("index")
	public String index(ModelMap modelMap) {

		return "ops/courseoffline/index";
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
	public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "flag", defaultValue = "1") Integer editFlag) {
		if (editFlag == 1) {
			// edit
			modelMap.put("courseOffline", courseOfflineService.selectById(id));
		} else if (editFlag == 2) {
			// show
			try {
				modelMap.put("courseOffline", courseOfflineService.selectOpsOne(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// new
			modelMap.put("courseOffline", new CourseOffline());
		}
		try {
			modelMap.put("platList", platformNetworkService.selectAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.put("edit", editFlag != 2);
		return "ops/courseoffline/edit";
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

		Page<CourseOfflineOpsVo> page = (Page<CourseOfflineOpsVo>) courseOfflineService.selectConditionByPage(offset,
				limit, filter);
		return new ResponsePageData<CourseOfflineOpsVo>(page.getTotal(), page);
	}

	@PostMapping("save")
	@ResponseBody
	public String save(CourseOffline model, String startTimeStr) {
		if (null == model) {
			return "error";
		}
		if (StringUtils.isNotEmpty(startTimeStr)) {
			model.setStartTime(DateUtil.getDate(startTimeStr, DateUtil.DATAFORMAT_STR));
		}
		if (StringUtils.isEmpty(model.getImagePath())) {
			model.setImagePath(Constants.USER_DEFAULT_AVATAR);
		}
		if (model.getId() != null && model.getId() > 0) {
			// 更新
			CourseOffline record = courseOfflineService.selectById(model.getId());
			if (null == record) {
				return "error";
			}
			record.setAddress(model.getAddress());
			record.setPrice(model.getPrice());;
			record.setPlatId(model.getPlatId());
			record.setRecommand(model.getRecommand());
			record.setTitle(model.getTitle());
			record.setContent(model.getContent());
			record.setTeachers(model.getTeachers());
			record.setImagePath(model.getImagePath());
			record.setUpdateTime(new Date());
			record.setStartTime(model.getStartTime());
			record.setHours(model.getHours());
			return courseOfflineService.createOrUpdate(record) >= 1 ? "success" : "error";
		} else {
			model.setSort(1);
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setIsDelete(0);
		}
		return courseOfflineService.createOrUpdate(model) >= 1 ? "success" : "error";
	}

	@DeleteMapping("del")
	@ResponseBody
	public int del(@RequestParam("id") Long id) {
		return courseOfflineService.deleteById(id);
	}
}
