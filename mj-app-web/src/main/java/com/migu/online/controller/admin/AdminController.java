package com.migu.online.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Liaopan on 2018/1/23.
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("")
    public ModelAndView index(){
        System.out.println("admin/index...");
        ModelAndView modelAndView = new ModelAndView("/admin/index");

        modelAndView.addObject("url","/main");
        return modelAndView;
    }

    @GetMapping("/layout")
    public ModelAndView layout(){
        return new ModelAndView("/admin/layout").addObject("content","content");
    }
}
