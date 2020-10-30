package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
}

