package com.backend.show.service;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.exception.CustomException;
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
        try {
            baseMongoService.save(entity,userData);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
        return "saved succesfully";
    }

    public List<UsersDataEntity> fetchUsersData(String name) {
        if (StringUtils.isEmpty(name))
            throw new CustomException("Unable to fetch");
        else{
            Query query = new Query().addCriteria(Criteria.where("name").is(name));
            return baseMongoService.find(query,UsersDataEntity.class);
        }
    }
}
