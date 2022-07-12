package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data // для каждого поля будет создан геттер и сеттер, будет переопределен equals, hashcode,
// и сгенерирован toString метод
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String tag;
    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
}
