package com.backend.show.service;

import com.backend.show.entity.UsersDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.backend.show.config.CollectionNames.userData;

@Service
public class UserDataService {

    @Autowired
    private BaseMongoService baseMongoService;


    public String saveUser(UsersDataEntity entity) {
        baseMongoService.save(entity,userData);
        return "saved succesfully";
    }

    public List<UsersDataEntity> fetchAllUsersData() {
        return baseMongoService.findAll(UsersDataEntity.class,userData);
    }
}
