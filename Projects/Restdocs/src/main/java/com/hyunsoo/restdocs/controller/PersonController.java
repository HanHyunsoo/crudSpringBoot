package com.hyunsoo.restdocs.controller;

import com.hyunsoo.restdocs.service.PersonService;
import com.hyunsoo.restdocs.dto.PersonRequest;
import com.hyunsoo.restdocs.dto.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody final PersonRequest request) {
        Long id = personService.save(request);

        return ResponseEntity.created(URI.create("/api/people/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll() {
        List<PersonResponse> people = personService.findAll();

        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable final Long id) {
        PersonResponse personResponse = personService.findById(id);

        return ResponseEntity.ok(personResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                    @RequestBody final PersonRequest personRequest) {
        personService.update(id, personRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        personService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
