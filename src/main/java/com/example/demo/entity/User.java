package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // создаёт отдельную таблицу для enum,
    // FetchType.EAGER - все enum будут подгружаться сразу (жадный)
    // FetchType.LAZY - enum будут подгружаться по надобности (лёгкий)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) // данное поле будет храниться в отдельной таблице,
    // для которой мы не описывали mapping
    // таблица user_role будет соединяться с текущей таблицей через user_id
    @Enumerated(EnumType.STRING) // enum будет храниться в виде строки
    private Set<Role> roles;
}
