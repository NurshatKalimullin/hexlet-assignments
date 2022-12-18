package exercise.controller;

import exercise.Person;
import exercise.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    @ResponseBody
    public ResponseEntity<List<Person>> listPersons() {
        String query = "SELECT * FROM person;";
        List<Person> persons = jdbc.query(query, new PersonMapper());
        return ResponseEntity.ok(persons);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Person> returnPerson(@PathVariable("id") Long id) {
        String query = "SELECT * FROM person WHERE ID = ?";
        Person foundStudent = jdbc.queryForObject(
                query, new Object[] { id }, new PersonMapper());
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundStudent);
        }
    }
    // END
}
