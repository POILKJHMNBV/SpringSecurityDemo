package com.example.config;

import com.example.dto.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhenwu
 * 为ExceptionTranslationFilter自定义AccessDeniedHandler，处理AccessDeniedException
 */
@Component
@Slf4j
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.warn("{} try to access {}", loginUser.getUsername(), request.getRequestURI());
        request.getRequestDispatcher("/error").forward(request, response);
    }
}