package com.jasper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.dto.AddCartDto;
import com.jasper.pojo.Cart;
import com.jasper.vo.CartVo;

import java.util.List;

/**
* @author 21903
* @description 针对表【cart(购物车表)】的数据库操作Service
* @createDate 2023-08-05 16:19:37
*/
public interface CartService extends IService<Cart> {

    Boolean addCart(AddCartDto cartDto, String username);
    Boolean updateQuantity(Long id, String value);

    List<CartVo> getAll();
}
