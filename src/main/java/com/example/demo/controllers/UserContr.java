package com.example.demo.controllers;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserContr {
    private UserRepo userRepo;

    public UserContr(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String all_u(Model model) {
        model.addAttribute("allf", userRepo.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String update(@PathVariable User id, // спринг вернёт объект у которого нужный нам id
                         Map<String, Object> map) {
        map.put("objU", id);
        Role[] rol = Role.values();
        map.put("rol", rol);

        return "updateUser";
    }

    @PostMapping("/{id}")
    public String updateP(@PathVariable User id,
                          @ModelAttribute(name = "objU") User user) {
        if( id != null && user.getRoles() != null) {
        id.setUsername(user.getUsername());
        id.setPassword(user.getPassword());
        id.setRoles(user.getRoles());
        userRepo.save(id);

        return "redirect:/user";
        }
        else {
            return "redirect:/{id}";
        }
    }

    @PostMapping("/d")
    public String delete(@RequestParam(name = "id") Long id) {
        userRepo.deleteById(id);

        return "redirect:/user";
    }
}
