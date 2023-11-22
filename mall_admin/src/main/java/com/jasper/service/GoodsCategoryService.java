package com.jasper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.common_utils.R;
import com.jasper.dto.GoodsCategoryDTO;
import com.jasper.pojo.GoodsCategory;
import com.jasper.vo.GoodsCategoryVo;
import com.jasper.vo.ParentCategory;

import java.util.List;

/**
* @author 21903
* @description 针对表【goods_category(产品分类)】的数据库操作Service
* @createDate 2023-07-22 10:58:15
*/
public interface GoodsCategoryService extends IService<GoodsCategory> {

    Page<GoodsCategory> pageList( String parentId,Integer pageNum, Integer pageSize);

    List<ParentCategory> getParentCategory();

    R<Boolean> addCategory(GoodsCategoryDTO goodsCategoryDTO);

    R<Boolean> updateCategory(GoodsCategoryDTO goodsCategoryDTO);

    Boolean deleteCategory(String id);

    R<List<GoodsCategoryVo>> getCategoryCascade();
}
