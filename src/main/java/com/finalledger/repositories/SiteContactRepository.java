package com.finalledger.repositories;

import com.finalledger.models.Contact;
import com.finalledger.models.SiteContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SiteContactRepository extends JpaRepository<SiteContact, Long> {

    @Query(value = "SELECT * FROM  final_ledger_db.site_contacts WHERE list_owner_id = ?1",  nativeQuery = true)
    List<SiteContact> findContactsByOwner_userIs(long id);

    @Query(value = "SELECT * FROM final_ledger_db.site_contacts WHERE added_user_id = ?1 AND list_owner_id = ?2", nativeQuery = true)
    List<SiteContact> findByOwner_userAndAdded_user_idExists(long added_user_id, long list_owner_id);

}