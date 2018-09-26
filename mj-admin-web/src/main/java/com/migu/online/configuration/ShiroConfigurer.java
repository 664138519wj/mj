package com.migu.online.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.migu.online.model.system.MyShiroRealm;


/**
 * Created by Liaopan on 2018/1/10.
 */
@Configuration
public class ShiroConfigurer {

    private Logger log = LoggerFactory.getLogger(ShiroConfigurer.class);
    
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        log.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录链接，默认"/login.jsp"
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //登录成功后的链接
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        //未授权返回的页面链接,
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/auth/logout", "logout");

        // <!-- authc:必须通过验证; anon:可以匿名访问-->
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/themes/**", "anon");
        filterChainDefinitionMap.put("/Lodop/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/admin/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/h5/**", "anon");
        
        filterChainDefinitionMap.put("/home/**", "anon");
        



        /*filterChainDefinitionMap.put("/api/**", "authcBasic");*/
        filterChainDefinitionMap.put("/api/**", "anon");
        
        filterChainDefinitionMap.put("/**", "authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        return shiroFilterFactoryBean;
    }

    @Bean
    public Realm realm(){
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCachingEnabled(true);
        return realm;
    }


    @Bean
    public SecurityManager securityManager(){
        log.info("securityManager安全管理器 注入");
        DefaultWebSecurityManager webSecurityManager =
                new DefaultWebSecurityManager();
        webSecurityManager.setRealm(realm());
        // 自定义session管理 
        webSecurityManager.setSessionManager(sessionManager());  
        return  webSecurityManager;
    }
    
   //自定义sessionManager  
    @Bean  
    public SessionManager sessionManager() {  
        MySessionManager mySessionManager = new MySessionManager();  
//        mySessionManager.setSessionDAO(redisSessionDAO());  
        return mySessionManager;  
    }  

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
