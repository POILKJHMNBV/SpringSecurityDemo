package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.TbUser;

/**
* @author zhenwu
*/
public interface TbUserService extends IService<TbUser> {
    String login(String username, String password);
    Boolean logout(String token);
}
