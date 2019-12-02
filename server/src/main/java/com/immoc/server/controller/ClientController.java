package com.immoc.server.controller;

import com.immoc.server.client.ProductClient;
import com.immoc.server.dto.CartDTO;
import com.immoc.server.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        RestTemplate restTemplate = new RestTemplate();

        // 方法1：直接写死url
//        String url = "http://127.0.0.1:8080/server/msg";

        //方法2：动态获取到其他服务的host跟port，再拼凑出url
//        ServiceInstance serviceInstance = loadBalancerClient.choose("product");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/server/msg";

        // 方法3 将restTemplate配置到config中去
//        String response = restTemplate.getForObject("http://PRODUCT/server/msg", String.class);

//        String response = restTemplate.getForObject(url, String.class);
//        log.info("response={}", response);
//        return response;

        // 方法4 使用feign 步骤：1.在pom文件中引入feign的依赖 2.在启动文件OrderApplication里面添加EnableFeignClients的注解
        // 3.在client包下面创建一个接口，声明要引用哪个服务下的哪个路由  4. 在Autowired中引入进来
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("789jhiortlmtqqq", "dsfq865321"));
        log.info("response={}", productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("789jhiortlmtqqq", 8)));
        return "success";
    }
}
