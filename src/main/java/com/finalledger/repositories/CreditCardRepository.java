package com.finalledger.repositories;

import com.finalledger.models.BankAccounts;
import com.finalledger.models.CreditCard;
import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository <CreditCard,Long> {
    CreditCard findByUserId (long id);
}
