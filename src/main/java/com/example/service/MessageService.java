package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Author: Christian Alberto Gomez
 * Company: Reveture
 * Date: September 8, 2024
 * 
 * Class Purpose:
 * The MessageService class manages business logic and operations related to the Message entity.
 * It offers methods to create, retrieve, update, and delete messages using the MessageRepository
 * for data access. This service layer encapsulates the repository operations and applies any
 * necessary business rules for message management.
 * 
 */

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    public List<Message> getMessagesByUserId(Integer userId) {
        return messageRepository.findByPostedBy(userId);
    }

    public void deleteMessage(Integer messageId) {
        messageRepository.deleteById(messageId);
    }

    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }
}
