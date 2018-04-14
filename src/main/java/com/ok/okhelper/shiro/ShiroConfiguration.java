package com.ok.okhelper.shiro;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 21:52 2018/4/9
 */

import com.ok.okhelper.service.UserService;
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
 *Author:zhangxin_an
 *Description:
 *Data:Created in
 */

@Configuration
public class ShiroConfiguration {

    //    @Autowired
    UserService userService;


//    @Bean(name="jwtFilter")
//    @Order(2)
//    public JwtAuthenticationTokenFilter myAuthFilter() {
//        return new JwtAuthenticationTokenFilter();
//    }

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
//        // 所有请求通过我们自己的JWT Filter
//         访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/404", "anon");
        filterRuleMap.put("/druid/**", "anon");
        filterRuleMap.put("/user/login", "anon");

        filterRuleMap.put("/**", "jwt");

//      filterRuleMap.put("/*", "authc");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;


//        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
//        bean.setSecurityManager(manager);
//// 添加自己的过滤器并且取名为jwt
//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("jwt", new JWTFilter());
//        bean.setFilters(filterMap);
//
//        bean.setUnauthorizedUrl("/401");
//
//        /*
//         * 自定义url规则
//         * http://shiro.apache.org/web.html#urls-
//         */
//        Map<String, String> filterRuleMap = new HashMap<>();
//        // 所有请求通过我们自己的JWT Filter
//        filterRuleMap.put("/*", "jwt");
//        // 访问401和404页面不通过我们的Filter
//        filterRuleMap.put("/401", "anon");
//        bean.setFilterChainDefinitionMap(filterRuleMap);
//
//        //配置登录的url和登录成功的url
//        bean.setLoginUrl("/login");
//        bean.setSuccessUrl("/home");
//        //配置访问权限
//        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
////        filterChainDefinitionMap.put("", "anon"); //表示可以匿名访问
////        filterChainDefinitionMap.put("/loginUser", "anon");
////        filterChainDefinitionMap.put("/logout*","anon");
////        filterChainDefinitionMap.put("/user/loginUser","anon");
////        filterChainDefinitionMap.put("/jsp/index.jsp*","authc");
////        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
////        filterChainDefinitionMap.put("/user/*", "authc");//表示需要认证才可以访问
//	    filterChainDefinitionMap.put("/user","rest[user]");
////	    filterChainDefinitionMap.put("/user/*","roles[admin]");
////        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
////        filterChainDefinitionMap.put("/*.*", "authc");
//        filterChainDefinitionMap.put("/*", "anon"); //表示可以匿名访问
//        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return bean;
    }

    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
//        System.err.println("--------------shiro已经加载----------------");
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

    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
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
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


}