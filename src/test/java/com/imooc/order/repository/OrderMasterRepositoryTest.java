package com.imooc.order.repository;

import com.imooc.order.dataobject.OrderMaster;
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
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("wdxhgrt");
        orderMaster.setBuyerName("candy");
        orderMaster.setBuyerPhone("12345987493");
        orderMaster.setBuyerAddress("cqjtu");
        orderMaster.setBuyerOpenid("rtyw7892");
        orderMaster.setOrderAmount(new BigDecimal("45.66"));
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void update(){
        OrderMaster orderMaster = repository.findById("wdxhgrt").orElse(null);
        if (orderMaster != null){
            orderMaster.setBuyerName("www");
            OrderMaster result = repository.save(orderMaster);
            Assert.assertNotNull(result);
        }

    }
}