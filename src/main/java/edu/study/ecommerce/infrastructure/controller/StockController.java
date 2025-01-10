package edu.study.ecommerce.infrastructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping ("/admin/products/stock")
public class StockController {

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        return "admin/stock/show";
    }



}
