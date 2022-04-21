package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(User user);
    void deleteUser(Long id);
    User getUser(Long id);
    List<User> getAllUsers();
    User findUserByEmail(String email);
    List<Role> getAllRoles();
    void editUser(User user);
}

