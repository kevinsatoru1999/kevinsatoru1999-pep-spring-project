package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
// public class SocialMediaController {

// }

@RestController
//@RequestMapping("/api")
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        try {Account newAccount = accountService.registerAccount(account);
        if (newAccount != null) {
            return new ResponseEntity<Account>(newAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
    }
        catch(Exception e){
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account loggedInAccount = accountService.login(account.getUsername(), account.getPassword());
        if (loggedInAccount != null) {
            return new ResponseEntity<Account>(loggedInAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<Account>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message newMessage = messageService.createMessage(message);
        if (newMessage != null) {
            return new ResponseEntity<Message>(newMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/{message_Id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_Id) {
        Message message = messageService.getMessageById(message_Id);
        if (message != null) {
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(HttpStatus.OK);
        }
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable Integer account_id) {
        List<Message> message = messageService.getMessagesByUserId(account_id);
        if (message != null) {
            return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
        } else {
            return null;
        }
    }

    //more endpoint
    //delete 
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable("message_id") Integer messageId) {
        try {
            if (messageService.deleteMessage(messageId)) {
            return new ResponseEntity<Integer> (1,HttpStatus.OK);
            } else {
                return new ResponseEntity<Integer> (0,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Integer>(HttpStatus.OK);
        }
    }
       

    // //update
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(
            @PathVariable("message_id") Integer messageId,
            @RequestBody Message updatedMessage
    ) {
        try {
            Integer updated = messageService.updateMessage(messageId, updatedMessage.getMessage_text());
            if (updated != null) {
                return new ResponseEntity<Integer>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }    
}