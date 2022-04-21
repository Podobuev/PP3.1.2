package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private Set<User> users;


    public Role(Long id, String role, Set<User> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return getRole();
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
