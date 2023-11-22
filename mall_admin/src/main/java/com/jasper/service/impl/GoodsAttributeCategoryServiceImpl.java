package com.jasper.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.mapper.GoodsAttributeCategoryMapper;
import com.jasper.pojo.GoodsAttributeCategory;
import com.jasper.service.GoodsAttributeCategoryService;
import com.jasper.vo.AttributeCategoryVo;
import com.jasper.vo.GoodsAttributeCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 21903
* @description 针对表【goods_attribute_category(产品属性分类表)】的数据库操作Service实现
* @createDate 2023-07-23 16:19:04
*/
@Service
@RequiredArgsConstructor
public class GoodsAttributeCategoryServiceImpl extends ServiceImpl<GoodsAttributeCategoryMapper, GoodsAttributeCategory>
    implements GoodsAttributeCategoryService{

    private final GoodsAttributeCategoryMapper goodsAttributeCategoryMapper;
    @Override
    public Page<GoodsAttributeCategory> pageList(Integer pageNum, Integer pageSize) {
        Page<GoodsAttributeCategory> page = new Page<>(pageNum, pageSize);
        return super.page(page);
    }

    @Override
    public Boolean addAttributeCategory(GoodsAttributeCategory goodsAttributeCategory) {
        goodsAttributeCategory.setAttributeCount(0);
        goodsAttributeCategory.setParamCount(0);
        return super.save(goodsAttributeCategory);
    }

    @Override
    public Boolean deleteAttributeCategory(String id) {
        return super.removeById(id);
    }

    @Override
    public List<AttributeCategoryVo> getAllAttributeCategory() {
        List<GoodsAttributeCategory> list = super.list();
        ArrayList<AttributeCategoryVo> voList = new ArrayList<>();
        list.forEach(
                goodsAttributeCategory -> {
                    AttributeCategoryVo attributeCategoryVo = new AttributeCategoryVo(goodsAttributeCategory.getId(), goodsAttributeCategory.getName());
                    voList.add(attributeCategoryVo);
                }
        );
        return voList;
    }

    @Override
    public List<GoodsAttributeCategoryVO> getListWithAttr() {
        return goodsAttributeCategoryMapper.getListWithAttr();
    }

}




