package com.finalledger.repositories;

import com.finalledger.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository <CreditCard,Long> {
    CreditCard findByUserId (long id);
}
