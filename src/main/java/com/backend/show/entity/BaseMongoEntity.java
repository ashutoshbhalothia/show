package com.backend.show.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
public class BaseMongoEntity implements Serializable {
    @Id
    @JsonProperty("_id")
    private  String id;
}
