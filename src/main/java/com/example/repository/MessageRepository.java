package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Christian Alberto Gomez
 * Company: Reveture
 * Date: September 8, 2024
 * 
 * Class Purpose:
 * The MessageRepository interface provides data access methods for the Message entity.
 * It extends JpaRepository, offering CRUD operations and additional query capabilities.
 * The repository includes a custom method to find messages based on the user ID of the message's author.
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPostedBy(Integer postedBy);
}
