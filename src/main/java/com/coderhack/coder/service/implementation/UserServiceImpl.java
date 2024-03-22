package com.coderhack.coder.service.implementation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhack.coder.entity.User;
import com.coderhack.coder.exceptionhandler.UserNotFoundException;
import com.coderhack.coder.repository.UserRepository;
import com.coderhack.coder.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        // return userRepository.findAll();
        return userRepository.findAllByOrderByScoreDesc();
    }

    @Override
    public User getSpecificUser(String userId) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(()->new UserNotFoundException("User not found with username: " + userId));
    }

    @Override
    public User addUser(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User updateUserScore(String userId, int score) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found with username: " + userId);
        }
        if (score < 0 || score > 100) {
            throw new UserNotFoundException("Score must be between 0 and 100");
        }

        User user = userOptional.get();
        user.setScore(score);
        updateBadges(user);
        userRepository.save(user);
        return user;
    }

    private void updateBadges(User user){
        int score = user.getScore();
        Set<String> set;
        if(user.getBadges()==null){
            set = new HashSet<>();
        }else{
            set = new HashSet<>(Arrays.asList(user.getBadges()));
        }
        if (score >= 1 && score <= 30) {
            set.add("Code Ninja");
        } else if (score > 30 && score <= 60) {
            set.add("Code Champ");
        } else if (score > 60 && score <= 100) {
            set.add("Code Master");
        }
        String[] badges = set.toArray(new String[set.size()]);
        user.setBadges(badges);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
    
}
