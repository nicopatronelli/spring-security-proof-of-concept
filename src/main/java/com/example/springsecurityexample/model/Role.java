package com.example.springsecurityexample.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for hibernate
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    private String name;

    private Role(String name) {
        this.name = name;
    }

    public static Role getUserRol() {
        return new Role("ROLE_USER");
    }

    public static Role getAdminRol() {
        return new Role("ROLE_ADMIN");
    }
}
