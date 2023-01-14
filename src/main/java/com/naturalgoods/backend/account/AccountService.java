package com.naturalgoods.backend.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
