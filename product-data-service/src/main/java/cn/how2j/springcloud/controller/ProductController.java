package cn.how2j.springcloud.controller;

import cn.how2j.springcloud.pojo.Product;
import cn.how2j.springcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
	private ProductService productService;
	
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Object products(Model m) {
    	List<Product> ps = productService.listProducts();
    	m.addAttribute("ps", ps);
        return ps;
    }

    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    public Object testRedis(Model m) {
        Object ps = productService.listRedisProducts();
        return ps;
    }

}