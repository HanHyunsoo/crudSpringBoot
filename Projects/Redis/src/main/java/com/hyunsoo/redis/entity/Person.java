package com.hyunsoo.redis.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "people", timeToLive = 60 * 5)
public class Person {

    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime created;

    @Builder
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("{id: \"%s\", name: \"%s\", age: %d, created: \"%s\"}", id, name, age, created);
    }
}
