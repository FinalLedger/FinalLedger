package com.finalledger.repositories;

import com.finalledger.models.InsurancePolicy;
import com.finalledger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository <InsurancePolicy,Long> {
}
