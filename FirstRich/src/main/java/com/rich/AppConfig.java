package com.rich;

import com.rich.account.AccountService;
import com.rich.account.AccountServiceImpl;
import com.rich.account.MemoryAccountRepository;
import com.rich.discount.DiscountPolicy;
import com.rich.discount.RateDiscountPolicy;
import com.rich.order.OrderService;
import com.rich.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//환경 구성에 대한 클래스
@Configuration
public class AppConfig {

    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl(accountRepository());
    }

    @Bean
    public MemoryAccountRepository accountRepository() {
        return new MemoryAccountRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(accountRepository(),  discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

}
