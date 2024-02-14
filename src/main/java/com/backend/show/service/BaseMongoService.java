package com.backend.show.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BaseMongoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMongoService.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Object object,String collectionName){
        mongoTemplate.save(object,collectionName);
    }

    public<T> List<T> findAll(Class<T> clazz,String collectionName){
        try {
            return mongoTemplate.findAll(clazz,collectionName);
        }catch (Exception e){
            LOGGER.error("Error encountered while fetching data from DB : {}",e.getMessage());
            return Collections.emptyList();
        }
    }
}
