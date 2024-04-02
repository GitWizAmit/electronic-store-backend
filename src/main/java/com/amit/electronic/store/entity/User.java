package com.amit.electronic.store.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
    private String userName;
    @Column(name = "user_email", unique = true, length = 100)
    private String userEmail;
    @Column(name = "user_gender", length = 10)
    private String userGender;
    @Column(name = "user_password", length = 100)
    private String userPassword;
    @Column(name = "user_description", length = 1000)
    private String userDescription;

}

