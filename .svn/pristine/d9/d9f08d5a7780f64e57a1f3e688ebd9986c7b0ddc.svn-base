package com.migu.online.controller.ops;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.migu.online.controller.response.ResponsePageData;
import com.migu.online.model.PracticeCard;
import com.migu.online.service.PracticeCardService;

/**
 * 练习卡
 * @author fanyunlong
 *
 */
@Controller
@RequestMapping("/ops/card")
public class PracticeCardOpsController {

    @Autowired
    private PracticeCardService practiceCardService;
   
 
    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "ops/card/index";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("data")
    @ResponseBody
    public ResponsePageData data(@RequestParam(value = "page", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                 @RequestParam(value = "filters", required = false) String _filters) throws Exception {
        Map<String, String> filters = Maps.newHashMap();
        if (_filters != null) {
            Map maps = (Map) JSON.parse(_filters);
            for (Object key : maps.keySet()) {
                filters.put((String) key, (String) maps.get(key));
            }
        }

        Page<PracticeCard> page = (Page<PracticeCard>) practiceCardService
                .selectByPage(offset, limit);
        return new ResponsePageData<PracticeCard>(page.getTotal(), page);
    }


    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "flag", defaultValue = "1") Integer editFlag) throws Exception {
        if (editFlag > 0) {
            // show
            modelMap.put("data", practiceCardService.getOne(id));
        } else {
            // new
            modelMap.put("data", new PracticeCard());
        }
        modelMap.put("edit", editFlag != 2);
        return "ops/card/edit";

    }
    
    @PostMapping("save")
	@ResponseBody
	public String save(PracticeCard model) {
		if (null == model) {
			return "error";
		}

		if (model.getId() != null && model.getId() > 0) {
			// 更新
			PracticeCard record = practiceCardService.getOne(model.getId());
			if (null == record) {
				return "error";
			}
			record.setPrice(model.getPrice());
			record.setExpiredDays(model.getExpiredDays());
		} else {		
		}
		practiceCardService.createOrUpdate(model);
		return  "success";
	}

}
