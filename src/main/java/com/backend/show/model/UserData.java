package com.backend.show.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserData {
    private String name;
    private Integer number;
}
