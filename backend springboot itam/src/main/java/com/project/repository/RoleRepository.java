package com.project.repository;

import com.project.repository.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface RoleRepository extends JpaRepository <RoleEntity, Long> {
    RoleEntity findByRole(String role);
    Set<RoleEntity> findByRoleIn(Set<String> roleNames);

    @Query("SELECT a.role FROM RoleEntity a")
    List<String> findAllRoles();

}
