package com.example.service;

// public class AccountService {
// }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) throws Exception{
        // Implement registration logic based on user stories
        // Validate input, check for existing username, save to database, and return the saved account
        // Handle validation and exceptions appropriately
        
        // Check if the username is not blank and password is at least 4 characters long
        if (account.getUsername() == null || account.getUsername().isBlank() || account.getPassword() == null || account.getPassword().length() < 4) {
            // Handle validation failure
            // You can throw an exception or return null as per your project requirements
            return null;
        }
        
        // Check if the username already exists
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount != null ) {
            // Handle duplicate username
            // You can throw an exception or return null as per your project requirements
            throw new Exception("the acoount can not have duplicate username");
            //return null;
        }
        
        // Save the new account to the database
        return accountRepository.save(account);
    }

    public Account login(String username, String password) {
        // Implement login logic based on user stories
        // Check if the username and password match a real account existing on the database
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            // Login successful, return the account
            return account;
        } else {
            // Login failed
            // You can throw an exception or return null as per your project requirements
            return null;
        }
    }

    // Additional methods for account-related operations
}

