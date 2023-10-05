package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.TbUser;

import java.util.List;

/**
* @author zhenwu
*/
public interface TbUserMapper extends BaseMapper<TbUser> {
    List<String> queryPermissions(String username);
}




