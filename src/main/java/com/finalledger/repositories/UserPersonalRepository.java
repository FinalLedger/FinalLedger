package com.finalledger.repositories;

import com.finalledger.models.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalRepository extends JpaRepository<PersonalInformation, Long> {

}
