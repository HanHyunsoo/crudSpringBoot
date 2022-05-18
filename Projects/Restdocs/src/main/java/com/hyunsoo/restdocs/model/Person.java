package com.hyunsoo.restdocs.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Short age;

    private LocalDateTime created;

    @Builder
    public Person(String name, Short age) {
        this.name = name;
        this.age = age;
        this.created = LocalDateTime.now();
    }

    public void update(String name, Short age) {
        this.name = name;
        this.age = age;
    }
}
