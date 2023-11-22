package com.jasper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.common_utils.R;
import com.jasper.pojo.GoodsAttribute;

/**
* @author 21903
* @description 针对表【goods_attribute(商品属性参数表)】的数据库操作Service
* @createDate 2023-07-24 16:08:08
*/
public interface GoodsAttributeService extends IService<GoodsAttribute> {

    Page<GoodsAttribute> pageList(Long cid, Integer pageNum, Integer pageSize, Integer type);

    Boolean addAttribute(GoodsAttribute goodsAttribute);

    Boolean deleteAttribute(String id);

    R<?> getRelationAttrInfoByCid(Long cid);

    R<?> getRelationAttrInfoByAttrCateId(Long acid);
}
