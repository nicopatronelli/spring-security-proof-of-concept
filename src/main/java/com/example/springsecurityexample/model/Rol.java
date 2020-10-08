package com.example.springsecurityexample.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for hibernate
public class Rol {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    private String name;

    private Rol(String name) {
        this.name = name;
    }

    public static Rol getUserRol() {
        return new Rol("USER");
    }

    public static Rol getAdminRol() {
        return new Rol("ADMIN");
    }
}
