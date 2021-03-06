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
import com.migu.online.model.TrafficLaw;
import com.migu.online.service.TrafficLawService;

/**
 * 法律法规
 */
@Controller
@RequestMapping("/ops/trafficlaw/")
public class TrafficLawOpsController {

    @Autowired
    private TrafficLawService trafficlawService;

    /**
     *  ------页面跳转 start-----
     */


    @GetMapping("index")
    public String index(ModelMap modelMap){
        return "ops/trafficlaw/index";
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
    	TrafficLaw trafficlaw = new TrafficLaw();
        if(editFlag > 0) {
        	trafficlaw = trafficlawService.selectById(id);
        }
        modelMap.put("trafficlaw",trafficlaw);
        modelMap.put("edit",editFlag != 2);
        return "ops/trafficlaw/edit";
    }

    /**
     *  ------页面跳转 end------
     */


    /**
     * 数据处理
     */
	@SuppressWarnings("rawtypes")
	@GetMapping("data")
	@ResponseBody
	public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer pageIndex,
			@RequestParam(value = "limit", defaultValue = "20") Integer limit,
			@RequestParam(value = "filter", required = false) String filter) {
		Page<TrafficLaw> page = (Page<TrafficLaw>) trafficlawService.selectConditonByPage(filter, pageIndex, limit);
		return new ResponsePageData<TrafficLaw>(page.getTotal(), page);
	}

	 @PostMapping("save")
		@ResponseBody
		public String save(TrafficLaw model) {
			if (null == model) {
				return "error";
			}

			if (model.getId() != null && model.getId() > 0) {
				// 更新
				TrafficLaw record = trafficlawService.selectById(model.getId());
				if (null == record) {
					return "error";
				}
				record.setTitle(model.getTitle());
				record.setImagePath(model.getImagePath());
				record.setSummary(model.getSummary());
				record.setContent(model.getContent());
				record.setTopic(model.getTopic());
				return trafficlawService.createOrUpdate(record) >= 1 ? "success" : "error";
			} else {
				model.setIsDelete(0);	
			}
			return trafficlawService.createOrUpdate(model) >= 1 ? "success" : "error";
		}

    @DeleteMapping("del")
    @ResponseBody
    public int del(@RequestParam("id") Long id){
        return trafficlawService.deleteById(id);
    }
}
