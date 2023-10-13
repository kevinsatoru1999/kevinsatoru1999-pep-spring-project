package com.example.repository;

// public interface AccountRepository {
// }

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    // Custom query to find an account by its username
    Account findByUsername(String username);
}
