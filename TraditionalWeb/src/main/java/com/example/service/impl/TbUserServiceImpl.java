package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.TbUser;
import com.example.service.TbUserService;
import com.example.mapper.TbUserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenwu
*/
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser>
    implements TbUserService{

}




