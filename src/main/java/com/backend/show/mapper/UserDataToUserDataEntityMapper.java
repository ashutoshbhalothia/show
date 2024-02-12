package com.backend.show.mapper;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.model.UserData;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserDataEntityMapper {

    public UsersDataEntity map(UserData userData){
        UsersDataEntity entity = new UsersDataEntity();
        entity.setName(userData.getName());
        entity.setNnumber(userData.getNumber());
        return entity;
    }
}
