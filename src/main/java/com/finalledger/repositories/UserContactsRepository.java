package com.finalledger.repositories;

import com.finalledger.models.Contants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepository extends JpaRepository<Contants, Long> {
}
