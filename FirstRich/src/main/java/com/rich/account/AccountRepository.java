package com.rich.account;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
    void signIn(Account account);
    Account findById(Long accountId);
}
