package com.backend.show.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.backend.show.config.CollectionNames.userData;

@Getter
@Setter
@Document(collation = userData)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDataEntity extends BaseMongoEntity{
    private String name;
    private Integer nnumber;
}
