package com.example.demo.controllers;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessRepos;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class MessContr {

    private MessRepos messRepos;

    public MessContr(MessRepos messRepos) {
        this.messRepos = messRepos;
    }

    @GetMapping("/{id}")
    public String update(@PathVariable(name = "id") Long id, // можно сразу получить объект, след метод
                         Map<String, Object> map) {
        Message byId = messRepos.findById(id).orElse(null);
        map.put("obj", byId);

        return "update";
    }

    @PostMapping("/{id}")
    public String updateP(@PathVariable(name = "id") Message message1,
                          @ModelAttribute(name = "obj") Message message) {
        message1.setText(message.getText());
        message1.setTag(message.getTag());
        messRepos.save(message1);

        return "redirect:/all";
    }

    @PostMapping("/d")
    public String delete(@RequestParam(name = "id") Long id) {
        messRepos.deleteById(id);

        return "redirect:/all";
    }
}
