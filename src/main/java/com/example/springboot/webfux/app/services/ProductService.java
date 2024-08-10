package com.example.springboot.webfux.app.services;

import java.time.Duration;

import com.example.springboot.webfux.app.models.documents.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Flux<Product> findAllWithDelay(Duration delay);
    Flux<Product> findAllRepeated(long repeatTimes);
    
} 
