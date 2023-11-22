package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodsCategoryAttributeRelationMapper;
import com.jasper.pojo.GoodsCategoryAttributeRelation;
import com.jasper.service.GoodsCategoryAttributeRelationService;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【goods_category_attribute_relation(产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）)】的数据库操作Service实现
* @createDate 2023-07-26 16:03:23
*/
@Service
public class GoodsCategoryAttributeRelationServiceImpl extends ServiceImpl<GoodsCategoryAttributeRelationMapper, GoodsCategoryAttributeRelation>
    implements GoodsCategoryAttributeRelationService{

}




