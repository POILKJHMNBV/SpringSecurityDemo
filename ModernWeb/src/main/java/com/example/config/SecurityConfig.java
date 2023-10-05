package com.example.config;

import com.example.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhenwu
 * Spring Security配置类，继承WebSecurityConfigurerAdapter后，配置类SpringBootWebSecurityConfiguration将失效
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public void setJwtAuthenticationTokenFilter(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    /**
     * 将全局的AuthenticationManager放入容器
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 指定PasswordEncoder，指定后，数据库存储的密码不应该有前缀
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注意这里与TraditionalWeb配置的不同
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 关闭跨站请求防护及不使用session
                .and().authorizeRequests()
                .mvcMatchers("/user/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()    // 前端发送请求以前都会先发送options请求，这里直接放行
                .anyRequest().authenticated()   // 除了上面permitAll放行的请求外，其它请求都需要认证
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestfulAccessDeniedHandler());

        // 向HttpSecurity中添加自定义Filter
        http.addFilterBefore(this.jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}