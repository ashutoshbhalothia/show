package com.backend.show.controller;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.handler.MongoDbRequestHandler;
import com.backend.show.model.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CrudOeration extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudOeration.class);

    @Autowired
    private MongoDbRequestHandler mongoDbRequestHandler;

    @GetMapping("health-check")
    public ResponseEntity<String> checkHealth(@RequestParam(defaultValue = "Buddy", required = false) String name) {
        LOGGER.info("fine");
        return new ResponseEntity<>("Hi" + name + "Everything looks good" + mongoUser, HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<UsersDataEntity>> fetchAll(){
        var response = mongoDbRequestHandler.handleFetchRequest();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<String> saveData(@RequestBody UserData userData){
        var response = mongoDbRequestHandler.handleSaveRequest(userData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
