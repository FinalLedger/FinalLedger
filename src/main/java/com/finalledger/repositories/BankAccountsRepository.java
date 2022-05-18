package com.finalledger.repositories;

import com.finalledger.models.BankAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountsRepository extends JpaRepository <BankAccounts,Long> {
    BankAccounts findByUserId (long id);
}
