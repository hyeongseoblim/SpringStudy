package com.rich.order;

import com.rich.AppConfig;
import com.rich.account.Account;
import com.rich.account.AccountGrade;
import com.rich.account.AccountService;
import com.rich.account.AccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    AppConfig appConfig = new AppConfig();

    AccountService accountService =  appConfig.accountService();
    OrderService orderService =  appConfig.orderService();

    @Test
    void createOrder(){
        //given
        Long accountId = 1L;
        Account account = new Account(accountId,"AccountA", AccountGrade.VVIP);
        accountService.signIn(account);
        //when
        Order order = orderService.createOrder(accountId,"item1",10000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(9000);
    }

    @Test
    void createOrderRateDiscount(){
        //given
        Long accountId = 1L;
        Long accountGeneralId = 2L;
        Account account = new Account(accountId, "AccountRateA",AccountGrade.VIP);
        Account accountGeneral = new Account(accountGeneralId, "AccountGenralB",AccountGrade.GENERAL);

        accountService.signIn(account);
        accountService.signIn(accountGeneral);

        //when
        Order vipOrder = orderService.createOrder(accountId,"item1",10000);
        Order generalOrder = orderService.createOrder(accountGeneralId,"item1",10000);

        //then

    }

}