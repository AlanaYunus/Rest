package com.bootstrap.bootstrap.controller;

import com.bootstrap.bootstrap.model.User;
import com.bootstrap.bootstrap.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/")
public class RestController {

    private UserService service;

    public RestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/admin")
    public List<User> getAllUsers() {
        return service.allUsers();
    }

    @GetMapping("/api/admin/{id}")
    public User getOneUser(@PathVariable("id") Long id) {
        return service.readUser(id);
    }

    @PostMapping("/api/admin")
    public ResponseEntity<User> save(@RequestBody User user) {
        service.createUser(user);
        return new ResponseEntity<User>(service.getUserByName(user.getName()), HttpStatus.OK);
    }

    @PutMapping("/api/admin")
    public ResponseEntity<User> changeUser(@RequestBody User user) {
        service.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/{id}")
    public ResponseEntity<User> changeUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}