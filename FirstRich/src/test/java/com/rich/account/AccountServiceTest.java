package com.rich.account;

import com.rich.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    AppConfig appConfig = new AppConfig();
    AccountService accountService =appConfig.accountService();

    @Test
    void join(){
        //given
        Account account = new Account(1L,"account1",AccountGrade.VVIP);
        //when
        accountService.signIn(account);
        Account account12 = accountService.findById(1L);
        //then
        Assertions.assertThat(account).isEqualTo(account12);
    }

}