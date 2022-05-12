package com.finalledger.repositories;

import com.finalledger.models.MedicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicalRepository extends JpaRepository<MedicalInformation, Long> {

    MedicalInformation findByUserId (long id);

}
