package cn.how2j.springcloud.client;

import cn.how2j.springcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description TODO
 * @Author: fk
 * @Date: 2020/3/18 16:09
 */
@Component
public class ProductClientRibbon {

    @Autowired
    private RestTemplate restTemplate;

    public List<Product> listPorducts() {
        return restTemplate.getForObject("http://PRODUCT-DATA-SERVICE/product/products", List.class);
    }

}
