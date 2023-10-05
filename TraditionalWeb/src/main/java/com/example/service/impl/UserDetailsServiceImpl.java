package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.TbUser;
import com.example.dto.LoginUser;
import com.example.mapper.TbUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author zhenwu
 * 实现UserDetailsService接口后，配置类将失效
 * UserDetailsService的实现类将由DaoAuthenticationProvider使用
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.查询用户信息
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        TbUser tbUser = tbUserMapper.selectOne(queryWrapper);
        if (Objects.isNull(tbUser)) {
            throw new UsernameNotFoundException("认证失败！");
        }

        // 2.查询用户对应的权限信息
        List<String> permissions = tbUserMapper.queryPermissions(username);

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(tbUser);
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}