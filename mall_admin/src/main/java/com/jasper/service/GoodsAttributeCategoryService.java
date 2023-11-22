package com.jasper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.pojo.GoodsAttributeCategory;
import com.jasper.vo.AttributeCategoryVo;
import com.jasper.vo.GoodsAttributeCategoryVO;

import java.util.List;

/**
* @author 21903
* @description 针对表【goods_attribute_category(产品属性分类表)】的数据库操作Service
* @createDate 2023-07-23 16:19:04
*/
public interface GoodsAttributeCategoryService extends IService<GoodsAttributeCategory> {

    Page<GoodsAttributeCategory> pageList(Integer pageNum, Integer pageSize);

    Boolean addAttributeCategory(GoodsAttributeCategory goodsAttributeCategory);

    Boolean deleteAttributeCategory(String id);

    List<AttributeCategoryVo> getAllAttributeCategory();

    List<GoodsAttributeCategoryVO> getListWithAttr();
}
