package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhenwu
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        log.info("username = {}", username);
        log.info("password = {}", password);
        // 此处仅用于演示可以从SecurityContextHolder中获取到认证成功的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("principal = " + authentication.getPrincipal());
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/details")
    public String details() {
        return "details";
    }

    @GetMapping("/enter")
    public String enter() {
        return "clorinde";
    }
}