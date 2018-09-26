package com.migu.online.controller.ops;

import java.util.Date;

import com.google.common.base.Strings;
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
import com.migu.online.model.CourseOnline;
import com.migu.online.ops.vo.CourseOnlineOpsVo;
import com.migu.online.service.CourseOnlineService;

@Controller
@RequestMapping("/ops/courseonline/")
public class CourseOnlineOpsController {

    @Autowired
    private CourseOnlineService courseOnlineService;

    /**
     *  ------页面跳转 start-----
     */


    @GetMapping("index")
    public String index(ModelMap modelMap){

        return "ops/courseonline/index";
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
			modelMap.put("courseOnline", courseOnlineService.selectById(id));
		} else if (editFlag == 2) {
			// show
			try {
				modelMap.put("courseOnline", courseOnlineService.selectOpsOne(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// new
			modelMap.put("courseOnline", new CourseOnline());
		}
		modelMap.put("edit", editFlag != 2);
		return "ops/courseonline/edit";
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

		Page<CourseOnlineOpsVo> page = (Page<CourseOnlineOpsVo>) courseOnlineService.selectConditionByPage(offset,
				limit, filter);
		return new ResponsePageData<CourseOnlineOpsVo>(page.getTotal(), page);
	}

	@PostMapping("save")
	@ResponseBody
	public String save(CourseOnline model) {
		if (null == model) {
			return "error";
		}

		if (StringUtils.isEmpty(model.getImagePath())) {
			model.setImagePath(Constants.USER_DEFAULT_AVATAR);
		}
		if (Strings.isNullOrEmpty(model.getSmallVideoPath())) {
			model.setSmallVideoPath(model.getVideoPath());
		}
		if (model.getId() != null && model.getId() > 0) {
			// 更新
			CourseOnline record = courseOnlineService.selectById(model.getId());
			if (null == record) {
				return "error";
			}
			record.setTeacherId(model.getTeacherId());
			record.setPrice(model.getPrice());
			record.setType(model.getType());
			record.setRecommand(model.getRecommand());
			record.setTitle(model.getTitle());
			record.setContent(model.getContent());
			record.setVideoPath(model.getVideoPath());
			record.setSmallVideoPath(model.getSmallVideoPath());
			record.setImagePath(model.getImagePath());
			record.setUpdateTime(new Date());
			record.setIsHotAndCharge(model.getIsHotAndCharge());
			record.setHour(model.getHour());
			record.setMinute(model.getMinute());
			record.setSecond(model.getSecond());
			record.setIsListRec(model.getIsListRec());
			return courseOnlineService.createOrUpdate(record) >= 1 ? "success" : "error";
		} else {
			model.setSort(1);
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setIsDelete(0);
			model.setPlayNumber(0L);
		}
		return courseOnlineService.createOrUpdate(model) >= 1 ? "success" : "error";
	}

	@PostMapping("release")
	@ResponseBody
	public String release(@RequestParam("id") Long id) {
		CourseOnline courseOnline = courseOnlineService.selectById(id);
		courseOnline.setReleaseTime(new Date());
		return courseOnlineService.createOrUpdate(courseOnline) >= 1 ? "success" : "error";
	}

	@DeleteMapping("del")
	@ResponseBody
	public int del(@RequestParam("id") Long id) {
		return courseOnlineService.deleteById(id);
	}

}
