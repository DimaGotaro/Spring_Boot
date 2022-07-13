package com.example.demo.controllers;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.repository.MessRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class Demo_controller {

//    @Value("${name2}") // переменная из application.properties
//    private String name2;

    @GetMapping
    public String hello(@RequestParam(value = "name"/*, required = false*/, defaultValue = "World") String name,
                        // required = false - присваивает null, если значение не передано
                        Model model) {
        model.addAttribute("name", name);

        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "name", defaultValue = "Map") String name,
                        Map<String, Object> model) {
        model.put("name", name);

        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/ws")
    public String lp() {
        return "redirect:/all";
    }

    private final MessRepos messRepos;

    @Autowired
    public Demo_controller(MessRepos messRepos) {
        this.messRepos = messRepos;
    }

    @GetMapping("/all")
    public String all(Map<String, Object> map,
                      @RequestParam(name = "fil", required = false) String tag) {
        Iterable<Message> messages;
        if (tag != null && !tag.isEmpty()) { // если не равно null и не передано пустое значение
            messages = messRepos.findByTag(tag);
            map.put("tag", tag);
        }
        else {
            messages = messRepos.findAll();
        }
        map.put("all", messages);

        return "all";
    }

    @PostMapping("/all")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      @AuthenticationPrincipal User user) {
        Message message = new Message(text, tag, user.getUsername());
        messRepos.save(message);

        return "redirect:/all";
    }

//    @GetMapping("/{id}")
//    public String update(@PathVariable(name = "id") Long id,
//                         Map<String, Object> map) {
//        Message byId = messRepos.findById(id).orElse(null);
//        map.put("obj", byId);
//
//        return "update";
//    }
//
//    @PostMapping("/{id}")
//    public String updateP(@PathVariable(name = "id") Long id,
//                         @ModelAttribute(name = "obj") Message message) {
//        Message message1 = messRepos.findById(id).orElse(null);
//        assert message1 != null;
//        message1.setText(message.getText());
//        message1.setTag(message.getTag());
//        messRepos.save(message1);
//
//        return "redirect:/all";
//    }
//
//    @PostMapping("/d")
//    public String delete(@RequestParam(name = "id") Long id) {
//        messRepos.deleteById(id);
//
//        return "redirect:/all";
//    }
}