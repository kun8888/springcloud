package cn.how2j.springcloud.service;

import cn.how2j.springcloud.client.ProductClientFeign;
import cn.how2j.springcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author: fk
 * @Date: 2020/3/18 16:11
 */
@Service
public class ProductService {

    @Autowired
    private ProductClientFeign productClientFeign;

    public List<Product> listProducts() {
        return productClientFeign.listProducts();
    }

}
