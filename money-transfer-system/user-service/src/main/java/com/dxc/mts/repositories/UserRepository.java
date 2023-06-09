package com.dxc.mts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.mts.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByEmailAddress(String emailAddress);
}
