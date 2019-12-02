package com.immoc.server.client;


import com.immoc.server.dataobject.ProductInfo;
import com.immoc.server.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);  //使用了requestBody之后需要用post方法提交

    @PostMapping("product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
