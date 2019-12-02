package com.immoc.server.controller;


import com.immoc.server.converter.OrderForm2OrderDTOConverter;
import com.immoc.server.enums.ResultEnum;
import com.immoc.server.exception.SellException;
import com.immoc.server.service.OrderService;
import com.immoc.server.vo.ResultVo;
import com.immoc.server.dto.OrderDTO;
import com.immoc.server.form.OrderForm;
import com.immoc.server.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数错误，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVoUtil.success(map);
    }
}
