package com.rich.account;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryAccountRepository implements AccountRepository{

    public static Map<Long, Account> store = new HashMap<>();
    @Override
    public void signIn(Account account) {
        store.put(account.getId(),account);
    }

    @Override
    public Account findById(Long accountId) {
        return store.get(accountId);
    }
}
