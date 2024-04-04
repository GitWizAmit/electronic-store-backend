package com.amit.electronic.store.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserRequest {
    private Long id;
    private String email;
    private String name;
}
