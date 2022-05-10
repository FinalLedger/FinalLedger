package com.finalledger.repositories;

import com.finalledger.models.User;
import com.finalledger.models.UserPersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalRepository extends JpaRepository<UserPersonalInformation, Long> {

}
