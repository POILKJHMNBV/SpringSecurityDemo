package com.example.controller;

import com.example.dto.LoginUser;
import com.example.dto.Result;
import com.example.service.TbUserService;
import com.example.vo.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhenwu
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private TbUserService userService;

    @Autowired
    public void setUserService(TbUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return Result.fail("Login failure!");
        }
        String token = this.userService.login(username, password);
        return token == null ? Result.fail("Login failure!") : Result.ok(token);
    }

    @GetMapping("/queryCurrentUserNickname")
    public Result<String> queryCurrentUserNickname() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.ok(loginUser.getUser().getNickname());
    }

    @GetMapping("/details")
    @PreAuthorize("hasAuthority('USER:SELECT')")    // 此注解与TraditionalWeb的配置一致
    public Result<String> details() {
        return Result.ok();
    }

    @GetMapping("/enter")
    @PreAuthorize("hasAuthority('USER:ENTER')")     // 此注解与TraditionalWeb的配置一致
    public Result<String> enter() {
        return Result.ok();
    }

    @GetMapping("/logout")
    public Result<String> logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Boolean result = this.userService.logout(token);
        if (result == null) {
            return Result.fail("Logout failure!");
        }
        return result ? Result.ok() : Result.fail("Logout failure!");
    }
}