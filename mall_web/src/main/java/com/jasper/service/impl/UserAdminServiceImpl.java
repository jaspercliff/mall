package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.UserAdminMapper;
import com.jasper.pojo.UserAdmin;
import com.jasper.service.UserAdminService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【user_admin】的数据库操作Service实现
* @createDate 2023-06-23 16:14:14
*/
@Service
public class UserAdminServiceImpl extends ServiceImpl<UserAdminMapper, UserAdmin>
    implements UserAdminService{

}




