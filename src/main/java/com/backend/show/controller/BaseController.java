package com.backend.show.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("start")
public class BaseController {

    @Value("${mongo.username:}")
    String mongoUser;

}
