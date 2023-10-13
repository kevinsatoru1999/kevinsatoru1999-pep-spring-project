package com.example.service;

// public class MessageService {
// }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }



    public Message createMessage(Message message) {
        // Implement message creation logic based on user stories
        // Validate input, link to existing user, save to the database, and return the saved message
        // Handle validation and exceptions appropriately
        //if accunt ius not in used, the coount does not exist


        // Check if the message_text is not blank and is under 255 characters
        if (message.getMessage_text() == null || message.getMessage_text().isBlank() || message.getMessage_text().length() > 255 || !accountRepository.existsById(message.getPosted_by())) {
            // Handle validation failure
            // You can throw an exception or return null as per your project requirements
            return null;
        }


        
        // Save the new message to the database
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        // Implement logic to retrieve all messages from the database and return the list
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId) {
        // Implement logic to retrieve a message by its ID from the database and return it
        return messageRepository.findById(messageId).orElse(null);
    }

    public boolean deleteMessage(Integer messageId) {
        // Implement logic to delete a message by its ID from the database
        // Return true if deletion is successful, false otherwise
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return true;
        } else {
            return false;
        }
    }

    public Integer updateMessage(Integer messageId, String newMessageText) {
        // Implement logic to update a message's text by its ID in the database
        // Validate input, find the message, update the text, save to the database, and return the updated message
        Message existingMessage = messageRepository.findById(messageId).orElse(null);
        if (existingMessage != null && newMessageText != null && !newMessageText.isBlank() && newMessageText.length() <= 255) {
            existingMessage.setMessage_text(newMessageText);
            messageRepository.save(existingMessage);
            return 1;
        } else {
            // Handle validation failure or message not found
            // You can throw an exception or return null as per your project requirements
            return null;
        }
    }

    public List<Message> getMessagesByUserId(Integer postedBy) {
        // Implement logic to retrieve messages posted by a specific user from the database and return the list
        return messageRepository.findByPostedBy(postedBy);
    }

    // Additional methods for message-related operations
}
