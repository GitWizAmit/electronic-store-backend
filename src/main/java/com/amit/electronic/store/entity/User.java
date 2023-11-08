package com.amit.electronic.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    public String id;
    @Column(name = "user_name", length = 10)
    public String name;
    @Column(name = "user_email", unique = true, length = 15)
    public String email;
    @Column(name = "user_gender")
    public String gender;
    @Column(name = "user_password", length = 12)
    public String password;
    @Column(name = "user_description", length = 1000)
    public String about;

}
