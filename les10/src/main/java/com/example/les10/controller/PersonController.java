package com.example.les10.controller;

import com.example.les10.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {
    private List<Person> persons = new ArrayList<>();

    // constructor waarin alvast enkele Persons in de lijst worden gezet
    public PersonController(){
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        p1.name = "Mark";
        p2.name = "Willem";
        p3.name = "Marleen";
        p1.dob = LocalDate.of(1990,2,15);
        p2.dob = LocalDate.of(1986, 4, 27);
        p3.dob = LocalDate.of(1994, 3, 7);
        p1.gender = 'm';
        p2.gender = 'm';
        p3.gender = 'v';
        persons.addAll(List.of(p1,p2,p3));
    }

    // GET method die de zoveelste persoon uit de lijst returned
    @GetMapping("/{index}")
    public ResponseEntity<Person> getPerson(@PathVariable int index){
        // Gebruik de ArrayList.get() methode om de Person uit de lijst te halen
        Person person = persons.get(index);
        // Return een ResponseEntity.ok voor HTTPStatus 200
        return ResponseEntity.ok(person);
    }

    @DeleteMapping()
    public ResponseEntity<?> deletePerson(@RequestBody String name){

        // Loop door de persons List heen.
        for(Person p : persons) {
            // Kijk of er een Person in de lijst staat met de gegeven naam
            if (p.name.equals(name)) {
                // Zo ja, verwijder deze persoon en return met HttpStatus 204
                persons.remove(p);
                return ResponseEntity.noContent().build();
            }
        }
        // Staat er geen Persoon met de gegeven naam in de List, return dan HttpStatus 404
        return ResponseEntity.notFound().build();
    }

    //Extra:
    @GetMapping("/search")
    public ResponseEntity<List<Person>> getPersonsContaining(@RequestBody String name){
        // Maak een Lijst waarin je de gevonden Persons kunt verzamelen
        List<Person> aggregator = new ArrayList<>();
        // Loop door de lijst om Persons waarvan de naam, of een gedeelte van de naam, overeenkomt met de parameter
        for(Person p : persons){
            if(p.name.contains(name)){
                // Voeg de gevonden persoon toe aan de List
                aggregator.add(p);
            }
        }

        // Als er minstens 1 person is gevonden, return dan HttpStatus 204
        if(aggregator.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        // Als er geen Person gevonden is met de gegeven naam, return dan HttpStatus 404
        return ResponseEntity.noContent().build();
    }



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
