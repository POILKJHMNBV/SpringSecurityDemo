package com.example.config;

import cn.hutool.json.JSONUtil;
import com.example.dto.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhenwu
 * 为ExceptionTranslationFilter自定义AuthenticationEntryPoint，处理AuthenticationException
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONUtil.toJsonStr(Result.unauthorized(authException.getMessage())));
    }
}
