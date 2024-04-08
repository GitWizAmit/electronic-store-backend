package com.amit.electronic.store.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserRequest {
    private Long id;
    private String email;
    private String name;
}
