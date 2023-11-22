package com.jasper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasper.pojo.GoodsAttributeCategory;
import com.jasper.vo.GoodsAttributeCategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 21903
* @description 针对表【goods_attribute_category(产品属性分类表)】的数据库操作Mapper
* @createDate 2023-07-23 16:19:40
* @Entity com.com.mapper.pojo.GoodsAttributeCategory
*/
@Mapper
public interface GoodsAttributeCategoryMapper extends BaseMapper<GoodsAttributeCategory> {

    Boolean subOneAttr(Long id);

    Boolean subOneParam(Long id);

    List<GoodsAttributeCategoryVO> getListWithAttr();
}




