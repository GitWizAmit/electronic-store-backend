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
    @Column(name = "id")
    @JsonInclude
    private Long id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "email", unique = true, length = 100)
    private String email;
    @Column(name = "gender", length = 10)
    private String gender;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "description", length = 1000)
    private String description;

}
