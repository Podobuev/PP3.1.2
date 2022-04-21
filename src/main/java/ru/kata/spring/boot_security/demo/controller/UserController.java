package ru.kata.spring.boot_security.demo.controller;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan(basePackages = "demo")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String startPage() {
        return "startPage";
    }


    @GetMapping(value = "/registration")
    public String registration(ModelMap model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addRegistration(@ModelAttribute("user") @Valid User user) {

        userService.saveUser(user);
        User user1 = userService.findUserByEmail(user.getEmail());
        System.out.println(user1);

        return "redirect:/login";
    }

    @GetMapping(value = "/user")
    public String getUser(Principal principal, ModelMap model) {
        String email = principal.getName();
        System.out.println(email);
        model.addAttribute("user", userService.findUserByEmail(email));
        return "user";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(Principal principal, ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("admin",
                userService.findUserByEmail(principal.getName()));
        return "admin1";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        System.out.println("DELETE USER ID:" + id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", userService.getAllRoles());
        return "edit";
    }

    @PatchMapping(value = "/admin/edit/{id}")
    public String edit(@ModelAttribute("user") @Valid User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }

}
