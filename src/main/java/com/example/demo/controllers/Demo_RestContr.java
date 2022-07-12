package com.example.demo.controllers;

import com.example.demo.restserv.Resurs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController // передаёт в GetMapping не представление страницы html, а значение(текст)
public class Demo_RestContr {

    @GetMapping("/c") // curl localhost:8080/c - вызвать из командной строки bash
    public String cons() {
        return "Hello, Priblyda!";
    }

    private static final String temp = "Hello, %s!";
    private final AtomicLong count = new AtomicLong();

    @GetMapping("/r")
    public Resurs res(@RequestParam(value = "name", defaultValue = "Kaya") String name) {
        return new Resurs(count.incrementAndGet(), String.format(temp, name));
    }
}
