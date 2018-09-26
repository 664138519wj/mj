package com.migu.online.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
     //   registry.addViewController("/empty").setViewName("blank");
        /**
         * 登录页面
         */
        registry.addViewController("/login.html").setViewName("login");
       // registry.addViewController("/").setViewName("forward:/home/profile");
       // registry.addViewController( "/" ).setViewName( "forward:/home/profile" );
        //registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers(registry);
    }
}
