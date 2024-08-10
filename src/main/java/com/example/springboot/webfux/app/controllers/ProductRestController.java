package com.example.springboot.webfux.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.webfux.app.models.documents.Product;
import com.example.springboot.webfux.app.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public Flux<Product> index() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> show(@PathVariable String id) {
        return productService.findById(id);
    }

}
