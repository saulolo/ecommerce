package edu.study.ecommerce.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/admin/products")
public class ProductController {

    @GetMapping("/create")
    public String product() {
        return "admin/products/create";
    }
}
