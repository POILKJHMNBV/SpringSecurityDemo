package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author zhenwu
 * Spring Security配置类，继承WebSecurityConfigurerAdapter后，配置类SpringBootWebSecurityConfiguration将失效
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    /**
     * 子类并未改变父类的属性disableDefaults，因此父类的applyDefaultConfiguration方法仍然会执行
     * @param http HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  // 开启Spring Security认证和授权功能，默认情况下SecurityFilterChain匹配所有请求
                .mvcMatchers("/login.html").permitAll()                         // 登录页不需要认证和授权即可访问
                .mvcMatchers("/static/css/**").permitAll()                      // 静态资源放行
                .mvcMatchers("/static/img/**").permitAll()
                .mvcMatchers("/user/details").hasAnyAuthority("USER:SELECT")    // 用户访问该路径需要有指定权限
                .mvcMatchers("/user/enter").hasAnyAuthority( "USER:ENTER")
                .anyRequest().authenticated()                       // 除了上面permitAll放行的请求外，其它请求都需要认证
                .and()
                .formLogin()            // 添加FormLoginConfigurer
                .failureForwardUrl("/login.html")   // 认证失败后转发至/login.html
                .loginPage("/login.html")           // 配置登录页
                .loginProcessingUrl("/user/login")  // 配置需要进行认证的url，即登录页form表单的请求url
                .successForwardUrl("/user/login")   // 认证成功后转发至/user/login
                .and()
                .csrf().disable()       // 关闭csrf
                .logout()               // 添加LogoutConfigurer
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .and()
                .exceptionHandling()        // 添加ExceptionHandlingConfigurer
                .accessDeniedHandler(accessDeniedHandler);
    }
}