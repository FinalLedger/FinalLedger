package com.finalledger.repositories;

import com.finalledger.models.BankAccounts;
import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountsRepository extends JpaRepository <BankAccounts,Long> {
}
