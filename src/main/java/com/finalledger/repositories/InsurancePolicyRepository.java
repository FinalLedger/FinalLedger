package com.finalledger.repositories;

import com.finalledger.models.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository <InsurancePolicy,Long> {
    InsurancePolicy findByUserId (long id);
}
