package com.hyunsoo.restdocs.service;

import com.hyunsoo.restdocs.model.Person;
import com.hyunsoo.restdocs.dto.PersonRequest;
import com.hyunsoo.restdocs.dto.PersonResponse;
import com.hyunsoo.restdocs.model.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id로 존재하는 Person이 없습니다.")
        );
    }

    @Transactional
    public Long save(PersonRequest personRequest) {
        Person person = Person.builder()
                .name(personRequest.getName())
                .age(personRequest.getAge())
                .build();

        return personRepository.save(person).getId();
    }

    @Transactional(readOnly = true)
    public PersonResponse findById(Long id) {
        Person person = findPersonById(id);

        return new PersonResponse(person);
    }

    @Transactional(readOnly = true)
    public List<PersonResponse> findAll() {
        List<Person> list = personRepository.findAll();

        if (list.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Person 데이터가 없습니다.");
        }

        return list.stream().map(PersonResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, PersonRequest personRequest) {
        Person person = findPersonById(id);
        person.update(personRequest.getName(), personRequest.getAge());

        return person.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        Person person = findPersonById(id);

        personRepository.delete(person);
    }
}
