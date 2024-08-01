package com.project.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="Roles")
public class RoleEntity {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private long id;
        private String role;
        @ManyToMany(mappedBy = "roles")
        private Set<UserEntity> user = new HashSet<>();

}
