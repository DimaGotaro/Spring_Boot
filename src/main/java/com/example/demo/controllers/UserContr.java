package com.example.demo.controllers;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.repository.MessRepos;
import com.example.demo.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
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
    public String update(@PathVariable(name = "id") Long id,
                         Map<String, Object> map) {
        User byId = userRepo.findById(id).orElse(null);
        map.put("objU", byId);

        return "updateUser";
    }

    @PostMapping("/{id}")
    public String updateP(@PathVariable(name = "id") Long id,
                          @ModelAttribute(name = "objU") User user) {
        User byId2 = userRepo.findById(id).orElse(null);
        assert byId2 != null;
        byId2.setUsername(user.getUsername());
        byId2.setPassword(user.getPassword());
        userRepo.save(byId2);

        return "redirect:/user";
    }

    @PostMapping("/d")
    public String delete(@RequestParam(name = "id") Long id) {
        userRepo.deleteById(id);

        return "redirect:/user";
    }
}
