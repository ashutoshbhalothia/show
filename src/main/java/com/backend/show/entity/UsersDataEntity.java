package com.backend.show.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collation = "users-data")
public class UsersDataEntity extends BaseMongoEntity{
    private String name;
    private Integer nnumber;
}
