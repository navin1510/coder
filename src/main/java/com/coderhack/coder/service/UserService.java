package com.coderhack.coder.service;

import java.util.List;


import com.coderhack.coder.entity.User;
import com.coderhack.coder.exceptionhandler.UserNotFoundException;

public interface UserService {
    List<User> getAllUsers();
    User getSpecificUser(String userId) throws UserNotFoundException;
    User addUser(User user);
    User updateUserScore(String userId,int score);
    void deleteUser(String userId);
}
