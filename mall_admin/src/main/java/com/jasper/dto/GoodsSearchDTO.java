package com.jasper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSearchDTO {
    private Long id;
    private String name;
    private Long brandId;
    private Long goodCategoryId;
    private Integer isPublished;
    private Integer isVerify;
    private Integer pageNum;
    private Integer pageSize;
}
