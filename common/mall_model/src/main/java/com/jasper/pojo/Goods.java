package com.jasper.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long brandId;

    /**
     * 
     */
    private Long goodCategoryId;

    private Long goodAttributeCategoryId;
    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String picture;

    /**
     * 删除状态：0->未删除；1->已删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Integer isPublished;

    /**
     * 新品状态:0->不是新品；1->新品
     */
    private Integer isNew;

    /**
     * 推荐状态；0->不推荐；1->推荐
     */
    private Integer isRecommend;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    private Integer isVerify;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存预警值
     */
    private Integer lowStock;

    /**
     * 商品重量，默认为克
     */
    private BigDecimal weight;

    /**
     * 
     */
    private String keywords;

    /**
     * 促销开始时间
     */
    private Date promotionStartTime;

    /**
     * 促销结束时间
     */
    private Date promotionEndTime;

    /**
     * 活动限购数量
     */
    private Integer promotionPerLimit;

    /**
     * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用满减价格；4->限时购
     */
    private Integer promotionType;

    /**
     * 促销价格
     */
    private Integer promotionPrice;

    /**
     * 买这个商品赠送的成长值
     */
    private Integer giftGrowth;

    /**
     * 积分
     */
    private Integer giftPoint;

    /**
     * 使用积分限制
     */
    private Integer pointLimit;

    /**
     * 是否包邮 0不 1 包邮
     */
    private Integer isPostFree;

    @TableField(exist = false)
    private String brand;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Goods other = (Goods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getGoodCategoryId() == null ? other.getGoodCategoryId() == null : this.getGoodCategoryId().equals(other.getGoodCategoryId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getIsPublished() == null ? other.getIsPublished() == null : this.getIsPublished().equals(other.getIsPublished()))
            && (this.getIsNew() == null ? other.getIsNew() == null : this.getIsNew().equals(other.getIsNew()))
            && (this.getIsRecommend() == null ? other.getIsRecommend() == null : this.getIsRecommend().equals(other.getIsRecommend()))
            && (this.getIsVerify() == null ? other.getIsVerify() == null : this.getIsVerify().equals(other.getIsVerify()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getSale() == null ? other.getSale() == null : this.getSale().equals(other.getSale()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
            && (this.getLowStock() == null ? other.getLowStock() == null : this.getLowStock().equals(other.getLowStock()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
            && (this.getPromotionStartTime() == null ? other.getPromotionStartTime() == null : this.getPromotionStartTime().equals(other.getPromotionStartTime()))
            && (this.getPromotionEndTime() == null ? other.getPromotionEndTime() == null : this.getPromotionEndTime().equals(other.getPromotionEndTime()))
            && (this.getPromotionPerLimit() == null ? other.getPromotionPerLimit() == null : this.getPromotionPerLimit().equals(other.getPromotionPerLimit()))
            && (this.getPromotionType() == null ? other.getPromotionType() == null : this.getPromotionType().equals(other.getPromotionType()))
            && (this.getPromotionPrice() == null ? other.getPromotionPrice() == null : this.getPromotionPrice().equals(other.getPromotionPrice()))
            && (this.getGiftGrowth() == null ? other.getGiftGrowth() == null : this.getGiftGrowth().equals(other.getGiftGrowth()))
            && (this.getGiftPoint() == null ? other.getGiftPoint() == null : this.getGiftPoint().equals(other.getGiftPoint()))
            && (this.getPointLimit() == null ? other.getPointLimit() == null : this.getPointLimit().equals(other.getPointLimit()))
            && (this.getIsPostFree() == null ? other.getIsPostFree() == null : this.getIsPostFree().equals(other.getIsPostFree()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getGoodCategoryId() == null) ? 0 : getGoodCategoryId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getIsPublished() == null) ? 0 : getIsPublished().hashCode());
        result = prime * result + ((getIsNew() == null) ? 0 : getIsNew().hashCode());
        result = prime * result + ((getIsRecommend() == null) ? 0 : getIsRecommend().hashCode());
        result = prime * result + ((getIsVerify() == null) ? 0 : getIsVerify().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getSale() == null) ? 0 : getSale().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getLowStock() == null) ? 0 : getLowStock().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getPromotionStartTime() == null) ? 0 : getPromotionStartTime().hashCode());
        result = prime * result + ((getPromotionEndTime() == null) ? 0 : getPromotionEndTime().hashCode());
        result = prime * result + ((getPromotionPerLimit() == null) ? 0 : getPromotionPerLimit().hashCode());
        result = prime * result + ((getPromotionType() == null) ? 0 : getPromotionType().hashCode());
        result = prime * result + ((getPromotionPrice() == null) ? 0 : getPromotionPrice().hashCode());
        result = prime * result + ((getGiftGrowth() == null) ? 0 : getGiftGrowth().hashCode());
        result = prime * result + ((getGiftPoint() == null) ? 0 : getGiftPoint().hashCode());
        result = prime * result + ((getPointLimit() == null) ? 0 : getPointLimit().hashCode());
        result = prime * result + ((getIsPostFree() == null) ? 0 : getIsPostFree().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", brandId=").append(brandId);
        sb.append(", goodCategoryId=").append(goodCategoryId);
        sb.append(", name=").append(name);
        sb.append(", picture=").append(picture);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", isPublished=").append(isPublished);
        sb.append(", isNew=").append(isNew);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", isVerify=").append(isVerify);
        sb.append(", sort=").append(sort);
        sb.append(", sale=").append(sale);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", lowStock=").append(lowStock);
        sb.append(", weight=").append(weight);
        sb.append(", keywords=").append(keywords);
        sb.append(", promotionStartTime=").append(promotionStartTime);
        sb.append(", promotionEndTime=").append(promotionEndTime);
        sb.append(", promotionPerLimit=").append(promotionPerLimit);
        sb.append(", promotionType=").append(promotionType);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", giftGrowth=").append(giftGrowth);
        sb.append(", giftPoint=").append(giftPoint);
        sb.append(", pointLimit=").append(pointLimit);
        sb.append(", isPostFree=").append(isPostFree);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}