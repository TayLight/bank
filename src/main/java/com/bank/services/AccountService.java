package com.bank.services;

import com.bank.entities.Account;
import com.bank.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public void deleteAllById(List<Integer> ids) {
        accountRepository.deleteAllById(ids);
    }
}
