package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.UserResourceMapper;
import com.jasper.pojo.UserResource;
import com.jasper.service.UserResourceService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【user_resource(后台资源表)】的数据库操作Service实现
* @createDate 2023-06-26 16:52:00
*/
@Service
public class UserResourceServiceImpl extends ServiceImpl<UserResourceMapper, UserResource>
    implements UserResourceService{

}




