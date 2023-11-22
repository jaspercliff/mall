package com.jasper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.ResultCode;
import com.jasper.config.CustomException;
import com.jasper.mapper.UserReceiveAddressMapper;
import com.jasper.pojo.User;
import com.jasper.pojo.UserReceiveAddress;
import com.jasper.service.UserReceiveAddressService;
import com.jasper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【user_receive_address(会员收货地址表)】的数据库操作Service实现
* @createDate 2023-08-08 16:28:30
*/
@Service
@RequiredArgsConstructor
public class UserReceiveAddressServiceImpl extends ServiceImpl<UserReceiveAddressMapper, UserReceiveAddress>
    implements UserReceiveAddressService{

    private final UserService userService;
    @Override
    public Boolean addAddress(UserReceiveAddress receiveAddress) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = "";
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            email=authentication.getName();
        }else {
            throw new CustomException(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail,email ));
        receiveAddress.setMemberId(user.getId());
        return super.save(receiveAddress);
    }

    @Override
    public Boolean deleteAddress(Long id) {
        return super.removeById(id);
    }
}




