package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.CompanyAddressMapper;
import com.jasper.pojo.CompanyAddress;
import com.jasper.service.CompanyAddressService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【company_address(公司收发货地址表)】的数据库操作Service实现
* @createDate 2023-08-08 16:28:30
*/
@Service
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddress>
    implements CompanyAddressService{

}




