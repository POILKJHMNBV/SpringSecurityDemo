package com.example.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.TbUser;
import com.example.dto.LoginUser;
import com.example.mapper.TbUserMapper;
import com.example.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @author zhenwu
*/
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser>
    implements TbUserService {

    private AuthenticationManager authenticationManager;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String login(String username, String password) {
        // 1.模仿UsernamePasswordAuthenticationFilter的认证流程进行认证
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticationResult = this.authenticationManager.authenticate(authRequest);
        if (authenticationResult == null) {
            return null;
        }

        // 2.认证成功，生成token，此处只用UUID进行简单的模拟
        String token = UUID.randomUUID(false).toString();

        // 3.将用户信息存入redis
        LoginUser loginUser = (LoginUser) authenticationResult.getPrincipal();
        this.stringRedisTemplate.opsForValue().set("login:token:" + token, JSONUtil.toJsonStr(loginUser), 1800, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public Boolean logout(String token) {
        String key = "login:token:" + token;
        if (Boolean.TRUE.equals(this.stringRedisTemplate.hasKey(key))) {
            return this.stringRedisTemplate.delete(key);
        }
        return false;
    }
}