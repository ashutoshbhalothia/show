package com.backend.show.service;

import com.backend.show.entity.UsersDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public List<UsersDataEntity> fetchAllUsersData(String name) {
        if (StringUtils.isEmpty(name))
            return baseMongoService.findAll(UsersDataEntity.class);
        else{
            Query query = new Query().addCriteria(Criteria.where("name").is(name));
            return (baseMongoService.find(query,UsersDataEntity.class));
        }
    }
}
