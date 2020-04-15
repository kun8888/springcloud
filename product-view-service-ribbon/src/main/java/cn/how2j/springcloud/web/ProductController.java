package cn.how2j.springcloud.web;

import cn.how2j.springcloud.pojo.Product;
import cn.how2j.springcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description ribbon视图 -- controller
 * @Author: fk
 * @Date: 2020/3/18 16:13
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public Object products(Model m) {
        List<Product> products = productService.listProducts();
        m.addAttribute("ps", products);
        return "products";
    }

}
