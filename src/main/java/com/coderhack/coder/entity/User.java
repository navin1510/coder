package com.coderhack.coder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String name;
    private int score=0;
    private String[] badges;
}
