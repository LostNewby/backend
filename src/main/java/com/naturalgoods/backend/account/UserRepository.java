package com.naturalgoods.backend.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "select u from UserEntity u where u.phoneNumber = ?1 or u.email = ?1")
    Optional<UserEntity> findByEmailOrPhoneNumber(String val);
}
