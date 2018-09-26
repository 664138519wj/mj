package com.migu.online.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 如果自定义配置不是放在application.properties，
 * 可以使用@Configuration  @PropertySource("classpath:***.properties")来注入
 * Created by Liaopan on 2018/1/8.
 */
@ConfigurationProperties(prefix = "ssm")
@Data
public class CustomConfig {

    private String name;
    private String title;
}
