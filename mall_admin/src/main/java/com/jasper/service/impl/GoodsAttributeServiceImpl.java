package com.jasper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.R;
import com.jasper.mapper.GoodsAttributeCategoryMapper;
import com.jasper.mapper.GoodsAttributeMapper;
import com.jasper.pojo.GoodsAttribute;
import com.jasper.pojo.GoodsAttributeCategory;
import com.jasper.pojo.GoodsCategoryAttributeRelation;
import com.jasper.service.GoodsAttributeCategoryService;
import com.jasper.service.GoodsAttributeService;
import com.jasper.service.GoodsCategoryAttributeRelationService;
import com.jasper.vo.AttributeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author 21903
* @description 针对表【goods_attribute(商品属性参数表)】的数据库操作Service实现
* @createDate 2023-07-24 16:08:08
*/
@Service
@RequiredArgsConstructor
public class GoodsAttributeServiceImpl extends ServiceImpl<GoodsAttributeMapper, GoodsAttribute>
    implements GoodsAttributeService{

    private final GoodsAttributeCategoryService attributeCategoryService;
    private final GoodsCategoryAttributeRelationService relationService;
    private final GoodsAttributeCategoryMapper attributeCategoryMapper;
    private final GoodsAttributeMapper attributeMapper;
    @Override
    public Page<GoodsAttribute> pageList(Long cid, Integer pageNum, Integer pageSize, Integer type) {
        Page<GoodsAttribute> goodsAttributePage = new Page<>();
        LambdaQueryWrapper<GoodsAttribute> wrapper = new LambdaQueryWrapper<GoodsAttribute>().
                eq(GoodsAttribute::getType, type).
                eq(GoodsAttribute::getProductAttributeCategoryId, cid);
        return super.page(goodsAttributePage,wrapper);
    }

    @Override
    public Boolean addAttribute(GoodsAttribute goodsAttribute) {
        super.save(goodsAttribute);
        LambdaUpdateWrapper<GoodsAttributeCategory> updateWrapper = new LambdaUpdateWrapper<>();
        if (goodsAttribute.getType()==0){
            updateWrapper.setSql("attribute_count=attribute_count+1");
        } else if (goodsAttribute.getType()==1) {
            updateWrapper.setSql("param_count=param_count+1");
        }
        updateWrapper.eq(GoodsAttributeCategory::getId,goodsAttribute.getProductAttributeCategoryId());
        attributeCategoryService.update(updateWrapper);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteAttribute(String id) {
        GoodsAttribute goodAttribute = super.getById(id);
        if(goodAttribute.getType()==0){
            attributeCategoryMapper.subOneAttr(goodAttribute.getProductAttributeCategoryId());
        }else if (goodAttribute.getType() == 1){
            attributeCategoryMapper.subOneParam(goodAttribute.getProductAttributeCategoryId());
        }
        boolean b = super.removeById(id);
        return b;
    }

    @Override
    public R<?> getRelationAttrInfoByCid(Long cid) {
        LambdaQueryWrapper<GoodsCategoryAttributeRelation> wrapper = new LambdaQueryWrapper<GoodsCategoryAttributeRelation>().eq(GoodsCategoryAttributeRelation::getProductCategoryId, cid);
        List<GoodsCategoryAttributeRelation> list = relationService.list(wrapper);
        ArrayList<AttributeVo> attributeVos = new ArrayList<>();
        list.forEach(
                item ->{
                    AttributeVo attributeVo = new AttributeVo(item.getProductAttributeId());
                    attributeVos.add(attributeVo);
                }
        );
//        List<RelationCategoryAttribute> list =  attributeMapper.getRelationAttrInfoByCid(cid);
        return R.success(attributeVos);
    }

    @Override
    public R<?> getRelationAttrInfoByAttrCateId(Long acid) {
        LambdaQueryWrapper<GoodsAttribute> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsAttribute::getProductAttributeCategoryId,acid);
        List<GoodsAttribute> attrList = super.list(wrapper);
        return R.success(attrList);
    }
}




