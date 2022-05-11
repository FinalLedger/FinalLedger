package com.finalledger.repositories;

import com.finalledger.models.User;
import com.finalledger.models.UserMedicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicalRepository extends JpaRepository<UserMedicalInformation, Long> {

}
