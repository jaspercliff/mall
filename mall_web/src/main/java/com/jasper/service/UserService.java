package com.jasper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.common_utils.R;
import com.jasper.dto.UserDto;
import com.jasper.pojo.User;
import com.jasper.pojo.UserResource;

import java.util.List;

/**
* @author 21903
* @description 针对表【user(会员表)】的数据库操作Service
* @createDate 2023-06-23 16:04:22
*/
public interface UserService extends IService<User> {

    R<?> getUserByUsername(String username);

    R<?> register(UserDto user, String emailcode);

    R<?> getUserByEmail(String email);

    R<?> login(UserDto userDto);
    public List<UserResource> getResourceList(Long userId);
}
