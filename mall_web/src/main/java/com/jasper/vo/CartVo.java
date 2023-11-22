package com.jasper.vo;

import com.jasper.pojo.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo extends Cart {
    private Integer stock;
}
