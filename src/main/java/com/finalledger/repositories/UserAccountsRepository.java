package com.finalledger.repositories;

import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountsRepository extends JpaRepository <User,Long> {
}
