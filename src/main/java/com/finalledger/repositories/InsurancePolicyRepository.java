package com.finalledger.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository <User,Long> {
}
