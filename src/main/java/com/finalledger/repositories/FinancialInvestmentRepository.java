package com.finalledger.repositories;

import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialInvestmentRepository extends JpaRepository<User,Long> {
}
