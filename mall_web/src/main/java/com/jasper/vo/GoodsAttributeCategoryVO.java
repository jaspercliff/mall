package com.jasper.vo;

import com.jasper.pojo.GoodsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GoodsAttributeCategoryVO {
    private Long id;
   private String name;
    private List<GoodsAttribute> attributeList;
}
