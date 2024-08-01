package com.project.repository;

import com.project.repository.entities.UserEntity;
import org.jboss.resteasy.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
    boolean existsByEmail(String gmail);
    boolean existsByUsername(String userName);











}
