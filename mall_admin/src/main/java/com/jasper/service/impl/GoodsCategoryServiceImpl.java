package com.jasper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.R;
import com.jasper.dto.GoodsCategoryDTO;
import com.jasper.mapper.GoodsCategoryMapper;
import com.jasper.pojo.GoodsCategory;
import com.jasper.pojo.GoodsCategoryAttributeRelation;
import com.jasper.service.GoodsCategoryAttributeRelationService;
import com.jasper.service.GoodsCategoryService;
import com.jasper.vo.GoodsCategoryVo;
import com.jasper.vo.ParentCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author 21903
* @description 针对表【goods_category(产品分类)】的数据库操作Service实现
* @createDate 2023-07-22 10:58:15
*/
@Service
@RequiredArgsConstructor
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory>
    implements GoodsCategoryService{

    private final GoodsCategoryAttributeRelationService relationService;
    @Override
    public Page<GoodsCategory> pageList(String parentId,Integer pageNum, Integer pageSize) {
        Page<GoodsCategory> goodsCategoryPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<GoodsCategory> wrapper = new LambdaQueryWrapper<GoodsCategory>().orderByDesc(GoodsCategory::getSort).eq(GoodsCategory::getLevel,parentId);
        Page<GoodsCategory> page = super.page(goodsCategoryPage, wrapper);
        List<GoodsCategory> records = page.getRecords();
        records.forEach(
                goodsCategory -> {
                    if(goodsCategory.getParentId()==0){
                        goodsCategory.setParentName("无上级");
                    }
                    if(goodsCategory.getParentId() !=0) {
                        LambdaQueryWrapper<GoodsCategory> wrapper1 = new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getId, goodsCategory.getParentId());
                        GoodsCategory one = this.getOne(wrapper1);
                        goodsCategory.setParentName(one.getName());
                    }
                });
        page.setRecords(records);
        return page;
    }

    @Override
    public List<ParentCategory> getParentCategory() {
        LambdaQueryWrapper<GoodsCategory> wrapper = new LambdaQueryWrapper<GoodsCategory>().in(GoodsCategory::getLevel, 0, 1);
        List<GoodsCategory> list = super.list(wrapper);
        ArrayList<ParentCategory> parentCategories = new ArrayList<>();
        list.forEach(
                goodsCategory -> {
                    ParentCategory parentCategory = BeanUtil.copyProperties(goodsCategory, ParentCategory.class);
                    parentCategories.add(parentCategory);
                }
        );
        return parentCategories;
    }

    @Override
    @Transactional
    public R<Boolean> addCategory(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtil.copyProperties(goodsCategoryDTO,goodsCategory);
//        设置级别
        setCategoryLevel(goodsCategory);
//         save
        boolean saveI = super.save(goodsCategory);
        boolean saveII = addCategoryAttributeRelation(goodsCategoryDTO, goodsCategory);
        return R.success(saveI && saveII);
    }

    @Override
    public R<Boolean> updateCategory(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory goodsCategory = BeanUtil.copyProperties(goodsCategoryDTO, GoodsCategory.class);
        setCategoryLevel(goodsCategory);
        super.updateById(goodsCategory);
//        修改了先删除在添加
        boolean removeSuccess = deleteCategoryAttributeRelation(String.valueOf(goodsCategory.getId()));
        boolean addSuccess = addCategoryAttributeRelation(goodsCategoryDTO, goodsCategory);
        return R.success(removeSuccess&&addSuccess);
    }

    @Override
    @Transactional
    public Boolean deleteCategory(String id) {
        boolean  remove = deleteCategoryAttributeRelation(id);
        boolean remove1 = super.removeById(id);
        return remove&&remove1;
    }

    @Override
    public R<List<GoodsCategoryVo>> getCategoryCascade() {
        ArrayList<GoodsCategoryVo> result = new ArrayList<>();
        LambdaQueryWrapper<GoodsCategory> eq = new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId, 0);
        List<GoodsCategory> oneCateList = super.list(eq);
        oneCateList.forEach(
                oneCate ->{
//                    二级
                    LambdaQueryWrapper<GoodsCategory> eqTwo = new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId, oneCate.getId());
                    List<GoodsCategory> twoList = super.list(eqTwo);
                    ArrayList<GoodsCategoryVo>  voListTwo = new ArrayList<>();
                    twoList.forEach(
                            twoCate ->{
//                                三级
                                LambdaQueryWrapper<GoodsCategory> eqThree = new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId, twoCate.getId());
                                List<GoodsCategory> threeList = super.list(eqThree);
//                                添加到voList
                                ArrayList<GoodsCategoryVo> voListThree = new ArrayList<>();
                                threeList.forEach(
                                        threeCate ->{
                                            GoodsCategoryVo threeVo = new GoodsCategoryVo(threeCate.getId(), threeCate.getName(),null);
                                            voListThree.add(threeVo);
                                        }
                                );
                                GoodsCategoryVo twoVo = new GoodsCategoryVo(twoCate.getId(), twoCate.getName(), voListThree);
                                voListTwo.add(twoVo);
                            }
                    );
                    GoodsCategoryVo oneVo = new GoodsCategoryVo(oneCate.getId(), oneCate.getName(), voListTwo);
                    result.add(oneVo);
                }
        );
            return R.success(result);
    }

    public  boolean deleteCategoryAttributeRelation(String id){
        LambdaUpdateWrapper<GoodsCategoryAttributeRelation> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(GoodsCategoryAttributeRelation::getProductCategoryId,id);
        return relationService.remove(wrapper);
    }
    public boolean addCategoryAttributeRelation(GoodsCategoryDTO goodsCategoryDTO,GoodsCategory goodsCategory){
//       attribute and category relation
        List<Long> attributeList = goodsCategoryDTO.getAttributeList();
        ArrayList<GoodsCategoryAttributeRelation> relationList = new ArrayList<>();
        attributeList.forEach(
                attributeId ->{
                    GoodsCategoryAttributeRelation relation = new GoodsCategoryAttributeRelation();
                    relation.setProductCategoryId(goodsCategory.getId());
                    relation.setProductAttributeId(attributeId);
                    relationList.add(relation);
                }
        );
        return relationService.saveBatch(relationList);
    }


    /**
     * 添加level的
     * @param goodsCategory 分类
     */
    private void setCategoryLevel(GoodsCategory goodsCategory){
        if(goodsCategory.getParentId()==0){
            goodsCategory.setLevel(0);
            return;
        }
        LambdaQueryWrapper<GoodsCategory> wrapper = new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getId, goodsCategory.getParentId());
        GoodsCategory parentCategory = super.getOne(wrapper);
            goodsCategory.setLevel(parentCategory.getLevel()+1);
    }
}




