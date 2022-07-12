package com.example.demo.restserv;

public class Resurs {

    private final long id;

    private final String name;

    public Resurs(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
