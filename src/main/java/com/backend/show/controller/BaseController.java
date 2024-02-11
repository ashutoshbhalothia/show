package com.backend.show.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("start")
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyError.class);

    @Value("${mongo.username:}")
    private String mongoUser;

    @GetMapping("health-check")
    public ResponseEntity<String> checkHealth(@RequestParam(defaultValue = "Buddy", required = false) String name) {
        logger.info("fine");
        return new ResponseEntity<>("Hi" + name + "Everything looks good" + mongoUser, HttpStatus.OK);
    }
}
