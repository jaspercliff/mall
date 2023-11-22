package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.UserPermissionMapper;
import com.jasper.pojo.UserPermission;
import com.jasper.service.UserPermissionService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【user_permission】的数据库操作Service实现
* @createDate 2023-06-23 16:14:14
*/
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission>
    implements UserPermissionService{

}




