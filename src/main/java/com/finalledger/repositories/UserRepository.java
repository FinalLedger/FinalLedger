package com.finalledger.repositories;

import com.finalledger.models.AuthenticationType;
import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Resource;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User u SET u.authType = ?2 WHERE u.username = ?1")
    public void updateAuthenticationType(String username, AuthenticationType authType);

    User getUserByUsername(String username);
    User getUserById(long id);
}
