package com.jasper.service.impl;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.R;
import com.jasper.dto.GoodsDTO;
import com.jasper.dto.GoodsSearchDTO;
import com.jasper.pojo.Goods;
import com.jasper.pojo.GoodsBrand;
import com.jasper.pojo.GoodsCategory;
import com.jasper.service.GoodsBrandService;
import com.jasper.service.GoodsCategoryService;
import com.jasper.service.GoodsService;
import com.jasper.mapper.GoodsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 21903
 * @description 针对表【goods(商品信息)】的数据库操作Service实现
 * @createDate 2023-07-27 17:38:26
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

    private final GoodsCategoryService goodsCategoryService;
    private final GoodsBrandService goodsBrandService;
    @Override
    public Page<Goods> pageGoodsList(GoodsSearchDTO goodsSearchDTO) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        if(!StrUtil.isBlank(goodsSearchDTO.getName())){
            wrapper.like(Goods::getName, goodsSearchDTO.getName());
        }
        Optional.ofNullable(goodsSearchDTO.getId())
                .ifPresent(id -> wrapper.eq(Goods::getId, id));

        if(goodsSearchDTO.getGoodCategoryId() !=null){
            ArrayList<Long> idList = new ArrayList<>();
            List<Long> resultList = collectSubIds(goodsSearchDTO.getGoodCategoryId(),idList);
            if(resultList.size()>0){
                wrapper.in(Goods::getGoodCategoryId,resultList);
            }else{
                wrapper.eq(Goods::getGoodCategoryId, goodsSearchDTO.getGoodCategoryId());
            }
        }

        if(goodsSearchDTO.getBrandId()!=null){
            wrapper.eq(Goods::getBrandId, goodsSearchDTO.getBrandId());
        }
        if (goodsSearchDTO.getIsPublished()!=null){
            wrapper.eq(Goods::getIsPublished, goodsSearchDTO.getIsPublished());
        }
        if(goodsSearchDTO.getIsVerify()!=null){
            wrapper.eq(Goods::getIsVerify, goodsSearchDTO.getIsVerify());
        }

        Page<Goods> goodsPage = new Page<>(goodsSearchDTO.getPageNum(), goodsSearchDTO.getPageSize());
        Page<Goods> page = super.page(goodsPage, wrapper);
        page.getRecords().forEach(
                goods -> {
                    GoodsBrand brand = goodsBrandService.getById(goods.getBrandId());
                    goods.setBrand(brand.getName());
                }
        );
        return page;
    }

    @Override
    public R<?> addGoods(GoodsDTO goodsDTO) {
        return null;
    }

    @Override
    public GoodsDTO getGoodById(String id) {
        return null;
    }

    @Override
    public Boolean deleteGoods(Long id) {
        return null;
    }

    /**
     *
     * @param categoryId  the category id passed from the front end        level 1 2 3
     * @param idList   递归调用后 会将子分类id传入一个新的idList  新的idList没有与之前的idList合并 所以这里新传入一个idList
     * @return
     */
    private List<Long> collectSubIds(Long categoryId,List<Long> idList) {                                //1
        LambdaQueryWrapper<GoodsCategory> cateWrapper = new LambdaQueryWrapper<>();
        cateWrapper.eq(GoodsCategory::getParentId,categoryId ); //1
        List<GoodsCategory> list = goodsCategoryService.list(cateWrapper); //2 3
        list.forEach(
                goodsCategory -> {
                    idList.add(goodsCategory.getId());
                    collectSubIds(goodsCategory.getId(),idList);
                }
        );
        return idList;
    }
}






