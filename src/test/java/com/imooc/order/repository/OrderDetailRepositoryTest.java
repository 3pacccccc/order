package com.imooc.order.repository;

import com.imooc.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Runner.class)
@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("asdfqwe");
        orderDetail.setOrderId("qwer789");
        orderDetail.setProductId("erwq787w9er");
        orderDetail.setProductName("黄焖鸡");
        orderDetail.setProductPrice(new BigDecimal("18"));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://91.com");

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest() {
        OrderDetail orderDetail = repository.findById("asdfqwe").orElse(null);
        if (orderDetail != null) {
            orderDetail.setProductQuantity(7);
            OrderDetail result = repository.save(orderDetail);
            Assert.assertNotNull(result);
        }
    }
}