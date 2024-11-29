package com.project.dto;

import com.project.repository.entities.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private String userId;
    private Set<String> roles;



}
