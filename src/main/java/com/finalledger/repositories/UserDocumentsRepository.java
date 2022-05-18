package com.finalledger.repositories;

import com.finalledger.models.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDocumentsRepository extends JpaRepository<Documents,Long> {

}
