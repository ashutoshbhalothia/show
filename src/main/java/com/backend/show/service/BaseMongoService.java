package com.backend.show.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BaseMongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Object object,String collectionName){
        mongoTemplate.save(object,collectionName);
    }

    public<T> List<T> findAll(Class<T> clazz,String collectionName){
        try {
            return mongoTemplate.findAll(clazz,collectionName);
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
