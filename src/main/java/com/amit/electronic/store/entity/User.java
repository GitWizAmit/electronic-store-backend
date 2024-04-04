package com.amit.electronic.store.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonInclude
    private Long id;
    @Column(name = "user_name", length = 50)
    private String name;
    @Column(name = "user_email", unique = true, length = 100)
    private String email;
    @Column(name = "user_gender", length = 10)
    private String gender;
    @Column(name = "user_password", length = 100)
    private String password;
    @Column(name = "user_description", length = 1000)
    private String description;

}
