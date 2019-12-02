package com.immoc.server.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderMaster {
    /*
     * 买家订单
     * */
    @Id
    private String orderId; // 订单ID

    private String buyerName; // 买家名字

    private String buyerPhone; // 买家手机号

    private String buyerAddress; //买家地址

    private String buyerOpenid; // 买家微信openid

    private BigDecimal orderAmount; // 订单总金额

    private Integer orderStatus; //订单状态 默认为新下单

    private Integer payStatus; // 支付状态，默认为0未支付

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createTime;  // 创建时间

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime; // 更新时间

//    @Transient // 在orm到数据库得时候会忽略此字段
//    private List<OrderDetail> orderDetailList;
}
