package com.example.springboot.webfux.app.services.impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.webfux.app.models.dao.ProductDao;
import com.example.springboot.webfux.app.models.documents.Product;
import com.example.springboot.webfux.app.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Flux<Product> findAll() {
        return dao.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        }).doOnNext(product -> log.info(product.getName()));
    }

    @Override
    public Mono<Product> findById(String id) {
        // Mono<Product> product = dao.findById(id);
        Flux<Product> products = dao.findAll();
        return products.filter(p -> p.getId().equals(id))
                .next()
                .doOnNext(pro -> log.info(pro.getName()));
    }

    @Override
    public Flux<Product> findAllWithDelay(Duration delay) {
        return dao.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        }).delayElements(delay).doOnNext(product -> log.info(product.getName()));
    }

    @Override
    public Flux<Product> findAllRepeated(long repeatTimes) {
        return dao.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        }).repeat(repeatTimes).doOnNext(product -> log.info(product.getName()));
    }

}
