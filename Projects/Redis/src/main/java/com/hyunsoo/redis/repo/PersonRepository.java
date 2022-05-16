package com.hyunsoo.redis.repo;

import com.hyunsoo.redis.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}
