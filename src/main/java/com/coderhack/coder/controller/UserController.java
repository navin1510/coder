package com.coderhack.coder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderhack.coder.entity.User;
import com.coderhack.coder.exceptionhandler.UserNotFoundException;
import com.coderhack.coder.service.UserService;

@RestController
public class UserController {
    static final String USER_API_ENDPOINT = "/user";

    @Autowired
    UserService userService;

    //GET /users - Retrieve a list of all registered user
    @GetMapping(USER_API_ENDPOINT)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // GET /users/{userId} - Retrieve the details of a specific user
    @GetMapping("/user/{userId}")
    public User getSpecificUser(@PathVariable(name="userId") String userId) {
        return userService.getSpecificUser(userId);
    }

    // POST /users - Register a new user to the contest
    @PostMapping(USER_API_ENDPOINT)
    public User addUser(@RequestBody User user){
        return userService.addUser(user);  
    }

    // PUT /users/{userId} - Update the score of a specific user
    @PutMapping("/user/{userId}")
    public User UpdateUserScore(@PathVariable(name="userId") String userId,@RequestBody Map<String,Integer> scoremap){
        int score = scoremap.get("score");
        return userService.updateUserScore(userId,score);   
    }

    // DELETE /users/{userId} - Deregister a specific user from the contest
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable(name="userId") String userId){
        userService.deleteUser(userId);  
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
