package com.jasper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasper.pojo.GoodsAttribute;
import com.jasper.vo.RelationCategoryAttribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 21903
* @description 针对表【goods_attribute(商品属性参数表)】的数据库操作Mapper
* @createDate 2023-07-24 16:08:00
* @Entity com.com.mapper.pojo.GoodsAttribute
*/
@Mapper
public interface GoodsAttributeMapper extends BaseMapper<GoodsAttribute> {

     List<RelationCategoryAttribute> getRelationAttrInfoByCid(Long cid);
}




