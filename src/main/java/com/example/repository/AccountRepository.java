package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Christian Alberto Gomez
 * Company: Reveture
 * Date: September 8, 2024
 * 
 * Class Purpose:
 *  The AccountRepository interface provides data access methods for the Account entity. 
 *  It extends JpaRepository, which offers CRUD operations and allows querying of Account data 
 *  using various methods. The repository includes a custom method to find accounts by their username.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
