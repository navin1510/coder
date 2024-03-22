package com.coderhack.coder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.coderhack.coder.entity.User;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'userId': ?0}")
    Optional<User> findByUserId(String userId);

    List<User> findAllByOrderByScoreDesc();

    void deleteById(String userId);
}
