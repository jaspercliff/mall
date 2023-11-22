package com.jasper.dto;

import com.jasper.pojo.GoodsCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategoryDTO extends GoodsCategory {
    private List<Long> attributeList;
}
