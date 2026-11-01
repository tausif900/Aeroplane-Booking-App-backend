package com.aeroplaneBooking.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeroplaneBooking.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	boolean existsByEmail(String email);

	Optional<UserEntity> findByEmail(String email);
}
