package com.migu.online.model.system;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.migu.online.model.User;
import com.migu.online.service.UserService;

/**
 * Created by Liaopan on 2018/1/10.
 */
@Component
public class MyAppRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(MyAppRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug((String)authenticationToken.getPrincipal());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.selectOneByMobile(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPwd(), getName());
        }
        return null;
    }

    /**
     * 获取授权信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String userName = (String) principals.getPrimaryPrincipal();
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();

//        User user = userService.selectOneByMobile(userName);
//        if(user != null){
//            List<SysRole> roles = user.getRoles();
//            Optional.of(roles).ifPresent(sysRoles -> {
//                sysRoles.forEach( role ->{
//                    userRoles.add(role.getRole());
//                    Optional.of(role.getPermissions()).ifPresent(sysPermissions -> {
//                        Set<String> temp = sysPermissions.stream().map(SysPermission::getName).collect(Collectors.toSet());
//                        userPermissions.addAll(temp);
//                    });
//                });
//            });
//        }else {
//            throw new AuthorizationException("用户不存在！");
//        }
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(userPermissions);
        log.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
        // TODO session 入库 保证服务重启客户端无需再次登录       
        return authorizationInfo;

    }
}
