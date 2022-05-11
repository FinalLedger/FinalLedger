package com.finalledger.repositories;

import com.finalledger.models.User;
import com.finalledger.models.UserContacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepository extends JpaRepository<UserContacts, Long> {
}
