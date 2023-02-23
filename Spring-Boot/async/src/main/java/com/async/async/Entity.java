package com.async.async;


import lombok.Data;

@Data
public class Entity {
    private String  name;
    private String age;

    public Entity(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
