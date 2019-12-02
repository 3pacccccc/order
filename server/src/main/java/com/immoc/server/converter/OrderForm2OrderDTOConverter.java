package com.immoc.server.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.immoc.server.enums.ResultEnum;
import com.immoc.server.dataobject.OrderDetail;
import com.immoc.server.dto.OrderDTO;
import com.immoc.server.exception.SellException;
import com.immoc.server.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    /**
     * 将OrderForm对象转化为OrderDTO对象
     * @param orderForm：OrderForm的实例化对象
     * @return OrderDTO的实例化对象
     */
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
