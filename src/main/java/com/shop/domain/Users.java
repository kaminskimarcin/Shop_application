package com.shop.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class Users {
    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean enabled;
    private Set<UserRole> userRole = new HashSet<>(0);

    public Users(String name, String password, String email, boolean enabled) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }


    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "EMAIL", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserRole(Set<UserRole> userUserRole) {
        this.userRole = userUserRole;
    }
}
