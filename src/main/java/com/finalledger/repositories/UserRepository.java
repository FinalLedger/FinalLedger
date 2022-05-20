package com.finalledger.repositories;

import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);

    User getUserByUsername(String username);
    User getUserById(long id);

    @Query(value = "SELECT * FROM final_ledger_db.users WHERE email LIKE %?1%", nativeQuery = true)
    List<User> searchByEmail(@Param("email") String email);
}
