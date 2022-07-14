package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data // для каждого поля будет создан геттер и сеттер, будет переопределен equals, hashcode,
// и сгенерирован toString метод
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    private String tag;
//    @ManyToOne(fetch = FetchType.EAGER) // одному пользователю соответствует множество сообщений. Каждый раз получая
    // сообщение, получаем информацию об авторе
//    @JoinColumn(name = "user_id") // название колонки
    private String author;
    private String filename;

    public Message(String text, String tag, String author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? getAuthor() : "<none>";
        // если автор не равен null, то возвращается username, если null, то вернётся none
    }
}
