package com.immoc.server.service.impl;

import com.immoc.client.ProductClient;
import com.immoc.common.DecreaseStockInput;
import com.immoc.common.ProductInfoOutput;
import com.immoc.server.dto.CartDTO;
import com.immoc.server.enums.OrderStatusEnum;
import com.immoc.server.enums.PayStatusEnum;
import com.immoc.server.dataobject.OrderDetail;
import com.immoc.server.dataobject.OrderMaster;
import com.immoc.server.dataobject.ProductInfo;
import com.immoc.server.dto.OrderDTO;
import com.immoc.server.repository.OrderDetailRepository;
import com.immoc.server.repository.OrderMasterRepository;
import com.immoc.server.service.OrderService;
import com.immoc.server.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        // 查询商品信息(调用商品product服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());//获取商品id列表
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 计算总价
        BigDecimal orderAmont = new BigDecimal(BigInteger.ZERO);  // 先将总价计为0，再累加
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    // 单价*数量
                    orderAmont = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmont);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setOrderId(orderId);
                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmont);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
