package ru.otus.spring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(schema = "auth", name = "users")
public class User {

    @Id
    private String userId;

    private String username;

    private String email;

    private String password;
}