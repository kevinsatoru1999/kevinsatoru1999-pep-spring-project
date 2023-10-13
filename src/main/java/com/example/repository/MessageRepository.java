package com.example.repository;

// public interface MessageRepository {
// }

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Custom query to find messages posted by a specific user
    //match with account.java class query annotation
    @Query(value="Select * from message where posted_by = :pos_id",nativeQuery = true) 
    List<Message> findByPostedBy(@Param("pos_id")Integer post_by);
}