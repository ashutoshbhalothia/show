package com.backend.show.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
public class BaseMongoEntity implements Serializable {
    @JsonProperty("_id")
    private  String id;
}
