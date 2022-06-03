package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM users WHERE email= ?
   // @Query("SELECT s FROM User s WHERE s.email= ?1")
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);

}
