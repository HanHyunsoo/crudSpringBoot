package com.hyunsoo.swagger.controller;

import com.hyunsoo.swagger.dto.PersonRequest;
import com.hyunsoo.swagger.dto.PersonResponse;
import com.hyunsoo.swagger.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(value = "test")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    @ApiOperation(value = "Person 등록", notes = "신규 Person 등록")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody final PersonRequest request) {
        Long id = personService.save(request);

        return ResponseEntity.created(URI.create("/api/people/" + id)).build();
    }

    @ApiOperation(value = "모든 Person 정보", notes = "모든 Person 정보 출력")
    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll() {
        List<PersonResponse> people = personService.findAll();

        return ResponseEntity.ok(people);
    }

    @ApiOperation(value = "Person 정보", notes = "해당 id의 Person 정보 출력")
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable final Long id) {
        PersonResponse personResponse = personService.findById(id);

        return ResponseEntity.ok(personResponse);
    }

    @ApiOperation(value = "Person 수정", notes = "해당 id의 Person 정보 수정")
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                    @RequestBody final PersonRequest personRequest) {
        personService.update(id, personRequest);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Person 삭제", notes = "해당 id의 Person 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        personService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
