package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.UserRoleMapper;
import com.jasper.pojo.UserRole;
import com.jasper.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2023-06-23 16:14:14
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




