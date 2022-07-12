package com.example.demo.controllers;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegisContr {
    private final UserRepo userRepo;

    @Autowired
    public RegisContr(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/regis")
    public String regis() {
        return "regis";
    }

    @PostMapping("/regis")
    public String addN(User user,
                       Map<String, Object> map) {
        User byUsername = userRepo.findByUsername(user.getUsername());
        if (byUsername != null) {
            map.put("message", "Пользователь занят!");
            return "redirect:/regis";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
