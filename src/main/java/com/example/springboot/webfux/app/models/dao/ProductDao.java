package com.example.springboot.webfux.app.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.springboot.webfux.app.models.documents.Product;

public interface ProductDao extends ReactiveMongoRepository<Product,String>{

}
