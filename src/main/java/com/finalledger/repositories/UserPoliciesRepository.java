package com.finalledger.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPoliciesRepository extends JpaRepository <User,Long> {
}
