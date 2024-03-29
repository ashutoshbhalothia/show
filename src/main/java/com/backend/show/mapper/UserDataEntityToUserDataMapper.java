package com.backend.show.mapper;

import com.backend.show.entity.UsersDataEntity;
import com.backend.show.model.UserData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataEntityToUserDataMapper {

    public List<UserData> map(List<UsersDataEntity> entities){
        return
                entities
                        .parallelStream()
                        .map(entity ->
                                new  UserData(
                                entity.getName(),
                                entity.getNnumber()
                                )
                        )
                        .toList();
    }
}
