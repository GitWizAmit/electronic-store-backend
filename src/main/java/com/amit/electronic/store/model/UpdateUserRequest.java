package com.amit.electronic.store.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String name;
    private String gender;
    private String description;
}
