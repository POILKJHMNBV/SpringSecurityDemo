package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import java.util.List;

@SpringBootApplication
@MapperScan("com.example.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 这里开启注解权限认证
public class ModernWebApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ModernWebApplication.class, args);
        FilterChainProxy filterChainProxy = applicationContext.getBean(FilterChainProxy.class);
        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
        for (SecurityFilterChain filterChain : filterChains) {
            List<Filter> filters = filterChain.getFilters();
            for (int i = 0; i < filters.size(); i++) {
                System.out.println((i + 1) + ": " + filters.get(i));
            }
        }
    }
}