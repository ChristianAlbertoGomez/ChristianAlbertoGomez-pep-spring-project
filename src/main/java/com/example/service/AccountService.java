package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Christian Alberto Gomez
 * Company: Reveture
 * Date: September 8, 2024
 * 
 * Class Purpose:
 * The AccountService class handles business logic and operations related to the Account entity.
 * It provides methods to create, retrieve, update, and delete accounts, utilizing the AccountRepository
 * for data access. This service layer abstracts the underlying repository operations and enforces any
 * business rules related to account management.
 */

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}
