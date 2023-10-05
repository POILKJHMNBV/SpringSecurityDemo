package com.example.filter;

import cn.hutool.json.JSONUtil;
import com.example.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhenwu
 * 由于不再利用session存储信息，需要我们自定义filter从redis取出用户信息存入SecurityContextHolder，后面授权要用
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token)) {
            String key = "login:token:" + token;
            String userJson = this.stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.hasLength(userJson)) {
                throw new AuthenticationException("Token has expired!");
            }
            this.stringRedisTemplate.expire(key, 1800, TimeUnit.SECONDS);
            LoginUser loginUser = JSONUtil.parseObj(userJson).toBean(LoginUser.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
