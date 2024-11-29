package com.project.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@Table(name="Users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String username;
    private String email;
    private String firstName;
    private String phoneNumber;
    private String lastName;
    @ManyToMany(fetch=FetchType.EAGER,cascade = { CascadeType.PERSIST, CascadeType.MERGE, })

    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy="userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetEntity> assets;
    @OneToMany(mappedBy= "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetLogEntity> assetLogAdmin;
    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssetLogEntity> assetLogUser;







}
