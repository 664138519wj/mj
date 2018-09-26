package com.migu.online.configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.migu.online.intercepter.UserInterceptor;
import com.migu.online.model.system.MyAppRealm;

/**
 * Created by Liaopan on 2018/1/10.
 */
@Configuration
public class ShiroConfigurer extends WebMvcConfigurerAdapter{

	private Logger log = LoggerFactory.getLogger(ShiroConfigurer.class);
	
	@Value("${spring.redis.host}")  
    private String host;  
    @Value("${spring.redis.port}")  
    private int port;  
    @Value("${spring.redis.timeout}")  
    private int timeout;  
    @Value("${spring.redis.password}")  
    private String password;  
    
	@Value("${base.url}")  
	private String baseUrl;
	@Value("${ctx}")
	private String ctx;
	@Value("${video.url}")
	private String videoUrl;
	@Value("${image.url}")
	private String imageUrl;
    
    public static Map<String, String> configMap = new HashMap<>();
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了login，其他都拦截判断
        registry.addInterceptor(new UserInterceptor());
        super.addInterceptors(registry);
    }
    
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		log.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 登录链接，默认"/login.jsp"
		shiroFilterFactoryBean.setLoginUrl("/home/index");
		// 登录成功后的链接
		shiroFilterFactoryBean.setSuccessUrl("/home/index");
		// 未授权返回的页面链接,
		shiroFilterFactoryBean.setUnauthorizedUrl("/auth/app/unauthorized");

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/auth/logout", "logout");

		// <!-- authc:必须通过验证; anon:可以匿名访问-->
		filterChainDefinitionMap.put("/auth/app/*", "anon");
		filterChainDefinitionMap.put("/auth/web/*", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		filterChainDefinitionMap.put("/admin/**", "anon");
		filterChainDefinitionMap.put("/upload/**", "anon");
		filterChainDefinitionMap.put("/h5/**", "anon");
		filterChainDefinitionMap.put("/home/**", "anon");


		/* filterChainDefinitionMap.put("/api/**", "authcBasic"); */
		filterChainDefinitionMap.put("/api/**", "anon");

		filterChainDefinitionMap.put("/**", "rest");


		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		configMap.put("baseUrl", baseUrl);
		configMap.put("ctx", ctx);
		configMap.put("videoUrl", videoUrl);
		configMap.put("imageUrl", imageUrl);

		return shiroFilterFactoryBean;
	}

	@Bean
	public Realm realm() {
		MyAppRealm realm = new MyAppRealm();
		realm.setCachingEnabled(true);
		return realm;
	}

	@Bean
	public SecurityManager securityManager() {
		log.info("securityManager安全管理器 注入");
		DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
		webSecurityManager.setRealm(realm());
		// 自定义session管理
		webSecurityManager.setSessionManager(sessionManager());
		// 自定义缓存实现 使用redis  
		webSecurityManager.setCacheManager(cacheManager());
		return webSecurityManager;
	}

	// 自定义sessionManager
	@Bean
	public SessionManager sessionManager() {
		MySessionManager mySessionManager = new MySessionManager();
		mySessionManager.setSessionDAO(redisSessionDAO());
		return mySessionManager;
	}
	
	/** 
     * 配置shiro redisManager 
     * <p> 
     * 使用的是shiro-redis开源插件 
     * 
     * @return 
     */  
    public RedisManager redisManager() {  
        RedisManager redisManager = new RedisManager();  
        redisManager.setHost(host);  
        redisManager.setPort(port);  
        redisManager.setExpire(0);// 配置缓存过期时间  
        redisManager.setTimeout(timeout);  
        redisManager.setPassword(password);  
        return redisManager;  
    }  
    
    /** 
     * cacheManager 缓存 redis实现 
     * <p> 
     * 使用的是shiro-redis开源插件 
     * 
     * @return 
     */  
    public RedisCacheManager cacheManager() {  
        RedisCacheManager redisCacheManager = new RedisCacheManager();  
        redisCacheManager.setRedisManager(redisManager());  
        return redisCacheManager;  
    }
    
    /** 
     * RedisSessionDAO shiro sessionDao层的实现 通过redis 
     * <p> 
     * 使用的是shiro-redis开源插件 
     */  
    public RedisSessionDAO redisSessionDAO() {  
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();  
        redisSessionDAO.setRedisManager(redisManager());  
        return redisSessionDAO;  
    }  

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
