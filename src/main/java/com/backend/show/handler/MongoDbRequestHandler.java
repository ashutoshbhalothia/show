package com.backend.show.handler;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.mapper.UserDataToUserDataEntityMapper;
import com.backend.show.model.UserData;
import com.backend.show.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class MongoDbRequestHandler {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserDataToUserDataEntityMapper userDataToUserDataEntityMapper;

    public String handleSaveRequest(UserData userData) {
        var entity = userDataToUserDataEntityMapper.map(userData);
        return userDataService.saveUser(entity);
    }

    public List<UsersDataEntity> handleFetchRequest() {
        return userDataService.fetchAllUsersData();
    }
}
