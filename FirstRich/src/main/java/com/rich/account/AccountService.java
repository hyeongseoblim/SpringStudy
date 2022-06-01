package com.rich.account;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    void signIn(Account account);

    Account findById(Long accountId);
}
