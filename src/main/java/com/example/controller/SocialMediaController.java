package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;
import java.util.Optional;


/**
 * Author: Christian Alberto Gomez
 * Company: Reveture
 * Date: September 3, 2024
 */

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 @RequestMapping
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){
        if(account.getUsername()==null || account.getUsername().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        if(account.getPassword() == null || account.getPassword().length() < 4){
            return ResponseEntity.badRequest().build();
        }
        if(accountService.getAccountByUsername(account.getUsername()).isPresent()){
            return ResponseEntity.status(409).build();
        }

        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        
        System.out.println("Received login request with: " + account);
    
        Optional<Account> existingAccount = accountService.getAccountByUsername(account.getUsername());
        if (existingAccount.isPresent() && existingAccount.get().getPassword().equals(account.getPassword())) {
            System.out.println("Login successful for user: " + existingAccount.get().getUsername());
            return ResponseEntity.ok(existingAccount.get());
        }
    
        System.out.println("Login failed for user: " + account.getUsername());
        return ResponseEntity.status(401).build();
    }

    
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        if (message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            return ResponseEntity.badRequest().build();
        }
        if (accountService.getAccountById(message.getPostedBy()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Message newMessage = messageService.createMessage(message);
        return ResponseEntity.ok(newMessage);
    }

    
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        Optional<Message> message = messageService.getMessageById(messageId);
        if (message.isPresent()) {
            return ResponseEntity.ok(message.get());
        } else {
            // Return 200 OK with an empty body
            return ResponseEntity.ok().build();
        }
    }


    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId) {
        Optional<Message> message = messageService.getMessageById(messageId);
        if (message.isPresent()) {
            messageService.deleteMessage(messageId);
            return ResponseEntity.ok(1); // Return 1 when a message is successfully deleted
        } else {
            return ResponseEntity.ok().build(); // Return empty body when no message is found
        }
    }

    
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, @RequestBody Message message) {
        Optional<Message> existingMessage = messageService.getMessageById(messageId);
        if (existingMessage.isEmpty() || message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            return ResponseEntity.badRequest().build();
        }
        Message updatedMessage = existingMessage.get();
        updatedMessage.setMessageText(message.getMessageText());
        messageService.updateMessage(updatedMessage);
        return ResponseEntity.ok(1);
    }
    
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByUserId(@PathVariable Integer accountId) {
        List<Message> messages = messageService.getMessagesByUserId(accountId);
        return ResponseEntity.ok(messages);
    }
}
