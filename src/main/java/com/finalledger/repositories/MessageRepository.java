package com.finalledger.repositories;

import com.finalledger.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM  final_ledger_db.messages WHERE receiver_id = ?1",  nativeQuery = true)
    List<Message> findAllByReceiver_info(long id);

    @Query(value = "SELECT * FROM  final_ledger_db.messages WHERE sender_id = ?1",  nativeQuery = true)
    List<Message> findAllBySender_info(long id);

}