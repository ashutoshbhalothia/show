package com.backend.show.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.backend.show.config.CollectionNames.userData;

@Getter
@Setter
@Document(collection = userData)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDataEntity extends BaseMongoEntity{

    @JsonProperty("name")
    private String name;

    @JsonProperty("number")
    private Integer nnumber;
}
