package com.jasper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCartDto {
    private Long productId;
    private Integer quantity;
}
