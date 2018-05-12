package com.ok.okhelper.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/*
 *Author:zhangxin_an,zc
 *Description:
 *Data:Created in
 */

@Configuration
public class ShiroConfiguration {

    //shiro过滤器链
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {


        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
//        factoryBean.setLoginUrl("/login");
//        factoryBean.setSuccessUrl("/home");

        /*
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         */
        final Map<String, String> filterRuleMap = new HashMap<>();

        // 访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/404", "anon");
        filterRuleMap.put("/druid/**", "anon");
        filterRuleMap.put("/favicon.ico", "anon");
        filterRuleMap.put("/webjar/**", "anon");
        filterRuleMap.put("/v2/api-docs", "anon");
        filterRuleMap.put("/doc.html", "anon");

        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/user/register", "anon");
        filterRuleMap.put("/user/check_username", "anon");
//        filterRuleMap.put("/user/phone","anon");
        filterRuleMap.put("/user/phone*","anon");

        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");

//        filterRuleMap.put("/*", "authc");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;

    }

    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        manager.setRealm(authRealm);
        return manager;
    }

    //ShiroCacheManager(使用Redis)
    @Bean(name = "redisShiroCacheManager")
    public RedisShiroCacheManager redisShiroCacheManager() {
        return new RedisShiroCacheManager();
    }

    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setCachingEnabled(true);
        //只缓存 登陆的token
        authRealm.setAuthenticationCachingEnabled(true);
        //不缓存 权限信息 换成在service 层控制缓存
        authRealm.setAuthorizationCachingEnabled(false);
        // 自定义缓存实现 使用redis
        authRealm.setCacheManager(redisShiroCacheManager());
        authRealm.setAuthenticationCacheName("authenticationCache");
        authRealm.setAuthorizationCacheName("authorizationCache");
        return authRealm;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题    https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


}