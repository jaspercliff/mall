package com.jasper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.dto.AddCartDto;
import com.jasper.mapper.CartMapper;
import com.jasper.pojo.Cart;
import com.jasper.pojo.Goods;
import com.jasper.pojo.GoodsBrand;
import com.jasper.pojo.User;
import com.jasper.service.CartService;
import com.jasper.service.GoodsBrandService;
import com.jasper.service.GoodsService;
import com.jasper.service.UserService;
import com.jasper.vo.CartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author 21903
* @description 针对表【cart(购物车表)】的数据库操作Service实现
* @createDate 2023-08-05 16:19:37
*/
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

    private final UserService userService;
    private final GoodsService goodsService;
    private final GoodsBrandService goodsBrandService;
    @Override
    public Boolean addCart(AddCartDto cartDto, String username) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<Cart>().eq(Cart::getProductId, cartDto.getProductId());
        Cart cartOne = super.getOne(wrapper);
        if(cartOne==null) {
            Cart cart = new Cart();
            BeanUtil.copyProperties(cartDto, cart);
            LambdaQueryWrapper<User> e = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
            User user = userService.getOne(e);
            LambdaQueryWrapper<Goods> eq = new LambdaQueryWrapper<Goods>().eq(Goods::getId, cartDto.getProductId());
            Goods goods = goodsService.getOne(eq);
            cart.setMemberId(user.getId());
            if (goods.getPromotionPrice() != null) {
                cart.setPrice(new BigDecimal(goods.getPromotionPrice()));
            } else {
                cart.setPrice(goods.getPrice());
            }
            cart.setProductName(goods.getName());
            cart.setProductPic(goods.getPicture());
            cart.setMemberNickname(username);
            cart.setProductCategoryId(goods.getGoodCategoryId());
            GoodsBrand brand = goodsBrandService.getById(goods.getBrandId());
            cart.setProductBrand(brand.getName());
            return super.save(cart);
        }else {
            return true;
        }
    }

    @Override
    public Boolean updateQuantity(Long id, String value) {
        LambdaUpdateWrapper<Cart> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Cart::getId,id);
        wrapper.set(Cart::getQuantity,value);
        return super.update(wrapper);
    }

    @Override
    public List<CartVo> getAll() {
        List<Cart> list = super.list();
        ArrayList<CartVo> cartVos = new ArrayList<>(list.size());
        list.forEach(
                cart -> {
                    CartVo cartVo = new CartVo();
                    BeanUtil.copyProperties(cart,cartVo);
                    Long productId = cart.getProductId();
                    Goods good = goodsService.getOne(new LambdaQueryWrapper<Goods>().eq(Goods::getId, productId));
                    cartVo.setStock(good.getStock());
                    cartVos.add(cartVo);
                }
        );
        return cartVos;
    }
}




