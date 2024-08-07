package com.ashutosh.expense_tracker.controller;

import com.ashutosh.expense_tracker.entity.User;
import com.ashutosh.expense_tracker.entity.UserModel;
import com.ashutosh.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> read(@Valid @PathVariable("id") Long id){
        return new ResponseEntity<User>(userService.read(id), HttpStatus.FOUND);
    }

    @PutMapping("user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id){
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("user/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "The user has been deleted";
    }
}
