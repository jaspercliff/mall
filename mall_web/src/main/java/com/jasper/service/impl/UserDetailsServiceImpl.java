package com.jasper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jasper.bo.UserDetailsImpl;
import com.jasper.pojo.User;
import com.jasper.pojo.UserResource;
import com.jasper.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author com
 * 6/22/2023  3:30 PM
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Lazy
    @Resource
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        if(one!=null){
            List<UserResource> resourceList = userService.getResourceList(one.getId());
            return new UserDetailsImpl(one,resourceList);
        }
        throw new UsernameNotFoundException("用户名或者密码错误");
    }



}
