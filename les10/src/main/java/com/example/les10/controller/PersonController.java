package com.example.les10.controller;

import com.example.les10.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {
    private List<Person> persons = new ArrayList<>();

    @GetMapping("")
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        persons.add(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable int id, @RequestBody Person p) {
        if (id >= 0 && id < persons.size()) {
            persons.set(id, p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid id", HttpStatus.NOT_FOUND);
        }
    }
}
