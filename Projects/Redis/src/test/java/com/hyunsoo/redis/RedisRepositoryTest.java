package com.hyunsoo.redis;

import com.hyunsoo.redis.config.RedisConfig;
import com.hyunsoo.redis.entity.Person;
import com.hyunsoo.redis.repo.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    Person getRandomPerson() {
        List<Person> list = (List<Person>) personRepository.findAll();
        int randomIndex = (int) (Math.random() * 100);
        return personRepository.findById(list.get(randomIndex).getId())
                .orElseThrow(IllegalArgumentException::new);
    }

    @Test
    void create() {
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Person person = Person.builder()
                    .name("test" + i)
                    .age(i)
                    .build();

            list.add(person);
        }

        personRepository.saveAll(list);
    }

    @Test
    void readAll() {
        List<Person> list = (List<Person>) personRepository.findAll();

        list.forEach(System.out::println);
    }

    @Test
    void readById() {
        Person person = getRandomPerson();

        System.out.println(person);
    }

    @Test
    void deleteById() {
        Person person = getRandomPerson();

        System.out.println(person + "을 삭제합니다.");
        personRepository.delete(person);
        System.out.println(personRepository.findById(person.getId()));
    }
}
