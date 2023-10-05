package com.example.config;

import cn.hutool.json.JSONUtil;
import com.example.dto.LoginUser;
import com.example.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhenwu
 * 为ExceptionTranslationFilter自定义AccessDeniedHandler，处理AccessDeniedException
 */
@Slf4j
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.warn("{} try to access {}", loginUser.getUsername(), request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONUtil.toJsonStr(Result.forbidden(accessDeniedException.getMessage())));
    }
}