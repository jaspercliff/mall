package com.jasper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.pojo.UserReceiveAddress;

/**
* @author 21903
* @description 针对表【user_receive_address(会员收货地址表)】的数据库操作Service
* @createDate 2023-08-08 16:28:30
*/
public interface UserReceiveAddressService extends IService<UserReceiveAddress> {

    Boolean addAddress(UserReceiveAddress receiveAddress);

    Boolean deleteAddress(Long id);
}
