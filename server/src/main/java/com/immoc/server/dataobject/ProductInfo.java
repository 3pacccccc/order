package com.immoc.server.dataobject;

import lombok.Data;
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
public class ProductInfo {
    @Id
    private String productId;

    private String productName;  // 名字

    private BigDecimal productPrice; // 单价

    private Integer productStock; // 库存

    private String productDescription; // 描述

    private String productIcon; // 小图

    private Integer productStatus; // 状态：0正常1下架;

    private Integer categoryType; // 类目编号

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createTime; // 创建时间

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime; // 更新时间
}
