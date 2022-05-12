package com.finalledger.repositories;

import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDocumentsRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
