package com.migu.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.migu.online.camera.CatD;
import com.migu.online.configuration.CustomConfig;


@SpringBootApplication
@ServletComponentScan("com.migu.online")
@EnableConfigurationProperties({CustomConfig.class})
@EnableScheduling
public class AdminApplication extends SpringBootServletInitializer{

	/*public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CatD(), "/demo/myservlet");// ServletName默认值为首字母小写，即myServlet
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	
	@Override//为了打包springboot项目
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(AdminApplication.class);
	}
}
