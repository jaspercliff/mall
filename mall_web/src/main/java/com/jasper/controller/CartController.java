package com.jasper.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jasper.dto.AddCartDto;
import com.jasper.pojo.Cart;
import com.jasper.service.CartService;
import com.jasper.vo.CartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("webService/cart")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PostMapping("addCart/{username}")
    private Boolean addCart(@RequestBody AddCartDto cartDto, @PathVariable String username){
        return cartService.addCart(cartDto,username);
    }

    @GetMapping("list")
    private List<CartVo> getAll(){
        return cartService.getAll();
    }

    /**
     * 在order界面展示的商品
     * @param ids  the goods checked
     * @return
     */
    @GetMapping("list/{ids}")
    private List<Cart> getListByIds(@PathVariable List<Integer> ids){
        return cartService.list(new LambdaQueryWrapper<Cart>().in(Cart::getId,ids));
    }

    @PutMapping("updateQuantity/{id}/{value}")
    private Boolean updateQuantity(@PathVariable("value") String value,@PathVariable Long id){
       return cartService.updateQuantity(id,value);
    }

    @DeleteMapping("deleteCart/{id}")
    private Boolean deleteCart(@PathVariable Long id){
        return cartService.removeById(id);
    }
}
