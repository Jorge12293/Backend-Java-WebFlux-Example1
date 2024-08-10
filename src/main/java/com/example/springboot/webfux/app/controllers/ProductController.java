package com.example.springboot.webfux.app.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.springboot.webfux.app.models.documents.Product;
import com.example.springboot.webfux.app.services.ProductService;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping({"list","/"})
    public String list(Model model) {
        Flux<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("title", "List of products");
        return "list";
    }

    @GetMapping("list-data-drive")
    public String listDataDrive(Model model) {
        Flux<Product> products = productService.findAllWithDelay(Duration.ofSeconds(1));
        model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 2));
        model.addAttribute("title", "List of products");
        return "list";
    }

    @GetMapping("list-full")
    public String listFull(Model model) {
        Flux<Product> products = productService.findAllRepeated(5000);
        model.addAttribute("products", products);
        model.addAttribute("title", "List of products");
        return "list";
    }

    @GetMapping("list-chunked")
    public String listChunked(Model model) {
        Flux<Product> products = productService.findAllRepeated(5000);
        model.addAttribute("products", products);
        model.addAttribute("title", "List of products");
        return "list-chunked";
    }
}
