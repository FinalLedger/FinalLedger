package com.finalledger.repositories;

import com.finalledger.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepository extends JpaRepository<Contact, Long> {
    Contact findByUserId (long id);
}
