package com.example.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
