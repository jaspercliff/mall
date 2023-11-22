package com.jasper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.common_utils.R;
import com.jasper.pojo.GoodsCategory;

/**
* @author 21903
* @description 针对表【goods_category(产品分类)】的数据库操作Service
* @createDate 2023-06-23 16:14:14
*/
public interface GoodsCategoryService extends IService<GoodsCategory> {
    R<?> getOneCategory();

    R<?> getTwoCategory(String oneCategoryId);

    R<?> getHotCategory();
}
