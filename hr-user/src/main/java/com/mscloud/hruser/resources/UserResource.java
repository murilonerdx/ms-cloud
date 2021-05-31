package com.mscloud.hruser.resources;

import com.mscloud.hruser.entities.User;
import com.mscloud.hruser.repositories.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserRepository repository;


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> listWorker = repository.findAll();
        return ResponseEntity.ok().body(listWorker);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = repository.findById(id).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        User user = repository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}
