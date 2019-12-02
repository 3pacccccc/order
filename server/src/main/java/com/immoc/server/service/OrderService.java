package com.immoc.server.service;

import com.immoc.server.dto.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
}
