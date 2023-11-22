package com.jasper.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 订单表
 * @TableName good_order
 */
@TableName(value ="good_order")
@Data
public class GoodOrder implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long memberId;

    /**
     * 用户帐号
     */
    private String memberUsername;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    private BigDecimal promotionAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integrationAmount;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    private Integer payType;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private Integer status;

    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private Integer orderType;

    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;

    /**
     * 自动确认时间（天）
     */
    private Integer autoConfirmDay;

    /**
     * 可以获得的积分
     */
    private Integer integration;

    /**
     * 可以获得的成长值
     */
    private Integer growth;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

    /**
     * 省份/直辖市
     */
    private String receiverProvince;

    /**
     * 城市
     */
    private String receiverCity;

    /**
     * 区
     */
    private String receiverRegion;

    /**
     * 详细地址
     */
    private String receiverDetailAddress;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    private Integer confirmStatus;

    /**
     * 删除状态：0->未删除；1->已删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 下单时使用的积分
     */
    private Integer useIntegration;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    private Date receiveTime;

    /**
     * 评价时间
     */
    private Date commentTime;

    /**
     * 提交时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

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
        GoodOrder other = (GoodOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getMemberUsername() == null ? other.getMemberUsername() == null : this.getMemberUsername().equals(other.getMemberUsername()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getFreightAmount() == null ? other.getFreightAmount() == null : this.getFreightAmount().equals(other.getFreightAmount()))
            && (this.getPromotionAmount() == null ? other.getPromotionAmount() == null : this.getPromotionAmount().equals(other.getPromotionAmount()))
            && (this.getIntegrationAmount() == null ? other.getIntegrationAmount() == null : this.getIntegrationAmount().equals(other.getIntegrationAmount()))
            && (this.getCouponAmount() == null ? other.getCouponAmount() == null : this.getCouponAmount().equals(other.getCouponAmount()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getDeliveryCompany() == null ? other.getDeliveryCompany() == null : this.getDeliveryCompany().equals(other.getDeliveryCompany()))
            && (this.getAutoConfirmDay() == null ? other.getAutoConfirmDay() == null : this.getAutoConfirmDay().equals(other.getAutoConfirmDay()))
            && (this.getIntegration() == null ? other.getIntegration() == null : this.getIntegration().equals(other.getIntegration()))
            && (this.getGrowth() == null ? other.getGrowth() == null : this.getGrowth().equals(other.getGrowth()))
            && (this.getReceiverName() == null ? other.getReceiverName() == null : this.getReceiverName().equals(other.getReceiverName()))
            && (this.getReceiverPhone() == null ? other.getReceiverPhone() == null : this.getReceiverPhone().equals(other.getReceiverPhone()))
            && (this.getReceiverPostCode() == null ? other.getReceiverPostCode() == null : this.getReceiverPostCode().equals(other.getReceiverPostCode()))
            && (this.getReceiverProvince() == null ? other.getReceiverProvince() == null : this.getReceiverProvince().equals(other.getReceiverProvince()))
            && (this.getReceiverCity() == null ? other.getReceiverCity() == null : this.getReceiverCity().equals(other.getReceiverCity()))
            && (this.getReceiverRegion() == null ? other.getReceiverRegion() == null : this.getReceiverRegion().equals(other.getReceiverRegion()))
            && (this.getReceiverDetailAddress() == null ? other.getReceiverDetailAddress() == null : this.getReceiverDetailAddress().equals(other.getReceiverDetailAddress()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getConfirmStatus() == null ? other.getConfirmStatus() == null : this.getConfirmStatus().equals(other.getConfirmStatus()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getUseIntegration() == null ? other.getUseIntegration() == null : this.getUseIntegration().equals(other.getUseIntegration()))
            && (this.getPaymentTime() == null ? other.getPaymentTime() == null : this.getPaymentTime().equals(other.getPaymentTime()))
            && (this.getDeliveryTime() == null ? other.getDeliveryTime() == null : this.getDeliveryTime().equals(other.getDeliveryTime()))
            && (this.getReceiveTime() == null ? other.getReceiveTime() == null : this.getReceiveTime().equals(other.getReceiveTime()))
            && (this.getCommentTime() == null ? other.getCommentTime() == null : this.getCommentTime().equals(other.getCommentTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getMemberUsername() == null) ? 0 : getMemberUsername().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getFreightAmount() == null) ? 0 : getFreightAmount().hashCode());
        result = prime * result + ((getPromotionAmount() == null) ? 0 : getPromotionAmount().hashCode());
        result = prime * result + ((getIntegrationAmount() == null) ? 0 : getIntegrationAmount().hashCode());
        result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getDeliveryCompany() == null) ? 0 : getDeliveryCompany().hashCode());
        result = prime * result + ((getAutoConfirmDay() == null) ? 0 : getAutoConfirmDay().hashCode());
        result = prime * result + ((getIntegration() == null) ? 0 : getIntegration().hashCode());
        result = prime * result + ((getGrowth() == null) ? 0 : getGrowth().hashCode());
        result = prime * result + ((getReceiverName() == null) ? 0 : getReceiverName().hashCode());
        result = prime * result + ((getReceiverPhone() == null) ? 0 : getReceiverPhone().hashCode());
        result = prime * result + ((getReceiverPostCode() == null) ? 0 : getReceiverPostCode().hashCode());
        result = prime * result + ((getReceiverProvince() == null) ? 0 : getReceiverProvince().hashCode());
        result = prime * result + ((getReceiverCity() == null) ? 0 : getReceiverCity().hashCode());
        result = prime * result + ((getReceiverRegion() == null) ? 0 : getReceiverRegion().hashCode());
        result = prime * result + ((getReceiverDetailAddress() == null) ? 0 : getReceiverDetailAddress().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getConfirmStatus() == null) ? 0 : getConfirmStatus().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getUseIntegration() == null) ? 0 : getUseIntegration().hashCode());
        result = prime * result + ((getPaymentTime() == null) ? 0 : getPaymentTime().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
        result = prime * result + ((getCommentTime() == null) ? 0 : getCommentTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberUsername=").append(memberUsername);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", freightAmount=").append(freightAmount);
        sb.append(", promotionAmount=").append(promotionAmount);
        sb.append(", integrationAmount=").append(integrationAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", payType=").append(payType);
        sb.append(", status=").append(status);
        sb.append(", orderType=").append(orderType);
        sb.append(", deliveryCompany=").append(deliveryCompany);
        sb.append(", autoConfirmDay=").append(autoConfirmDay);
        sb.append(", integration=").append(integration);
        sb.append(", growth=").append(growth);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverPhone=").append(receiverPhone);
        sb.append(", receiverPostCode=").append(receiverPostCode);
        sb.append(", receiverProvince=").append(receiverProvince);
        sb.append(", receiverCity=").append(receiverCity);
        sb.append(", receiverRegion=").append(receiverRegion);
        sb.append(", receiverDetailAddress=").append(receiverDetailAddress);
        sb.append(", note=").append(note);
        sb.append(", confirmStatus=").append(confirmStatus);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", useIntegration=").append(useIntegration);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", commentTime=").append(commentTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}