package com.finalledger.repositories;

import com.finalledger.models.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepository extends JpaRepository<Contacts, Long> {
}
