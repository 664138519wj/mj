package com.migu.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.migu.online.configuration.CustomConfig;


@SpringBootApplication
@ServletComponentScan("com.migu.online")
@EnableConfigurationProperties({CustomConfig.class})
@EnableScheduling
@EnableCaching
public class AppApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(AppApplication.class);
	}
}
