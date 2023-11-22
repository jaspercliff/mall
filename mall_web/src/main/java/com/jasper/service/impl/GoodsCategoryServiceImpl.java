package com.jasper.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.R;
import com.jasper.mapper.GoodsCategoryMapper;
import com.jasper.pojo.GoodsCategory;
import com.jasper.service.GoodsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jasper.config.RedisConstants.CACHE_PRODUCT_CATEGORY;

/**
* @author 21903
* @description 针对表【goods_category(产品分类)】的数据库操作Service实现
* @createDate 2023-06-23 16:14:14
*/
@Service
@RequiredArgsConstructor
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory>
    implements GoodsCategoryService{

    private final StringRedisTemplate stringRedisTemplate;
    String key = CACHE_PRODUCT_CATEGORY;
    @Override
    public R<?> getOneCategory() {
        List<String> categoryList = stringRedisTemplate.opsForList().range(key, 0, -1);
        if (categoryList!=null && !categoryList.isEmpty()){
            ArrayList<GoodsCategory> goodsCategories = new ArrayList<>();
            categoryList.forEach(
                    category ->{
                        GoodsCategory bean = JSONUtil.toBean(category, GoodsCategory.class);
                        goodsCategories.add(bean);
                    }
            );
            return R.success(goodsCategories);
        }
//     从数据库查询
        List<GoodsCategory> oneCategooryList = this.list(new LambdaUpdateWrapper<GoodsCategory>().
                eq(GoodsCategory::getLevel, 0).
                orderByDesc(GoodsCategory::getSort));
//     保存到redis
        ArrayList<String> oneCategories = new ArrayList<>();
        oneCategooryList.forEach(
                oneCategory ->{
                    String jsonStr = JSONUtil.toJsonStr(oneCategory);
                    oneCategories.add(jsonStr);
                }
        );
        stringRedisTemplate.opsForList().rightPushAll(key,oneCategories);
        stringRedisTemplate.expire(key,2L, TimeUnit.MINUTES);
        return R.success(oneCategooryList);
    }

    @Override
    public R<?> getTwoCategory(String oneCategoryId) {
        List<GoodsCategory> list = this.list(new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId, oneCategoryId));
        return R.success(list);
    }

    @Override
    public R<?> getHotCategory() {
        List<GoodsCategory> list = this.list(new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getNavStatus, 1));
        return R.success(list);
    }
}




