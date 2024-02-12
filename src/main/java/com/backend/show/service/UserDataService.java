package com.backend.show.service;

import com.backend.show.entity.UsersDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDataService {

    @Autowired
    private BaseMongoService baseMongoService;


    public String saveUser(UsersDataEntity userData) {
        baseMongoService.save(userData);
        return "saved succesfully";
    }

    public Collection<UsersDataEntity> fetchAllUsersData() {
        return baseMongoService.findAll(UsersDataEntity.class);
    }
}
