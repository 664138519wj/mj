package com.migu.online.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.migu.online.controller.response.Result;
import com.migu.online.model.Contact;
import com.migu.online.model.Jobs;
import com.migu.online.model.Media;
import com.migu.online.model.Works;
import com.migu.online.service.ContactService;
import com.migu.online.service.JobsService;
import com.migu.online.service.MediaService;
import com.migu.online.service.SysManager;
import com.migu.online.service.WorksService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
private Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SysManager sysManagerService;
    
    @Autowired
    private WorksService worksService;
    
    @Autowired
    private MediaService mediaService;
    
    @Autowired
    private ContactService contactService;
    
    @Autowired
    private JobsService jobsService;

    @Value("${uuid}")
    private String id;


    @RequestMapping("index")
    public ModelAndView index(){
    	 ModelAndView modelAndView = new ModelAndView("home/index");
        return modelAndView;
    }
    
    @RequestMapping("profile")
    public ModelAndView profile(){
    	 ModelAndView modelAndView = new ModelAndView("home/profile");
        return modelAndView;
    }
    @RequestMapping("honor")
    public ModelAndView honor(){
    	ModelAndView modelAndView = new ModelAndView("home/honor");
    	return modelAndView;
    }
    @RequestMapping("jobs")
    public ModelAndView jobs(){
    	ModelAndView modelAndView = new ModelAndView("home/jobs");
    	return modelAndView;
    }
    @RequestMapping("team")
    public ModelAndView team(){
    	ModelAndView modelAndView = new ModelAndView("home/team");
    	return modelAndView;
    }
    
    @RequestMapping("works")
    public ModelAndView works(){
    	 ModelAndView modelAndView = new ModelAndView("home/works");
        return modelAndView;
    }
    
    @RequestMapping("press")
    public ModelAndView press(){
    	 ModelAndView modelAndView = new ModelAndView("home/press");
        return modelAndView;
    }
    
    @RequestMapping("contact")
    public ModelAndView contact(){
    	 ModelAndView modelAndView = new ModelAndView("home/contact");
        return modelAndView;
    }
    
    
   
    @SuppressWarnings("rawtypes")
    @PostMapping("getAllWorks")
    @ResponseBody
    public Result getAllWorks() throws Exception{
    	
    	
    	List<Works> works = worksService.selectAll();
    	
    	return new Result(works);
    	
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("getAllMedia")
    @ResponseBody
    public Result getAllMedia() throws Exception{
    	
    	
    	List<Media> medias = mediaService.selectAll();
    	
    	return new Result(medias);
    	
    }
    
    @SuppressWarnings("rawtypes")
    @RequestMapping("getJobsHtml")
    @ResponseBody
    public Result getJobsHtml() throws Exception{
    	
    	
    	List<Jobs> jobs = jobsService.selectAll();
    	
    	return new Result(jobs);
    	
    }
    
    
    @SuppressWarnings("rawtypes")
    @PostMapping("saveContact")
    @ResponseBody
    public String saveContact(Contact model) throws Exception{
    	 model.setCreateTime(new Date());
    	
    	
    	  return contactService.createOrUpdate(model) >= 1 ? "success" : "error";
    	
    }
    
}
