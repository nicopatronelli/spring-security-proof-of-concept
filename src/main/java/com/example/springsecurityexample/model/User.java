package com.example.springsecurityexample.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private boolean active;
    @ElementCollection(fetch=FetchType.EAGER) // Not efficient, just for practice purposes
    private List<String> roles;
}
