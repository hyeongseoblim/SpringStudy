package com.rich.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    //Interface에 의존 어떤게 들어올지는 외부에서 주입되는 값에 따라 다름 -> DIP

    @Autowired //ac.getBean(AccountRepository.class)
    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public void signIn(Account account) {
        accountRepository.signIn(account);
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId);
    }
}
