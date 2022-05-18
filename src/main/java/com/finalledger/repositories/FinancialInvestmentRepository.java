package com.finalledger.repositories;

import com.finalledger.models.FinancialInvestment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialInvestmentRepository extends JpaRepository<FinancialInvestment,Long> {
    FinancialInvestment findByUserId (long id);
}
