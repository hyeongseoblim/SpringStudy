package com.rich.order;

import com.rich.account.Account;
import com.rich.account.AccountGrade;
import com.rich.account.AccountRepository;
import com.rich.account.MemoryAccountRepository;
import com.rich.discount.DiscountPolicy;
import com.rich.discount.FixDiscountPolicy;
import com.rich.discount.RateDiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final AccountRepository accountRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(AccountRepository accountRepository, DiscountPolicy rateDiscountPolicy){
        this.accountRepository = accountRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long accountId, String itemName, int itemPrice) {
        Account account = accountRepository.findById(accountId);
        int discountPrice = discountPolicy.discount(account,itemPrice);
        return new Order(accountId, itemName, itemPrice, discountPrice);
    }
}
