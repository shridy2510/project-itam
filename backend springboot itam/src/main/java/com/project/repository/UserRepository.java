package com.project.repository;

import com.project.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
    boolean existsByEmail(String gmail);
    boolean existsByUsername(String userName);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    @Query("Select a.id FROM UserEntity a WHERE a.userId= :userid")
    Optional<Long> findIdByUserId(@Param("userid") String userid);









}
