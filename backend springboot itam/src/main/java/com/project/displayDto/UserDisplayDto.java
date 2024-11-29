package com.project.displayDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDisplayDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;
    private Long id;
    private String phoneNumber;
    private Set<String> roles;


}
