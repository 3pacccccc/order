package com.immoc.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.immoc.server.dataobject.OrderDetail;
import com.immoc.server.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 将结果为null的自动过滤不返回
public class OrderDTO {

    private String orderId; // 订单ID

    private String buyerName; // 买家名字

    private String buyerPhone; // 买家手机号

    private String buyerAddress; //买家地址

    private String buyerOpenid; // 买家微信openid

    private BigDecimal orderAmount; // 订单总金额

    private Integer orderStatus; //订单状态 默认为新下单

    private Integer payStatus; // 支付状态，默认为0未支付

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;  // 创建时间

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime; // 更新时间

    List<OrderDetail> orderDetailList;
}

// 回滚测试
// 回滚测试2
// 回滚测试3