package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
