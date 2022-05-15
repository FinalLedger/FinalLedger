package com.finalledger.repositories;

import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User u SET u.authProvider = ?2 WHERE u.username = ?1")
    User getUserByUsername(@Param("username") String username);
}
